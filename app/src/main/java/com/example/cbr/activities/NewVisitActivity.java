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
import com.example.cbr.models.VisitRecord;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import static com.example.cbr.models.Constants.DATE_OF_VISIT_KEY;
import static com.example.cbr.models.Constants.HEALTH_ADVICE_DESC_KEY;
import static com.example.cbr.models.Constants.HEALTH_ADVOCACY_DESC_KEY;
import static com.example.cbr.models.Constants.HEALTH_ENCOURAGEMENT_WR_DESC_KEY;
import static com.example.cbr.models.Constants.HEALTH_OUTCOME_DESC_KEY;
import static com.example.cbr.models.Constants.IS_EDUCATION_CHECKED_KEY;
import static com.example.cbr.models.Constants.IS_HEALTH_ADVICE_CHECKED_KEY;
import static com.example.cbr.models.Constants.IS_HEALTH_ADVOCACY_CHECKED_KEY;
import static com.example.cbr.models.Constants.IS_HEALTH_CHECKED_KEY;
import static com.example.cbr.models.Constants.IS_HEALTH_ENCOURAGEMENT_CHECKED_KEY;
import static com.example.cbr.models.Constants.IS_ORTHOTIC_CHECKED_KEY;
import static com.example.cbr.models.Constants.IS_PROSTHETIC_CHECKED_KEY;
import static com.example.cbr.models.Constants.IS_REFERRAL_TO_HC_CHECKED_KEY;
import static com.example.cbr.models.Constants.IS_SOCIAL_CHECKED_KEY;
import static com.example.cbr.models.Constants.IS_WHEEL_CHAIR_CHECKED_KEY;
import static com.example.cbr.models.Constants.IS_WR_CHECKED_KEY;
import static com.example.cbr.models.Constants.LOCATION_OF_VISIT_KEY;
import static com.example.cbr.models.Constants.NAME_OF_CBR_WORKER_KEY;
import static com.example.cbr.models.Constants.ORTHOTIC_DESC_KEY;
import static com.example.cbr.models.Constants.PROSTHETIC_DESC_KEY;
import static com.example.cbr.models.Constants.PURPOSE_OF_VISIT_KEY;
import static com.example.cbr.models.Constants.REFERRAL_TO_HC_DESC_KEY;
import static com.example.cbr.models.Constants.SITE_LOCATION_KEY;
import static com.example.cbr.models.Constants.VILLAGE_NUMBER_KEY;
import static com.example.cbr.models.Constants.WHEEL_CHAIR_DESC_KEY;
import static com.example.cbr.models.Constants.WR_DESC_KEY;

public class NewVisitActivity extends AppCompatActivity {

    private static final String VISIT_RECORD = "visitRecord";
    private static final String CLIENT_ID = "clientID";
    private static final String LOG_TAG = "NewVisitActivity";

    private Fragment currentFragment;
    private VisitRecord visitRecord;
    private Queue<Fragment> nextFragments;
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

