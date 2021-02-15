package com.example.cbr.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cbr.R;
import com.example.cbr.models.Constants;
import com.example.cbr.models.VisitRecord;

import org.w3c.dom.Text;

import static com.example.cbr.models.Constants.CANCELLED;
import static com.example.cbr.models.Constants.CONCLUDED;
import static com.example.cbr.models.Constants.HEALTH_ADVICE_DESC_KEY;
import static com.example.cbr.models.Constants.HEALTH_ADVOCACY_DESC_KEY;
import static com.example.cbr.models.Constants.HEALTH_ENCOURAGEMENT_WR_DESC_KEY;
import static com.example.cbr.models.Constants.HEALTH_GOAL_STATUS;
import static com.example.cbr.models.Constants.HEALTH_OUTCOME_DESC_KEY;
import static com.example.cbr.models.Constants.IS_HEALTH_ADVICE_CHECKED_KEY;
import static com.example.cbr.models.Constants.IS_HEALTH_ADVOCACY_CHECKED_KEY;
import static com.example.cbr.models.Constants.IS_HEALTH_ENCOURAGEMENT_CHECKED_KEY;
import static com.example.cbr.models.Constants.IS_ORTHOTIC_CHECKED_KEY;
import static com.example.cbr.models.Constants.IS_PROSTHETIC_CHECKED_KEY;
import static com.example.cbr.models.Constants.IS_REFERRAL_TO_HC_CHECKED_KEY;
import static com.example.cbr.models.Constants.IS_WHEEL_CHAIR_CHECKED_KEY;
import static com.example.cbr.models.Constants.IS_WR_CHECKED_KEY;
import static com.example.cbr.models.Constants.ONGOING;
import static com.example.cbr.models.Constants.ORTHOTIC_DESC_KEY;
import static com.example.cbr.models.Constants.PROSTHETIC_DESC_KEY;
import static com.example.cbr.models.Constants.REFERRAL_TO_HC_DESC_KEY;
import static com.example.cbr.models.Constants.WHEEL_CHAIR_DESC_KEY;
import static com.example.cbr.models.Constants.WR_DESC_KEY;

public class VisitSecondQuestionSetFragment extends Fragment {

    private EditText editTextWheelChair;
    private EditText editTextProsthetic;
    private EditText editTextOrthotic;
    private EditText editTextWR;
    private EditText editTextReferralToHC;
    private EditText editTextAdvice;
    private EditText editTextAdvocacy;
    private EditText editTextEncouragement;
    private EditText editTextHealthOutcome;

    private final VisitRecord visitRecord;
    private final Context context;

    private RadioGroup goalStatus;
    private CheckBox checkBoxWheelchair;
    private CheckBox checkBoxProsthetic;
    private CheckBox checkBoxOrthotic;
    private CheckBox checkBoxWR;
    private CheckBox checkBoxReferralToHC;
    private CheckBox checkBoxAdvice;
    private CheckBox checkBoxAdvocacy;
    private CheckBox checkBoxEncouragement;
    private TextView question10;

    public VisitSecondQuestionSetFragment(VisitRecord visitRecord, Context context) {
        this.visitRecord = visitRecord;
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_visit_second_question_set, container, false);

        preLoadViews(view);

        setupCheckBoxes(view);
        setupRadioGroup(view);

