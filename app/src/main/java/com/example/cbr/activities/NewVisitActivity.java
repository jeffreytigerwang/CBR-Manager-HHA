package com.example.cbr.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cbr.R;
import com.example.cbr.databinding.ActivityNewVisitBinding;
import com.example.cbr.fragments.newvisit.VisitFirstQuestionSetFragment;
import com.example.cbr.fragments.newvisit.VisitFourthQuestionSetFragment;
import com.example.cbr.fragments.newvisit.VisitSecondQuestionSetFragment;
import com.example.cbr.fragments.newvisit.VisitThirdQuestionSetFragment;
import com.example.cbr.models.Constants;
import com.example.cbr.models.VisitCheckContainer;

import java.util.LinkedList;
import java.util.Stack;

public class NewVisitActivity extends AppCompatActivity {

    private ActivityNewVisitBinding binding;

    private static final String VISIT_RECORD = "visitCheckContainer";
    private static final String CLIENT_ID = "clientID";
    private static final String LOG_TAG = "NewVisitActivity";

    private Fragment currentFragment;
    private VisitCheckContainer visitCheckContainer;
    private LinkedList<Fragment> nextFragments;
    private Stack<Fragment> prevFragments;
    private Button buttonBack;
    private Button buttonRecord;
    private Button buttonNext;
    private byte totalFragments;
    private byte pageNum;

    public static Intent makeLaunchIntent(
            Context context,
            final long clientID,
            final VisitCheckContainer visitCheckContainer) {
        Intent intent = new Intent(context, NewVisitActivity.class);
        intent.putExtra(CLIENT_ID, clientID);
        intent.putExtra(VISIT_RECORD, visitCheckContainer);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewVisitBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        Intent intent = getIntent();
        final long clientID = intent.getLongExtra(CLIENT_ID, -1);
        visitCheckContainer = (VisitCheckContainer) intent.getSerializableExtra(VISIT_RECORD);

        if (clientID != -1) {
            // TODO: 2021-02-09 get client info from DB
        } else {
            Log.d(LOG_TAG, "onCreate: failed to get client ID");
        }

        currentFragment = new VisitFirstQuestionSetFragment(
                binding,
                visitCheckContainer,
                NewVisitActivity.this
        );
        manageFragment(currentFragment);
        totalFragments += 1;
        pageNum = 1;

        nextFragments = new LinkedList<>();
        prevFragments = new Stack<>();

        setupNextButton();
        setupBackButton();
        setupRecordButton();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // TODO: 2021-02-15 user confirmation: save session or delete 
        removeSavedSession();
    }