    private void setupRecordButton() {
        buttonRecord = findViewById(R.id.buttonVisitRecord);

        // TODO: 2021-02-11 save data to db
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
                saveQuestionSetData();

                nextFragments.add(currentFragment);
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
                saveQuestionSetData();

                if (pageNum == 1) {
                    prevFragments.clear();
                    nextFragments.clear();

                    totalFragments = 1;

                    if (visitRecord.isHealthChecked()) {
                        nextFragments.offer(new VisitSecondQuestionSetFragment(visitRecord, NewVisitActivity.this));
                        totalFragments += 1;
                    }
                    if (visitRecord.isEducationChecked()) {
                        nextFragments.offer(new VisitThirdQuestionSetFragment(visitRecord));
                        totalFragments += 1;
                    }
                    if (visitRecord.isSocialChecked()) {
                        nextFragments.offer(new VisitFourthQuestionSetFragment(visitRecord));
                        totalFragments += 1;
                    }
                }

                if (nextFragments.peek() != null) {
                    prevFragments.add(currentFragment);
                    currentFragment = nextFragments.remove();
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

    private void saveQuestionSetData() {
        SharedPreferences sharedPreferences = getSharedPreferences("questionSet" + pageNum, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        switch (pageNum) {
            case 1:
                VisitFirstQuestionSetFragment firstFragment = (VisitFirstQuestionSetFragment) currentFragment;
                EditText dateOfVisit = firstFragment.getDate();
                EditText workerName = firstFragment.getCbrWorkerName();
                EditText locationOfVisit = firstFragment.getLocation();
                EditText villageNumber = firstFragment.getVillageNumber();

                editor.putString(PURPOSE_OF_VISIT_KEY, visitRecord.getPurposeOfVisit());
                editor.putBoolean(IS_HEALTH_CHECKED_KEY, visitRecord.isHealthChecked());
                editor.putBoolean(IS_EDUCATION_CHECKED_KEY, visitRecord.isEducationChecked());
                editor.putBoolean(IS_SOCIAL_CHECKED_KEY, visitRecord.isSocialChecked());
                editor.putString(DATE_OF_VISIT_KEY, dateOfVisit.getText().toString());
                editor.putString(NAME_OF_CBR_WORKER_KEY, workerName.getText().toString());
                editor.putString(LOCATION_OF_VISIT_KEY, locationOfVisit.getText().toString());
                editor.putString(SITE_LOCATION_KEY, visitRecord.getSiteLocation());
                editor.putString(VILLAGE_NUMBER_KEY, villageNumber.getText().toString());

                Log.d(LOG_TAG, "workerName: " + workerName.getText().toString());

//                editor.putString("purposeOfVisit", visitRecord.getPurposeOfVisit());
//                editor.putBoolean("isHealthChecked", visitRecord.isHealthChecked());
//                editor.putBoolean("isEducationChecked", visitRecord.isEducationChecked());
//                editor.putBoolean("isSocialChecked", visitRecord.isSocialChecked());
//                editor.putString("dateOfVisit", visitRecord.getDateOfVisit().toString());
//                editor.putString("nameOfCBRWorker", visitRecord.getNameOfCBRWorker());
//                editor.putString("locationOfVisit", visitRecord.getLocationOfVisit());
//                editor.putString("siteLocation", visitRecord.getSiteLocation());
//                editor.putInt("villageNumber", visitRecord.getVillageNumber());
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

                editor.putBoolean(IS_WHEEL_CHAIR_CHECKED_KEY, visitRecord.isWheelChairChecked());
                editor.putBoolean(IS_PROSTHETIC_CHECKED_KEY, visitRecord.isProstheticChecked());
                editor.putBoolean(IS_ORTHOTIC_CHECKED_KEY, visitRecord.isOrthoticChecked());
                editor.putBoolean(IS_WR_CHECKED_KEY, visitRecord.isWRChecked());
                editor.putBoolean(IS_REFERRAL_TO_HC_CHECKED_KEY, visitRecord.isReferralToHCChecked());
                editor.putBoolean(IS_HEALTH_ADVICE_CHECKED_KEY, visitRecord.isHealthAdviceChecked());
                editor.putBoolean(IS_HEALTH_ADVOCACY_CHECKED_KEY, visitRecord.isHealthAdvocacyChecked());
                editor.putBoolean(IS_HEALTH_ENCOURAGEMENT_CHECKED_KEY, visitRecord.isHealthEncouragementChecked());

                editor.putString(WHEEL_CHAIR_DESC_KEY, wheelChairDesc.getText().toString());
                editor.putString(PROSTHETIC_DESC_KEY, prostheticDesc.getText().toString());
                editor.putString(ORTHOTIC_DESC_KEY, orthoticDesc.getText().toString());
                editor.putString(WR_DESC_KEY, WRDesc.getText().toString());
                editor.putString(REFERRAL_TO_HC_DESC_KEY, referralToHCDesc.getText().toString());
                editor.putString(HEALTH_ADVICE_DESC_KEY, healthAdviceDesc.getText().toString());
                editor.putString(HEALTH_ADVOCACY_DESC_KEY, healthAdvocacyDesc.getText().toString());
                editor.putString(HEALTH_ENCOURAGEMENT_WR_DESC_KEY, healthEncouragementDesc.getText().toString());
                editor.putString(HEALTH_OUTCOME_DESC_KEY, healthOutcomeDesc.getText().toString());

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