        return view;
    }

    private void preLoadViews(View view) {
        findViews(view);

        SharedPreferences sharedPref = context.getSharedPreferences(Constants.QUESTION_SET_2_PREF_NAME, Context.MODE_PRIVATE);

        boolean isWheelchairChecked = sharedPref.getBoolean(IS_WHEEL_CHAIR_CHECKED_KEY, false);
        boolean isProstheticChecked = sharedPref.getBoolean(IS_PROSTHETIC_CHECKED_KEY, false);
        boolean isOrthoticChecked = sharedPref.getBoolean(IS_ORTHOTIC_CHECKED_KEY, false);
        boolean isWRChecked = sharedPref.getBoolean(IS_WR_CHECKED_KEY, false);
        boolean isReferralToHCChecked = sharedPref.getBoolean(IS_REFERRAL_TO_HC_CHECKED_KEY, false);
        boolean isAdviceChecked = sharedPref.getBoolean(IS_HEALTH_ADVICE_CHECKED_KEY, false);
        boolean isAdvocacyChecked = sharedPref.getBoolean(IS_HEALTH_ADVOCACY_CHECKED_KEY, false);
        boolean isEncouragementChecked = sharedPref.getBoolean(IS_HEALTH_ENCOURAGEMENT_CHECKED_KEY, false);
        String wheelchairDesc = sharedPref.getString(WHEEL_CHAIR_DESC_KEY, "");
        String prostheticDesc = sharedPref.getString(PROSTHETIC_DESC_KEY, "");
        String orthoticDesc = sharedPref.getString(ORTHOTIC_DESC_KEY, "");
        String WRDesc = sharedPref.getString(WR_DESC_KEY, "");
        String referralToHCDesc = sharedPref.getString(REFERRAL_TO_HC_DESC_KEY, "");
        String adviceDesc = sharedPref.getString(HEALTH_ADVICE_DESC_KEY, "");
        String advocacyDesc = sharedPref.getString(HEALTH_ADVOCACY_DESC_KEY, "");
        String encouragementDesc = sharedPref.getString(HEALTH_ENCOURAGEMENT_WR_DESC_KEY, "");
        String healthOutcomeDesc = sharedPref.getString(HEALTH_OUTCOME_DESC_KEY, "");
        String  goalStatus = sharedPref.getString(HEALTH_GOAL_STATUS, "");

        checkBoxWheelchair.setChecked(isWheelchairChecked);
        loadEditTextVisibility(isWheelchairChecked, editTextWheelChair);
        checkBoxProsthetic.setChecked(isProstheticChecked);
        loadEditTextVisibility(isProstheticChecked, editTextProsthetic);
        checkBoxOrthotic.setChecked(isOrthoticChecked);
        loadEditTextVisibility(isOrthoticChecked, editTextOrthotic);
        checkBoxWR.setChecked(isWRChecked);
        loadEditTextVisibility(isWRChecked, editTextWR);
        checkBoxReferralToHC.setChecked(isReferralToHCChecked);
        loadEditTextVisibility(isReferralToHCChecked, editTextReferralToHC);
        checkBoxAdvice.setChecked(isAdviceChecked);
        loadEditTextVisibility(isAdviceChecked, editTextAdvice);
        checkBoxAdvocacy.setChecked(isAdvocacyChecked);
        loadEditTextVisibility(isAdvocacyChecked, editTextAdvocacy);
        checkBoxEncouragement.setChecked(isEncouragementChecked);
        loadEditTextVisibility(isEncouragementChecked, editTextEncouragement);

        editTextWheelChair.setText(wheelchairDesc);
        editTextProsthetic.setText(prostheticDesc);
        editTextOrthotic.setText(orthoticDesc);
        editTextWR.setText(WRDesc);
        editTextReferralToHC.setText(referralToHCDesc);
        editTextAdvice.setText(adviceDesc);
        editTextAdvocacy.setText(advocacyDesc);
        editTextEncouragement.setText(encouragementDesc);
        editTextHealthOutcome.setText(healthOutcomeDesc);

        if (goalStatus.equalsIgnoreCase(CANCELLED)) {
            this.goalStatus.check(R.id.radioButtonHealthCancelled);
        } else if (goalStatus.equalsIgnoreCase(ONGOING)) {
            this.goalStatus.check(R.id.radioButtonHealthOngoing);
        } else if (goalStatus.equalsIgnoreCase(CONCLUDED)) {
            this.goalStatus.check(R.id.radioButtonHealthConcluded);
            question10.setVisibility(View.VISIBLE);
            editTextHealthOutcome.setVisibility(View.VISIBLE);
        }
    }

    private void loadEditTextVisibility(boolean isChecked, EditText editText) {
        if (isChecked) {
            editText.setVisibility(View.VISIBLE);
        }
    }

    private void findViews(View view) {
        editTextWheelChair = view.findViewById(R.id.editTextHealthWheelchair);
        editTextProsthetic = view.findViewById(R.id.editTextHealthProsthetic);
        editTextOrthotic = view.findViewById(R.id.editTextHealthOrthotic);
        editTextWR = view.findViewById(R.id.editTextHealthWR);
        editTextReferralToHC = view.findViewById(R.id.editTextHealthReferralToHC);
        editTextAdvice = view.findViewById(R.id.editTextHealthAdvice);
        editTextAdvocacy = view.findViewById(R.id.editTextHealthAdvocacy);
        editTextEncouragement = view.findViewById(R.id.editTextHealthEncouragement);
        editTextHealthOutcome = view.findViewById(R.id.editTextHealthOutcome);

        goalStatus = view.findViewById(R.id.radioGroupHealthGoalStatus);

        checkBoxWheelchair = view.findViewById(R.id.checkBoxHealthWheelchair);
        checkBoxProsthetic = view.findViewById(R.id.checkBoxHealthProsthetic);
        checkBoxOrthotic = view.findViewById(R.id.checkBoxHealthOrthotic);
        checkBoxWR = view.findViewById(R.id.checkBoxHealthWR);
        checkBoxReferralToHC = view.findViewById(R.id.checkBoxHealthReferralToHC);
        checkBoxAdvice = view.findViewById(R.id.checkBoxHealthAdvice);
        checkBoxAdvocacy = view.findViewById(R.id.checkBoxHealthAdvocacy);
        checkBoxEncouragement = view.findViewById(R.id.checkBoxHealthEncouragement);

        question10 = view.findViewById(R.id.textViewQ10);
    }

    private void setupRadioGroup(final View view) {


        goalStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                TextView question10 = view.findViewById(R.id.textViewQ10);

                if (checkedId == R.id.radioButtonHealthConcluded) {
                    visitRecord.setHealthGoalStatus(CONCLUDED);

                    question10.setVisibility(View.VISIBLE);
                    editTextHealthOutcome.setVisibility(View.VISIBLE);
                } else {
                    question10.setVisibility(View.GONE);
                    editTextHealthOutcome.setVisibility(View.GONE);
                }
                if (checkedId == R.id.radioButtonHealthOngoing) {
                    visitRecord.setHealthGoalStatus(ONGOING);

                } else if (checkedId == R.id.radioButtonHealthCancelled) {
                    visitRecord.setHealthGoalStatus(CANCELLED);
                }
            }
        });
    }


    private void setupCheckBoxes(final View view) {

        checkBoxWheelchair.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextWheelChair.setVisibility(View.VISIBLE);
                    visitRecord.setWheelChairChecked(true);
                } else {
                    editTextWheelChair.setVisibility(View.GONE);
                    visitRecord.setWheelChairChecked(false);
                }
            }
        });
        checkBoxProsthetic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextProsthetic.setVisibility(View.VISIBLE);
                    visitRecord.setProstheticChecked(true);
                } else {
                    editTextProsthetic.setVisibility(View.GONE);
                    visitRecord.setProstheticChecked(false);
                }
            }
        });
        checkBoxOrthotic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextOrthotic.setVisibility(View.VISIBLE);
                    visitRecord.setOrthoticChecked(true);
                } else {
                    editTextOrthotic.setVisibility(View.GONE);
                    visitRecord.setOrthoticChecked(false);
                }
            }
        });
        checkBoxWR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextWR.setVisibility(View.VISIBLE);
                    visitRecord.setWRChecked(true);
                } else {
                    editTextWR.setVisibility(View.GONE);
                    visitRecord.setWRChecked(false);
                }
            }
        });
        checkBoxReferralToHC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextReferralToHC.setVisibility(View.VISIBLE);
                    visitRecord.setReferralToHCChecked(true);
                } else {
                    editTextReferralToHC.setVisibility(View.GONE);
                    visitRecord.setReferralToHCChecked(false);
                }
            }
        });
        checkBoxAdvice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextAdvice.setVisibility(View.VISIBLE);
                    visitRecord.setHealthAdviceChecked(true);
                } else {
                    editTextAdvice.setVisibility(View.GONE);
                    visitRecord.setHealthAdviceChecked(false);
                }
            }
        });
        checkBoxAdvocacy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextAdvocacy.setVisibility(View.VISIBLE);
                    visitRecord.setHealthAdvocacyChecked(true);
                } else {
                    editTextAdvocacy.setVisibility(View.GONE);
                    visitRecord.setHealthAdvocacyChecked(false);
                }
            }
        });
        checkBoxEncouragement.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextEncouragement.setVisibility(View.VISIBLE);
                    visitRecord.setWheelChairChecked(true);
                } else {
                    editTextEncouragement.setVisibility(View.GONE);
                    visitRecord.setWheelChairChecked(false);
                }
            }
        });
    }

    public EditText getEditTextWheelChair() {
        return editTextWheelChair;
    }

    public EditText getEditTextProsthetic() {
        return editTextProsthetic;
    }

    public EditText getEditTextOrthotic() {
        return editTextOrthotic;
    }

    public EditText getEditTextWR() {
        return editTextWR;
    }

    public EditText getEditTextReferralToHC() {
        return editTextReferralToHC;
    }

    public EditText getEditTextAdvice() {
        return editTextAdvice;
    }

    public EditText getEditTextAdvocacy() {
        return editTextAdvocacy;
    }

    public EditText getEditTextEncouragement() {
        return editTextEncouragement;
    }

    public EditText getEditTextHealthOutcome() {
        return editTextHealthOutcome;
    }
}