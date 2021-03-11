    package com.example.cbr.activities;

    import android.content.Context;
    import android.content.Intent;
    import android.os.Bundle;
    import android.os.StrictMode;
    import android.util.Log;
    import android.view.Menu;
    import android.view.MenuItem;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.TextView;
    import android.widget.Toast;

    import androidx.annotation.NonNull;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.fragment.app.Fragment;
    import androidx.fragment.app.FragmentManager;
    import androidx.fragment.app.FragmentTransaction;

    import com.example.cbr.R;
    import com.example.cbr.databinding.ActivityNewVisitBinding;
    import com.example.cbr.fragments.newvisit.NewVisitContract;
    import com.example.cbr.fragments.newvisit.NewVisitPresenter;
    import com.example.cbr.fragments.newvisit.VisitFirstQuestionSetFragment;
    import com.example.cbr.fragments.newvisit.VisitFourthQuestionSetFragment;
    import com.example.cbr.fragments.newvisit.VisitSecondQuestionSetFragment;
    import com.example.cbr.fragments.newvisit.VisitThirdQuestionSetFragment;
    import com.example.cbr.models.ClientInfo;
    import com.example.cbr.models.Users;
    import com.example.cbr.models.VisitEducationQuestionSetData;
    import com.example.cbr.models.VisitGeneralQuestionSetData;
    import com.example.cbr.models.VisitHealthQuestionSetData;
    import com.example.cbr.models.VisitSocialQuestionSetData;
    import com.example.cbr.retrofit.JsonPlaceHolderApi;
    import com.example.cbr.retrofit.RetrofitInit;
    import com.example.cbr.util.Constants;

    import java.util.ArrayList;
    import java.util.LinkedList;
    import java.util.List;
    import java.util.Stack;
    import java.util.concurrent.ThreadLocalRandom;

    import retrofit2.Call;
    import retrofit2.Callback;
    import retrofit2.Response;
    import retrofit2.Retrofit;

    /**
    * Activity to handle new visit questions, which holds four sets of questions (fragments)
    * */

public class NewVisitActivity extends AppCompatActivity implements NewVisitContract.View {

    private ActivityNewVisitBinding binding;

    private static final String CLIENT_INFO = "clientInfo";
    private static final String LOG_TAG = "NewVisitActivity";

    private int clientId;
    private ClientInfo clientInfo;

    private Fragment currentFragment;
    private VisitGeneralQuestionSetData generalQuestionSetData;
    private VisitHealthQuestionSetData healthQuestionSetData;
    private VisitEducationQuestionSetData educationQuestionSetData;
    private VisitSocialQuestionSetData socialQuestionSetData;
    private LinkedList<Fragment> nextFragments;
    private Stack<Fragment> prevFragments;
    private Button buttonBack;
    private Button buttonNext;
    private byte totalFragments;
    private byte pageNum;

    private NewVisitContract.Presenter presenter;

    public static Intent makeLaunchIntent(
            Context context,
            ClientInfo clientInfo) {
        Intent intent = new Intent(context, NewVisitActivity.class);
        intent.putExtra(CLIENT_INFO, clientInfo);
        return intent;
    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        binding = ActivityNewVisitBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setTitle(getString(R.string.new_visit_title));

        setPresenter(new NewVisitPresenter(this, NewVisitActivity.this));

        Intent intent = getIntent();
        clientInfo = (ClientInfo) intent.getSerializableExtra(CLIENT_INFO);

        // TODO: 2021-03-10 Client ID from ClientInfo does not match with clientId in DB, wait for fix
        if (clientId == -1) {
            Log.d(LOG_TAG, "onCreate: failed to get client ID");
        }
        Log.d(LOG_TAG, "onCreate: clientId=" + clientId);


        generalQuestionSetData = new VisitGeneralQuestionSetData();
        healthQuestionSetData = new VisitHealthQuestionSetData();
        educationQuestionSetData = new VisitEducationQuestionSetData();
        socialQuestionSetData = new VisitSocialQuestionSetData();

        setVisitClientId();
        setWorkerName();

        currentFragment = new VisitFirstQuestionSetFragment(
                binding,
                generalQuestionSetData);
        manageFragment(currentFragment);
        totalFragments += 1;
        pageNum = 1;

        nextFragments = new LinkedList<>();
        prevFragments = new Stack<>();

        setupNextButton();
        setupBackButton();
    }

    private void setVisitClientId() {
        final int visitId = ThreadLocalRandom.current().nextInt(100000000, 999999999);

        generalQuestionSetData.setClientId(clientId);
        generalQuestionSetData.setVisitId(visitId);

        healthQuestionSetData.setClientId(clientId);
        healthQuestionSetData.setVisitId(visitId);

        educationQuestionSetData.setClientId(clientId);
        educationQuestionSetData.setVisitId(visitId);

        socialQuestionSetData.setClientId(clientId);
        socialQuestionSetData.setVisitId(visitId);
    }

