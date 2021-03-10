    package com.example.cbr.activities;

    import android.content.Context;
    import android.content.Intent;
    import android.os.Bundle;
    import android.util.Log;
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

    private static final String CLIENT_ID = "clientID";
    private static final String LOG_TAG = "NewVisitActivity";

    private int clientId;
    private int visitId;

    private Fragment currentFragment;
    private VisitGeneralQuestionSetData generalQuestionSetData;
    private VisitHealthQuestionSetData healthQuestionSetData;
    private VisitEducationQuestionSetData educationQuestionSetData;
    private VisitSocialQuestionSetData socialQuestionSetData;
    private LinkedList<Fragment> nextFragments;
    private Stack<Fragment> prevFragments;
    private Button buttonBack;
    private Button buttonRecord;
    private Button buttonNext;
    private byte totalFragments;
    private byte pageNum;

    private NewVisitContract.Presenter presenter;

    public static Intent makeLaunchIntent(
            Context context,
            final int clientID) {
        Intent intent = new Intent(context, NewVisitActivity.class);
        intent.putExtra(CLIENT_ID, clientID);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewVisitBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getSupportActionBar().hide();

        setPresenter(new NewVisitPresenter(this, NewVisitActivity.this));

        Intent intent = getIntent();
        clientId = intent.getIntExtra(CLIENT_ID, -1);

        if (clientId == -1) {
            Log.d(LOG_TAG, "onCreate: failed to get client ID");
        }

        generalQuestionSetData = new VisitGeneralQuestionSetData();
        healthQuestionSetData = new VisitHealthQuestionSetData();
        educationQuestionSetData = new VisitEducationQuestionSetData();
        socialQuestionSetData = new VisitSocialQuestionSetData();

        setWorkerName();

        currentFragment = new VisitFirstQuestionSetFragment(
                binding,
                generalQuestionSetData,
                NewVisitActivity.this);
        manageFragment(currentFragment);
        totalFragments += 1;
        pageNum = 1;

        nextFragments = new LinkedList<>();
        prevFragments = new Stack<>();

        setupNextButton();
        setupBackButton();
        setupRecordButton();
    }

    private void setWorkerName() {
        Users users = Users.getInstance();
        final String workerName = users.getFirstName() + " " + users.getLastName();
        generalQuestionSetData.setWorkerName(workerName);
    }

    private void setupRecordButton() {
        buttonRecord = binding.newVisitRecordButton;

        buttonRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visitId = ThreadLocalRandom.current().nextInt(100000000, 999999999);

                generalQuestionSetData.setClientId(clientId);
                generalQuestionSetData.setVisitId(visitId);

                healthQuestionSetData.setClientId(clientId);
                healthQuestionSetData.setVisitId(visitId);

                educationQuestionSetData.setClientId(clientId);
                educationQuestionSetData.setVisitId(visitId);

                socialQuestionSetData.setClientId(clientId);
                socialQuestionSetData.setVisitId(visitId);

                onFragmentSaveText(currentFragment);
                handleEmptyRequiredQuestions();
            }
        });
    }

        private void handleEmptyRequiredQuestions() {
            final List<String> emptyGeneralQuestions = generalQuestionSetData.getEmptyQuestions();
            final List<String> emptyHealthQuestions = healthQuestionSetData.getEmptyQuestions();
            final List<String> emptyEducationQuestions = educationQuestionSetData.getEmptyQuestions();
            final List<String> emptySocialQuestions = socialQuestionSetData.getEmptyQuestions();
            final String purposeOfVisit = generalQuestionSetData.getPurposeOfVisit();

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
                if (!purposeOfVisit.equalsIgnoreCase(Constants.CBR)) {
                    displayNumberEmpty(emptyGeneralQuestions);
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
        }

        private boolean isAllRequiredQuestionsFilled(List<String> emptyGeneralQuestions,
                                                     List<String> emptyHealthQuestions,
                                                     List<String> emptyEducationQuestions,
                                                     List<String> emptySocialQuestions) {
            boolean isAllFilled;

            final String purposeOfVisit = generalQuestionSetData.getPurposeOfVisit();
            final boolean isHealthChecked = generalQuestionSetData.isHealthChecked();
            final boolean isEducationChecked = generalQuestionSetData.isEducationChecked();
            final boolean isSocialChecked = generalQuestionSetData.isSocialChecked();

            if (!purposeOfVisit.equalsIgnoreCase(Constants.CBR)) {
                isAllFilled = emptyGeneralQuestions.isEmpty();
            } else {
                // This one-liner may obscure readability, but it is necessary to remove
                // 'if' statements (improve performance).
                // All is filled under the condition that the empty question lists are empty,
                // it can depend on whether health, education, or social is considered required.
                isAllFilled = emptyGeneralQuestions.isEmpty()
                    && (!isHealthChecked || emptyHealthQuestions.isEmpty())
                    && (!isEducationChecked || emptyEducationQuestions.isEmpty())
                    && (!isSocialChecked || emptySocialQuestions.isEmpty());
            }
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
                    buttonRecord.setVisibility(View.GONE);
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
                                healthQuestionSetData));
                        totalFragments += 1;
                    }
                    if (generalQuestionSetData.isEducationChecked()) {
                        nextFragments.offer(new VisitThirdQuestionSetFragment(
                                educationQuestionSetData));
                        totalFragments += 1;
                    }
                    if (generalQuestionSetData.isSocialChecked()) {
                        nextFragments.offer(new VisitFourthQuestionSetFragment(
                                socialQuestionSetData));
                        totalFragments += 1;
                    }
                    if (generalQuestionSetData.getPurposeOfVisit().equalsIgnoreCase(Constants.CBR)
                            && (!generalQuestionSetData.isHealthChecked()
                            && !generalQuestionSetData.isEducationChecked()
                            && !generalQuestionSetData.isSocialChecked())) {
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
                    buttonRecord.setVisibility(View.VISIBLE);
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

        EditText socialAdvice = fourthFragment.getEditTextAdvice();
        EditText socialAdvocacy = fourthFragment.getEditTextAdvocacy();
        EditText socialRef = fourthFragment.getEditTextRef();
        EditText socialEncouragement = fourthFragment.getEditTextEncouragement();
        EditText socialOutcome = fourthFragment.getEditTextSocialOutcome();

        socialQuestionSetData.setSocialAdviceDesc(socialAdvice.getText().toString());
        socialQuestionSetData.setSocialAdvocacyDesc(socialAdvocacy.getText().toString());
        socialQuestionSetData.setSocialReferralDesc(socialRef.getText().toString());
        socialQuestionSetData.setSocialEncouragementDesc(socialEncouragement.getText().toString());
        socialQuestionSetData.setSocialOutcomeDesc(socialOutcome.getText().toString());
    }

    private void saveThirdQuestionSetDesc() {
        VisitThirdQuestionSetFragment thirdFragment = (VisitThirdQuestionSetFragment) currentFragment;

        EditText educationAdvice = thirdFragment.getEditTextAdvice();
        EditText educationAdvocacy = thirdFragment.getEditTextAdvocacy();
        EditText educationRef = thirdFragment.getEditTextRef();
        EditText educationEncouragement = thirdFragment.getEditTextEncouragement();
        EditText educationOutcome = thirdFragment.getEditTextEducationOutcome();

        educationQuestionSetData.setEducationAdviceDesc(educationAdvice.getText().toString());
        educationQuestionSetData.setEducationAdvocacyDesc(educationAdvocacy.getText().toString());
        educationQuestionSetData.setEducationReferralDesc(educationRef.getText().toString());
        educationQuestionSetData.setEducationEncouragementDesc(educationEncouragement.getText().toString());
        educationQuestionSetData.setEducationOutcomeDesc(educationOutcome.getText().toString());
    }

    private void saveSecondQuestionSetDesc() {
        VisitSecondQuestionSetFragment secondFragment = (VisitSecondQuestionSetFragment) currentFragment;

        EditText wheelChairDesc = secondFragment.getEditTextWheelChair();
        EditText prostheticDesc = secondFragment.getEditTextProsthetic();
        EditText orthoticDesc = secondFragment.getEditTextOrthotic();
        EditText WRDesc = secondFragment.getEditTextWR();
        EditText referralToHCDesc = secondFragment.getEditTextReferralToHC();
        EditText healthAdviceDesc = secondFragment.getEditTextAdvice();
        EditText healthAdvocacyDesc = secondFragment.getEditTextAdvocacy();
        EditText healthEncouragementDesc = secondFragment.getEditTextEncouragement();
        EditText healthOutcomeDesc = secondFragment.getEditTextHealthOutcome();

        healthQuestionSetData.setWheelChairDesc(wheelChairDesc.getText().toString());
        healthQuestionSetData.setProstheticDesc(prostheticDesc.getText().toString());
        healthQuestionSetData.setOrthoticDesc(orthoticDesc.getText().toString());
        healthQuestionSetData.setWheelChairRepairDesc(WRDesc.getText().toString());
        healthQuestionSetData.setReferralToHCDesc(referralToHCDesc.getText().toString());
        healthQuestionSetData.setHealthAdviceDesc(healthAdviceDesc.getText().toString());
        healthQuestionSetData.setHealthAdvocacyDesc(healthAdvocacyDesc.getText().toString());
        healthQuestionSetData.setHealthEncouragementDesc(healthEncouragementDesc.getText().toString());
        healthQuestionSetData.setHealthOutcomeDesc(healthOutcomeDesc.getText().toString());
    }

    private void saveFirstQuestionSet() {
        VisitFirstQuestionSetFragment firstFragment = (VisitFirstQuestionSetFragment) currentFragment;

        EditText workerName = firstFragment.getCbrWorkerName();
        EditText locationOfVisit = firstFragment.getLocation();
        EditText villageNumberString = firstFragment.getVillageNumber();

        generalQuestionSetData.setWorkerName(workerName.getText().toString());
        generalQuestionSetData.setVisitGpsLocation(locationOfVisit.getText().toString());
        generalQuestionSetData.setVillageNumber(villageNumberString.getText().toString());
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