    private void setupRecordButton() {
        buttonRecord = binding.buttonVisitRecord;

        buttonRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2021-02-11 save data to db
                removeSavedSession();
                finish();
            }
        });
    }

    private void removeSavedSession() {
        clearSharedPreference(Constants.QUESTION_SET_1_PREF_NAME);
        clearSharedPreference(Constants.QUESTION_SET_2_PREF_NAME);
        clearSharedPreference(Constants.QUESTION_SET_3_PREF_NAME);
        clearSharedPreference(Constants.QUESTION_SET_4_PREF_NAME);
    }

    private void clearSharedPreference(String questionSetPrefName) {
        SharedPreferences sharedPref = getSharedPreferences(questionSetPrefName, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit().clear();
        editor.apply();
    }

    private void setupBackButton() {
        buttonBack = binding.buttonVisitBack;
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pageNum-1 == 1) {
                    Log.d(LOG_TAG, "pagNum-1: " + (pageNum-1));
                    buttonBack.setVisibility(View.GONE);
                }
                saveSession(pageNum);
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
        buttonNext = binding.buttonVisitNext;
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSession(pageNum);

                if (pageNum == 1) {
                    prevFragments.clear();
                    nextFragments.clear();

                    totalFragments = 1;

                    if (visitCheckContainer.isHealthChecked()) {
                        nextFragments.offer(new VisitSecondQuestionSetFragment(
                                visitCheckContainer, NewVisitActivity.this));
                        totalFragments += 1;
                    }
                    if (visitCheckContainer.isEducationChecked()) {
                        nextFragments.offer(new VisitThirdQuestionSetFragment(
                                visitCheckContainer, NewVisitActivity.this));
                        totalFragments += 1;
                    }
                    if (visitCheckContainer.isSocialChecked()) {
                        nextFragments.offer(new VisitFourthQuestionSetFragment(
                                visitCheckContainer, NewVisitActivity.this));
                        totalFragments += 1;
                    }
                }

                if (nextFragments.peek() != null) {
                    prevFragments.add(currentFragment);
                    currentFragment = nextFragments.removeFirst();
                    manageFragment(currentFragment);
                    pageNum += 1;
                }

                Log.d(LOG_TAG, "pageNum: " + pageNum);
                Log.d(LOG_TAG, "Is health: " + visitCheckContainer.isHealthChecked()
                        + " is education: " + visitCheckContainer.isEducationChecked()
                        + " is social: " + visitCheckContainer.isSocialChecked());

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

    private void saveSession(byte pageNum) {
        SharedPreferences sharedPreferences = getSharedPreferences("questionSet" + pageNum, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        switch (pageNum) {
            case 1:
                saveFirstQuestionSet(editor);

                editor.apply();
                break;
            case 2:
                saveSecondQuestionSet(editor);

                editor.apply();
                break;
            case 3:
                saveThirdQuestionSet(editor);

                editor.apply();
                break;
            case 4:
                saveFourthQuestionSet(editor);

                editor.apply();
                break;
        }
    }

    private void saveFourthQuestionSet(SharedPreferences.Editor editor) {
        VisitFourthQuestionSetFragment fourthFragment = (VisitFourthQuestionSetFragment) currentFragment;

        EditText socialAdvice = fourthFragment.getEditTextAdvice();
        EditText socialAdvocacy = fourthFragment.getEditTextAdvocacy();
        EditText socialRef = fourthFragment.getEditTextRef();
        EditText socialEncouragement = fourthFragment.getEditTextEncouragement();
        EditText socialOutcome = fourthFragment.getEditTextSocialOutcome();

        editor.putBoolean(Constants.IS_SOCIAL_ADVICE_CHECKED, visitCheckContainer.isSocialAdviceChecked());
        editor.putBoolean(Constants.IS_SOCIAL_ADVOCACY_CHECKED, visitCheckContainer.isSocialAdvocacyChecked());
        editor.putBoolean(Constants.IS_SOCIAL_REF_CHECKED, visitCheckContainer.isSocialRefChecked());
        editor.putBoolean(Constants.IS_SOCIAL_ENCOURAGEMENT_CHECKED, visitCheckContainer.isSocialEncouragementChecked());

        editor.putString(Constants.SOCIAL_ADVICE_DESC, socialAdvice.getText().toString());
        editor.putString(Constants.SOCIAL_ADVOCACY_DESC, socialAdvocacy.getText().toString());
        editor.putString(Constants.SOCIAL_REF_DESC, socialRef.getText().toString());
        editor.putString(Constants.SOCIAL_ENCOURAGEMENT_DESC, socialEncouragement.getText().toString());
        editor.putString(Constants.SOCIAL_OUTCOME_DESC, socialOutcome.getText().toString());
        editor.putString(Constants.SOCIAL_GOAL_STATUS, visitCheckContainer.getSocialGoalStatus());
    }

    private void saveThirdQuestionSet(SharedPreferences.Editor editor) {
        VisitThirdQuestionSetFragment thirdFragment = (VisitThirdQuestionSetFragment) currentFragment;

        EditText educationAdvice = thirdFragment.getEditTextAdvice();
        EditText educationAdvocacy = thirdFragment.getEditTextAdvocacy();
        EditText educationRef = thirdFragment.getEditTextRef();
        EditText educationEncouragement = thirdFragment.getEditTextEncouragement();
        EditText educationOutcome = thirdFragment.getEditTextEducationOutcome();

        editor.putBoolean(Constants.IS_EDUCATION_ADVICE_CHECKED, visitCheckContainer.isEducationAdviceChecked());
        editor.putBoolean(Constants.IS_EDUCATION_ADVOCACY_CHECKED, visitCheckContainer.isEducationAdvocacyChecked());
        editor.putBoolean(Constants.IS_EDUCATION_REF_CHECKED, visitCheckContainer.isEducationRefChecked());
        editor.putBoolean(Constants.IS_EDUCATION_ENCOURAGEMENT_CHECKED, visitCheckContainer.isEducationEncouragementChecked());

        editor.putString(Constants.EDUCATION_ADVICE_DESC, educationAdvice.getText().toString());
        editor.putString(Constants.EDUCATION_ADVOCACY_DESC, educationAdvocacy.getText().toString());
        editor.putString(Constants.EDUCATION_REF_DESC, educationRef.getText().toString());
        editor.putString(Constants.EDUCATION_ENCOURAGEMENT_DESC, educationEncouragement.getText().toString());
        editor.putString(Constants.EDUCATION_OUTCOME_DESC, educationOutcome.getText().toString());
        editor.putString(Constants.EDUCATION_GOAL_STATUS, visitCheckContainer.getEducationGoalStatus());
    }

    private void saveSecondQuestionSet(SharedPreferences.Editor editor) {
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

        editor.putBoolean(Constants.IS_WHEEL_CHAIR_CHECKED_KEY, visitCheckContainer.isWheelChairChecked());
        editor.putBoolean(Constants.IS_PROSTHETIC_CHECKED_KEY, visitCheckContainer.isProstheticChecked());
        editor.putBoolean(Constants.IS_ORTHOTIC_CHECKED_KEY, visitCheckContainer.isOrthoticChecked());
        editor.putBoolean(Constants.IS_WR_CHECKED_KEY, visitCheckContainer.isWRChecked());
        editor.putBoolean(Constants.IS_REFERRAL_TO_HC_CHECKED_KEY, visitCheckContainer.isReferralToHCChecked());
        editor.putBoolean(Constants.IS_HEALTH_ADVICE_CHECKED_KEY, visitCheckContainer.isHealthAdviceChecked());
        editor.putBoolean(Constants.IS_HEALTH_ADVOCACY_CHECKED_KEY, visitCheckContainer.isHealthAdvocacyChecked());
        editor.putBoolean(Constants.IS_HEALTH_ENCOURAGEMENT_CHECKED_KEY, visitCheckContainer.isHealthEncouragementChecked());

        editor.putString(Constants.WHEEL_CHAIR_DESC_KEY, wheelChairDesc.getText().toString());
        editor.putString(Constants.PROSTHETIC_DESC_KEY, prostheticDesc.getText().toString());
        editor.putString(Constants.ORTHOTIC_DESC_KEY, orthoticDesc.getText().toString());
        editor.putString(Constants.WR_DESC_KEY, WRDesc.getText().toString());
        editor.putString(Constants.REFERRAL_TO_HC_DESC_KEY, referralToHCDesc.getText().toString());
        editor.putString(Constants.HEALTH_ADVICE_DESC_KEY, healthAdviceDesc.getText().toString());
        editor.putString(Constants.HEALTH_ADVOCACY_DESC_KEY, healthAdvocacyDesc.getText().toString());
        editor.putString(Constants.HEALTH_ENCOURAGEMENT_WR_DESC_KEY, healthEncouragementDesc.getText().toString());
        editor.putString(Constants.HEALTH_OUTCOME_DESC_KEY, healthOutcomeDesc.getText().toString());
        editor.putString(Constants.HEALTH_GOAL_STATUS, visitCheckContainer.getHealthGoalStatus());
    }

    private void saveFirstQuestionSet(SharedPreferences.Editor editor) {
        VisitFirstQuestionSetFragment firstFragment = (VisitFirstQuestionSetFragment) currentFragment;

        EditText dateOfVisit = firstFragment.getDate();
        EditText workerName = firstFragment.getCbrWorkerName();
        EditText locationOfVisit = firstFragment.getLocation();
        EditText villageNumber = firstFragment.getVillageNumber();

        editor.putString(Constants.PURPOSE_OF_VISIT_KEY, visitCheckContainer.getPurposeOfVisit());
        editor.putBoolean(Constants.IS_HEALTH_CHECKED_KEY, visitCheckContainer.isHealthChecked());
        editor.putBoolean(Constants.IS_EDUCATION_CHECKED_KEY, visitCheckContainer.isEducationChecked());
        editor.putBoolean(Constants.IS_SOCIAL_CHECKED_KEY, visitCheckContainer.isSocialChecked());
        editor.putString(Constants.DATE_OF_VISIT_KEY, dateOfVisit.getText().toString());
        editor.putString(Constants.NAME_OF_CBR_WORKER_KEY, workerName.getText().toString());
        editor.putString(Constants.LOCATION_OF_VISIT_KEY, locationOfVisit.getText().toString());
        editor.putString(Constants.SITE_LOCATION_KEY, visitCheckContainer.getSiteLocation());
        editor.putInt(Constants.SITE_LOCATION_SPINNER_SELECTED_POSITION_KEY, visitCheckContainer.getSiteLocationSpinnerSelectedPosition());
        editor.putString(Constants.VILLAGE_NUMBER_KEY, villageNumber.getText().toString());
    }

    private void manageFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameVisitQuestion, fragment);
        fragmentTransaction.commit();
    }
}