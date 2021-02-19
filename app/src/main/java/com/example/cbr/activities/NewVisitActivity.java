    package com.example.cbr.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
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
import com.example.cbr.models.VisitEducationQuestionSetData;
import com.example.cbr.models.VisitHealthQuestionSetData;
import com.example.cbr.models.VisitGeneralQuestionSetData;
import com.example.cbr.models.VisitSocialQuestionSetData;

import java.util.LinkedList;
import java.util.Stack;

public class NewVisitActivity extends AppCompatActivity {

    private ActivityNewVisitBinding binding;

    private static final String CLIENT_ID = "clientID";
    private static final String LOG_TAG = "NewVisitActivity";

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

    public static Intent makeLaunchIntent(
            Context context,
            final long clientID) {
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

        Intent intent = getIntent();
        final long clientID = intent.getLongExtra(CLIENT_ID, -1);

        if (clientID != -1) {
            // TODO: 2021-02-09 get client info from DB
        } else {
            Log.d(LOG_TAG, "onCreate: failed to get client ID");
        }

        generalQuestionSetData = new VisitGeneralQuestionSetData();
        healthQuestionSetData = new VisitHealthQuestionSetData();
        educationQuestionSetData = new VisitEducationQuestionSetData();
        socialQuestionSetData = new VisitSocialQuestionSetData();

        currentFragment = new VisitFirstQuestionSetFragment(
                binding,
                generalQuestionSetData
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
    }

    private void setupRecordButton() {
        buttonRecord = binding.newVisitRecordButton;

        buttonRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2021-02-11 save data to db
                saveSession(pageNum);
                finish();
            }
        });
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
        buttonNext = binding.newVisitNextButton;
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSession(pageNum);

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

    private void saveSession(byte pageNum) {
        switch (pageNum) {
            case 1:
                saveFirstQuestionSet();
                break;
            case 2:
                saveSecondQuestionSetDesc();
                break;
            case 3:
                saveThirdQuestionSetDesc();
                break;
            case 4:
                saveFourthQuestionSetDesc();
                break;
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

        EditText dateOfVisit = firstFragment.getDate();
        EditText workerName = firstFragment.getCbrWorkerName();
        EditText locationOfVisit = firstFragment.getLocation();
        EditText villageNumberString = firstFragment.getVillageNumber();

        generalQuestionSetData.setDateOfVisit(dateOfVisit.getText().toString());
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
}