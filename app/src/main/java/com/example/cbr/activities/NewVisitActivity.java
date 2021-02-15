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
import com.example.cbr.fragments.VisitFirstQuestionSetFragment;
import com.example.cbr.fragments.VisitFourthQuestionSetFragment;
import com.example.cbr.fragments.VisitSecondQuestionSetFragment;
import com.example.cbr.fragments.VisitThirdQuestionSetFragment;
import com.example.cbr.models.Constants;
import com.example.cbr.models.VisitRecord;

import java.util.LinkedList;
import java.util.Stack;

public class NewVisitActivity extends AppCompatActivity {

    private static final String VISIT_RECORD = "visitRecord";
    private static final String CLIENT_ID = "clientID";
    private static final String LOG_TAG = "NewVisitActivity";

    private Fragment currentFragment;
    private VisitRecord visitRecord;
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
            final VisitRecord visitRecord) {
        Intent intent = new Intent(context, NewVisitActivity.class);
        intent.putExtra(CLIENT_ID, clientID);
        intent.putExtra(VISIT_RECORD, visitRecord);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_visit);

        Intent intent = getIntent();
        final long clientID = intent.getLongExtra(CLIENT_ID, -1);
        visitRecord = (VisitRecord) intent.getSerializableExtra(VISIT_RECORD);

        if (clientID != -1) {
            // TODO: 2021-02-09 get client info from DB
        } else {
            Log.d(LOG_TAG, "onCreate: failed to get client ID");
        }

        currentFragment = new VisitFirstQuestionSetFragment(visitRecord);
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

        removeSavedSession();
    }

    private void setupRecordButton() {
        buttonRecord = findViewById(R.id.buttonVisitRecord);
        // TODO: 2021-02-11 save data to db

        removeSavedSession();

        buttonRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        buttonBack = findViewById(R.id.buttonVisitBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pageNum-1 == 1) {
                    Log.d(LOG_TAG, "pagNum-1: " + (pageNum-1));
                    buttonBack.setVisibility(View.GONE);
                }
                saveQuestionSetData(pageNum);
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
        buttonNext = findViewById(R.id.buttonVisitNext);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveQuestionSetData(pageNum);

                if (pageNum == 1) {
                    prevFragments.clear();
                    nextFragments.clear();

                    totalFragments = 1;

                    if (visitRecord.isHealthChecked()) {
                        nextFragments.offer(new VisitSecondQuestionSetFragment(visitRecord, NewVisitActivity.this));
                        totalFragments += 1;
                    }
                    if (visitRecord.isEducationChecked()) {
                        nextFragments.offer(new VisitThirdQuestionSetFragment(visitRecord, NewVisitActivity.this));
                        totalFragments += 1;
                    }
                    if (visitRecord.isSocialChecked()) {
                        nextFragments.offer(new VisitFourthQuestionSetFragment(visitRecord, NewVisitActivity.this));
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
                Log.d(LOG_TAG, "Is health: " + visitRecord.isHealthChecked()
                        + " is education: " + visitRecord.isEducationChecked()
                        + " is social: " + visitRecord.isSocialChecked());

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

    private void saveQuestionSetData(byte pageNum) {
        SharedPreferences sharedPreferences = getSharedPreferences("questionSet" + pageNum, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        switch (pageNum) {
            case 1:
                VisitFirstQuestionSetFragment firstFragment = (VisitFirstQuestionSetFragment) currentFragment;
                EditText dateOfVisit = firstFragment.getDate();
                EditText workerName = firstFragment.getCbrWorkerName();
                EditText locationOfVisit = firstFragment.getLocation();
                EditText villageNumber = firstFragment.getVillageNumber();

                editor.putString(Constants.PURPOSE_OF_VISIT_KEY, visitRecord.getPurposeOfVisit());
                editor.putBoolean(Constants.IS_HEALTH_CHECKED_KEY, visitRecord.isHealthChecked());
                editor.putBoolean(Constants.IS_EDUCATION_CHECKED_KEY, visitRecord.isEducationChecked());
                editor.putBoolean(Constants.IS_SOCIAL_CHECKED_KEY, visitRecord.isSocialChecked());
                editor.putString(Constants.DATE_OF_VISIT_KEY, dateOfVisit.getText().toString());
                editor.putString(Constants.NAME_OF_CBR_WORKER_KEY, workerName.getText().toString());
                editor.putString(Constants.LOCATION_OF_VISIT_KEY, locationOfVisit.getText().toString());
                editor.putString(Constants.SITE_LOCATION_KEY, visitRecord.getSiteLocation());
                editor.putString(Constants.VILLAGE_NUMBER_KEY, villageNumber.getText().toString());

                Log.d(LOG_TAG, "workerName: " + workerName.getText().toString());
                editor.apply();
                break;
            case 2:
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

                editor.putBoolean(Constants.IS_WHEEL_CHAIR_CHECKED_KEY, visitRecord.isWheelChairChecked());
                editor.putBoolean(Constants.IS_PROSTHETIC_CHECKED_KEY, visitRecord.isProstheticChecked());
                editor.putBoolean(Constants.IS_ORTHOTIC_CHECKED_KEY, visitRecord.isOrthoticChecked());
                editor.putBoolean(Constants.IS_WR_CHECKED_KEY, visitRecord.isWRChecked());
                editor.putBoolean(Constants.IS_REFERRAL_TO_HC_CHECKED_KEY, visitRecord.isReferralToHCChecked());
                editor.putBoolean(Constants.IS_HEALTH_ADVICE_CHECKED_KEY, visitRecord.isHealthAdviceChecked());
                editor.putBoolean(Constants.IS_HEALTH_ADVOCACY_CHECKED_KEY, visitRecord.isHealthAdvocacyChecked());
                editor.putBoolean(Constants.IS_HEALTH_ENCOURAGEMENT_CHECKED_KEY, visitRecord.isHealthEncouragementChecked());

                editor.putString(Constants.WHEEL_CHAIR_DESC_KEY, wheelChairDesc.getText().toString());
                editor.putString(Constants.PROSTHETIC_DESC_KEY, prostheticDesc.getText().toString());
                editor.putString(Constants.ORTHOTIC_DESC_KEY, orthoticDesc.getText().toString());
                editor.putString(Constants.WR_DESC_KEY, WRDesc.getText().toString());
                editor.putString(Constants.REFERRAL_TO_HC_DESC_KEY, referralToHCDesc.getText().toString());
                editor.putString(Constants.HEALTH_ADVICE_DESC_KEY, healthAdviceDesc.getText().toString());
                editor.putString(Constants.HEALTH_ADVOCACY_DESC_KEY, healthAdvocacyDesc.getText().toString());
                editor.putString(Constants.HEALTH_ENCOURAGEMENT_WR_DESC_KEY, healthEncouragementDesc.getText().toString());
                editor.putString(Constants.HEALTH_OUTCOME_DESC_KEY, healthOutcomeDesc.getText().toString());
                editor.putString(Constants.HEALTH_GOAL_STATUS, visitRecord.getHealthGoalStatus());

                editor.apply();
                break;
            case 3:
                VisitThirdQuestionSetFragment thirdFragment = (VisitThirdQuestionSetFragment) currentFragment;
                EditText educationAdvice = thirdFragment.getEditTextAdvice();
                EditText educationAdvocacy = thirdFragment.getEditTextAdvocacy();
                EditText educationRef = thirdFragment.getEditTextRef();
                EditText educationEncouragement = thirdFragment.getEditTextEncouragement();
                EditText educationOutcome = thirdFragment.getEditTextEducationOutcome();

                editor.putBoolean(Constants.IS_EDUCATION_ADVICE_CHECKED, visitRecord.isEducationAdviceChecked());
                editor.putBoolean(Constants.IS_EDUCATION_ADVOCACY_CHECKED, visitRecord.isEducationAdvocacyChecked());
                editor.putBoolean(Constants.IS_EDUCATION_REF_CHECKED, visitRecord.isEducationRefChecked());
                editor.putBoolean(Constants.IS_EDUCATION_ENCOURAGEMENT_CHECKED, visitRecord.isEducationEncouragementChecked());

                editor.putString(Constants.EDUCATION_ADVICE_DESC, educationAdvice.getText().toString());
                editor.putString(Constants.EDUCATION_ADVOCACY_DESC, educationAdvocacy.getText().toString());
                editor.putString(Constants.EDUCATION_REF_DESC, educationRef.getText().toString());
                editor.putString(Constants.EDUCATION_ENCOURAGEMENT_DESC, educationEncouragement.getText().toString());
                editor.putString(Constants.EDUCATION_OUTCOME_DESC, educationOutcome.getText().toString());
                editor.putString(Constants.EDUCATION_GOAL_STATUS, visitRecord.getEducationGoalStatus());

                editor.apply();
                break;
            case 4:
                VisitFourthQuestionSetFragment fourthFragment = (VisitFourthQuestionSetFragment) currentFragment;
                EditText socialAdvice = fourthFragment.getEditTextAdvice();
                EditText socialAdvocacy = fourthFragment.getEditTextAdvocacy();
                EditText socialRef = fourthFragment.getEditTextRef();
                EditText socialEncouragement = fourthFragment.getEditTextEncouragement();
                EditText socialOutcome = fourthFragment.getEditTextSocialOutcome();

                editor.putBoolean(Constants.IS_SOCIAL_ADVICE_CHECKED, visitRecord.isSocialAdviceChecked());
                editor.putBoolean(Constants.IS_SOCIAL_ADVOCACY_CHECKED, visitRecord.isSocialAdvocacyChecked());
                editor.putBoolean(Constants.IS_SOCIAL_REF_CHECKED, visitRecord.isSocialRefChecked());
                editor.putBoolean(Constants.IS_SOCIAL_ENCOURAGEMENT_CHECKED, visitRecord.isSocialEncouragementChecked());

                editor.putString(Constants.SOCIAL_ADVICE_DESC, socialAdvice.getText().toString());
                editor.putString(Constants.SOCIAL_ADVOCACY_DESC, socialAdvocacy.getText().toString());
                editor.putString(Constants.SOCIAL_REF_DESC, socialRef.getText().toString());
                editor.putString(Constants.SOCIAL_ENCOURAGEMENT_DESC, socialEncouragement.getText().toString());
                editor.putString(Constants.SOCIAL_OUTCOME_DESC, socialOutcome.getText().toString());
                editor.putString(Constants.SOCIAL_GOAL_STATUS, visitRecord.getSocialGoalStatus());

                editor.apply();
                break;
        }
    }

    private void manageFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameVisitQuestion, fragment);
        fragmentTransaction.commit();
    }
}