    private void setWorkerName() {
        Users users = Users.getInstance();
        final String workerName = users.getFirstName() + " " + users.getLastName();
        generalQuestionSetData.setWorkerName(workerName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_record, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle action bar item clicks here
        int itemId = item.getItemId();

        if (itemId == R.id.newVisit_recordButton) {
            onFragmentSaveText(currentFragment);
            handleEmptyRequiredQuestions();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void handleEmptyRequiredQuestions() {
        final List<String> emptyGeneralQuestions = generalQuestionSetData.getEmptyQuestions();
        final List<String> emptyHealthQuestions = healthQuestionSetData.getEmptyQuestions();
        final List<String> emptyEducationQuestions = educationQuestionSetData.getEmptyQuestions();
        final List<String> emptySocialQuestions = socialQuestionSetData.getEmptyQuestions();

        boolean isAllFilled = isAllRequiredQuestionsFilled(emptyGeneralQuestions,
                emptyHealthQuestions, emptyEducationQuestions, emptySocialQuestions);
        if (isAllFilled) {
            presenter.createVisitGeneralQuestionSetData(generalQuestionSetData);
            if (generalQuestionSetData.isHealthChecked()) {
                presenter.createVisitHealthQuestionSetData(healthQuestionSetData);
            }
            if (generalQuestionSetData.isEducationChecked()) {
                presenter.createVisitEducationQuestionSetData(educationQuestionSetData);
            }
            if (generalQuestionSetData.isSocialChecked()) {
                presenter.createVisitSocialQuestionSetData(socialQuestionSetData);
            }
            finish();
        } else {
            List<String> emptyQuestions = new ArrayList<>(emptyGeneralQuestions);
            if (generalQuestionSetData.isHealthChecked()) {
                emptyQuestions.addAll(emptyHealthQuestions);
                displayNumberEmpty(emptyQuestions);
            }
            if (generalQuestionSetData.isEducationChecked()) {
                emptyQuestions.addAll(emptyEducationQuestions);
            }
            if (generalQuestionSetData.isSocialChecked()) {
                emptyQuestions.addAll(emptySocialQuestions);
            }
            displayNumberEmpty(emptyQuestions);
        }
    }

    private boolean isAllRequiredQuestionsFilled(List<String> emptyGeneralQuestions,
                                                 List<String> emptyHealthQuestions,
                                                 List<String> emptyEducationQuestions,
                                                 List<String> emptySocialQuestions) {
        boolean isAllFilled;

        final boolean isHealthChecked = generalQuestionSetData.isHealthChecked();
        final boolean isEducationChecked = generalQuestionSetData.isEducationChecked();
        final boolean isSocialChecked = generalQuestionSetData.isSocialChecked();

        // This one-liner may obscure readability, but it is necessary to remove
        // 'if' statements (improve performance).
        // All is filled under the condition that the empty question lists are empty,
        // it can depend on whether health, education, or social is considered required.
        isAllFilled = emptyGeneralQuestions.isEmpty()
            && (!isHealthChecked || emptyHealthQuestions.isEmpty())
            && (!isEducationChecked || emptyEducationQuestions.isEmpty())
            && (!isSocialChecked || emptySocialQuestions.isEmpty());

        return isAllFilled;
    }

    private void displayNumberEmpty(List<String> emptyQuestions) {
        TextView textViewRecordError = binding.newVisitRecordErrorTextView;
        TextView textViewQuestionNumbers = binding.newVisitQuestionNumbersTextView;

        textViewRecordError.setVisibility(View.VISIBLE);
        textViewQuestionNumbers.setVisibility(View.VISIBLE);

        StringBuilder questionNumbers = new StringBuilder();
        for (int i = 0; i < emptyQuestions.size(); i++) {
            questionNumbers.append(emptyQuestions.get(i)).append(" ");
        }
        textViewQuestionNumbers.setText(questionNumbers.toString());
    }

    private void setupBackButton() {
        buttonBack = binding.newVisitBackButton;
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pageNum-1 == 1) {
                    Log.d(LOG_TAG, "pagNum-1: " + (pageNum-1));
                    buttonBack.setVisibility(View.GONE);
                }
                onFragmentSaveText(currentFragment);
                Log.d(LOG_TAG, "pagNum onBack: " + pageNum);

                nextFragments.addFirst(currentFragment);
                currentFragment = prevFragments.pop();
                manageFragment(currentFragment);
                pageNum -= 1;

                if (pageNum < totalFragments) {
                    Log.d(LOG_TAG, "pagNum: " + pageNum + " totalFragments: " + totalFragments);
                    buttonNext.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void setupNextButton() {
        buttonNext = binding.newVisitNextButton;
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFragmentSaveText(currentFragment);

                if (pageNum == 1) {
                    prevFragments.clear();
                    nextFragments.clear();

                    totalFragments = 1;

                    if (generalQuestionSetData.isHealthChecked()) {
                        nextFragments.offer(new VisitSecondQuestionSetFragment(
                                healthQuestionSetData, clientInfo));
                        totalFragments += 1;
                    }
                    if (generalQuestionSetData.isEducationChecked()) {
                        nextFragments.offer(new VisitThirdQuestionSetFragment(
                                educationQuestionSetData, clientInfo));
                        totalFragments += 1;
                    }
                    if (generalQuestionSetData.isSocialChecked()) {
                        nextFragments.offer(new VisitFourthQuestionSetFragment(
                                socialQuestionSetData, clientInfo));
                        totalFragments += 1;
                    }
                    if (!generalQuestionSetData.isHealthChecked()
                            && !generalQuestionSetData.isEducationChecked()
                            && !generalQuestionSetData.isSocialChecked()) {
                        Toast.makeText(NewVisitActivity.this,
                                getString(R.string.question_2_not_filled),
                                Toast.LENGTH_SHORT).show();
                    }
                }

                if (nextFragments.peek() != null) {
                    prevFragments.add(currentFragment);
                    currentFragment = nextFragments.removeFirst();
                    manageFragment(currentFragment);
                    pageNum += 1;
                }

                Log.d(LOG_TAG, "pageNum: " + pageNum);
                Log.d(LOG_TAG, "Is health: " + generalQuestionSetData.isHealthChecked()
                        + " is education: " + generalQuestionSetData.isEducationChecked()
                        + " is social: " + generalQuestionSetData.isSocialChecked());

                if (pageNum > 1) {
                    buttonBack.setVisibility(View.VISIBLE);
                }
                if (pageNum == totalFragments && pageNum != 1) {
                    buttonNext.setVisibility(View.GONE);
                }
            }
        });
    }

    // We need to save texts here, because only when the user press the next/back/record buttons
    // will we know that the user has finished writing.
    private void onFragmentSaveText(Fragment currentFragment) {
            if (currentFragment instanceof VisitFirstQuestionSetFragment) {
                saveFirstQuestionSet();
            } else if (currentFragment instanceof VisitSecondQuestionSetFragment) {
                saveSecondQuestionSetDesc();
            } else if (currentFragment instanceof VisitThirdQuestionSetFragment) {
                saveThirdQuestionSetDesc();
            } else {
                saveFourthQuestionSetDesc();
            }
    }

    private void saveFourthQuestionSetDesc() {
        VisitFourthQuestionSetFragment fourthFragment = (VisitFourthQuestionSetFragment) currentFragment;

        socialQuestionSetData.setSocialAdviceDesc(fourthFragment.getSocialAdvice());
        socialQuestionSetData.setSocialAdvocacyDesc(fourthFragment.getSocialAdvocacy());
        socialQuestionSetData.setSocialReferralDesc(fourthFragment.getSocialRef());
        socialQuestionSetData.setSocialEncouragementDesc(fourthFragment.getSocialEncouragement());
        socialQuestionSetData.setSocialOutcomeDesc(fourthFragment.getSocialSocialOutcome());
    }

    private void saveThirdQuestionSetDesc() {
        VisitThirdQuestionSetFragment thirdFragment = (VisitThirdQuestionSetFragment) currentFragment;

        educationQuestionSetData.setEducationAdviceDesc(thirdFragment.getEducationAdvice());
        educationQuestionSetData.setEducationAdvocacyDesc(thirdFragment.getEducationAdvocacy());
        educationQuestionSetData.setEducationReferralDesc(thirdFragment.getEducationRef());
        educationQuestionSetData.setEducationEncouragementDesc(thirdFragment.getEducationEncouragement());
        educationQuestionSetData.setEducationOutcomeDesc(thirdFragment.getEducationEducationOutcome());
    }

    private void saveSecondQuestionSetDesc() {
        VisitSecondQuestionSetFragment secondFragment = (VisitSecondQuestionSetFragment) currentFragment;

        healthQuestionSetData.setWheelChairDesc(secondFragment.getHealthWheelChair());
        healthQuestionSetData.setProstheticDesc(secondFragment.getHealthProsthetic());
        healthQuestionSetData.setOrthoticDesc(secondFragment.getHealthOrthotic());
        healthQuestionSetData.setWheelChairRepairDesc(secondFragment.getHealthWR());
        healthQuestionSetData.setReferralToHCDesc(secondFragment.getHealthReferralToHC());
        healthQuestionSetData.setHealthAdviceDesc(secondFragment.getHealthAdvice());
        healthQuestionSetData.setHealthAdvocacyDesc(secondFragment.getHealthAdvocacy());
        healthQuestionSetData.setHealthEncouragementDesc(secondFragment.getHealthEncouragement());
        healthQuestionSetData.setHealthOutcomeDesc(secondFragment.getHealthHealthOutcome());
    }

    private void saveFirstQuestionSet() {
        VisitFirstQuestionSetFragment firstFragment = (VisitFirstQuestionSetFragment) currentFragment;

        generalQuestionSetData.setWorkerName(firstFragment.getCbrWorkerName());
        generalQuestionSetData.setVisitGpsLocation(firstFragment.getLocation());
        generalQuestionSetData.setVillageNumber(firstFragment.getVillageNumber());
    }

    private void manageFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.newVisit_questionFrame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void setPresenter(NewVisitContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
