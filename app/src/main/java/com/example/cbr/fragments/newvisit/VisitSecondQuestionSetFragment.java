package com.example.cbr.fragments.newvisit;

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
import com.example.cbr.databinding.FragmentVisitSecondQuestionSetBinding;
import com.example.cbr.models.Constants;
import com.example.cbr.models.VisitHealthQuestionSetData;

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

    private FragmentVisitSecondQuestionSetBinding binding;

    private final VisitHealthQuestionSetData dataContainer;

    private EditText editTextWheelChair;
    private EditText editTextProsthetic;
    private EditText editTextOrthotic;
    private EditText editTextWR;
    private EditText editTextReferralToHC;
    private EditText editTextAdvice;
    private EditText editTextAdvocacy;
    private EditText editTextEncouragement;
    private EditText editTextHealthOutcome;

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

    public VisitSecondQuestionSetFragment(VisitHealthQuestionSetData dataContainer) {
        this.dataContainer = dataContainer;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentVisitSecondQuestionSetBinding.inflate(inflater, container, false);

        preLoadViews();

        setupCheckBoxes();
        setupRadioGroup();

        return binding.getRoot();
    }

    private void preLoadViews() {
        findViews();

        checkBoxWheelchair.setChecked(dataContainer.isWheelChairChecked());
        toggleEditTextVisibility(dataContainer.isWheelChairChecked(), editTextWheelChair);
        checkBoxProsthetic.setChecked(dataContainer.isProstheticChecked());
        toggleEditTextVisibility(dataContainer.isProstheticChecked(), editTextProsthetic);
        checkBoxOrthotic.setChecked(dataContainer.isOrthoticChecked());
        toggleEditTextVisibility(dataContainer.isOrthoticChecked(), editTextOrthotic);
        checkBoxWR.setChecked(dataContainer.isWRChecked());
        toggleEditTextVisibility(dataContainer.isWRChecked(), editTextWR);
        checkBoxReferralToHC.setChecked(dataContainer.isReferralToHCChecked());
        toggleEditTextVisibility(dataContainer.isReferralToHCChecked(), editTextReferralToHC);
        checkBoxAdvice.setChecked(dataContainer.isReferralToHCChecked());
        toggleEditTextVisibility(dataContainer.isHealthAdviceChecked(), editTextAdvice);
        checkBoxAdvocacy.setChecked(dataContainer.isHealthAdvocacyChecked());
        toggleEditTextVisibility(dataContainer.isHealthAdvocacyChecked(), editTextAdvocacy);
        checkBoxEncouragement.setChecked(dataContainer.isHealthEncouragementChecked());
        toggleEditTextVisibility(dataContainer.isHealthEncouragementChecked(), editTextEncouragement);

        editTextWheelChair.setText(dataContainer.getWheelChairDesc());
        editTextProsthetic.setText(dataContainer.getProstheticDesc());
        editTextOrthotic.setText(dataContainer.getOrthoticDesc());
        editTextWR.setText(dataContainer.getWRDesc());
        editTextReferralToHC.setText(dataContainer.getReferralToHCDesc());
        editTextAdvice.setText(dataContainer.getHealthAdviceDesc());
        editTextAdvocacy.setText(dataContainer.getHealthAdvocacyDesc());
        editTextEncouragement.setText(dataContainer.getHealthEncouragementDesc());
        editTextHealthOutcome.setText(dataContainer.getHealthOutcomeDesc());

        String goalStatus = dataContainer.getHealthGoalStatus();
        if (goalStatus.equalsIgnoreCase(CANCELLED)) {
            this.goalStatus.check(R.id.newVisit_healthCancelledRadioButton);
        } else if (goalStatus.equalsIgnoreCase(ONGOING)) {
            this.goalStatus.check(R.id.newVisit_healthOngoingRadioButton);
        } else if (goalStatus.equalsIgnoreCase(CONCLUDED)) {
            this.goalStatus.check(R.id.newVisit_healthConcludedRadioButton);
            question10.setVisibility(View.VISIBLE);
            editTextHealthOutcome.setVisibility(View.VISIBLE);
        }
    }

    private void findViews() {
        editTextWheelChair = binding.newVisitHealthWheelchairEditText;
        editTextProsthetic = binding.newVisitHealthProstheticEditText;
        editTextOrthotic = binding.newVisitHealthOrthoticEditText;
        editTextWR = binding.newVisitHealthWREditText;
        editTextReferralToHC = binding.newVisitHealthReferralToHCEditText;
        editTextAdvice = binding.newVisitHealthAdviceEditText;
        editTextAdvocacy = binding.newVisitHealthAdvocacyEditText;
        editTextEncouragement = binding.newVisitHealthEncouragementEditText;
        editTextHealthOutcome = binding.newVisitHealthOutcomeEditText;

        goalStatus = binding.newVisitHealthGoalStatusRadioGroup;

        checkBoxWheelchair = binding.newVisitHealthWheelchairCheckBox;
        checkBoxProsthetic = binding.newVisitHealthProstheticCheckBox;
        checkBoxOrthotic = binding.newVisitHealthOrthoticCheckBox;
        checkBoxWR = binding.newVisitHealthWRCheckBox;
        checkBoxReferralToHC = binding.newVisitHealthReferralToHCCheckBox;
        checkBoxAdvice = binding.newVisitHealthAdviceCheckBox;
        checkBoxAdvocacy = binding.newVisitHealthAdvocacyCheckBox;
        checkBoxEncouragement = binding.newVisitHealthEncouragementCheckBox;

        question10 = binding.newVisitQ10TextView;
    }

    private void setupRadioGroup() {


        goalStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.newVisit_healthConcludedRadioButton) {
                    dataContainer.setHealthGoalStatus(CONCLUDED);

                    question10.setVisibility(View.VISIBLE);
                    editTextHealthOutcome.setVisibility(View.VISIBLE);
                } else {
                    question10.setVisibility(View.GONE);
                    editTextHealthOutcome.setVisibility(View.GONE);
                }
                if (checkedId == R.id.newVisit_healthOngoingRadioButton) {
                    dataContainer.setHealthGoalStatus(ONGOING);

                } else if (checkedId == R.id.newVisit_healthCancelledRadioButton) {
                    dataContainer.setHealthGoalStatus(CANCELLED);
                }
            }
        });
    }


    private void setupCheckBoxes() {

        checkBoxWheelchair.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dataContainer.setWheelChairChecked(isChecked);
                toggleEditTextVisibility(isChecked, editTextWheelChair);
            }
        });
        checkBoxProsthetic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dataContainer.setProstheticChecked(isChecked);
                toggleEditTextVisibility(isChecked, editTextProsthetic);
            }
        });
        checkBoxOrthotic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dataContainer.setOrthoticChecked(isChecked);
                toggleEditTextVisibility(isChecked, editTextOrthotic);
            }
        });
        checkBoxWR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dataContainer.setWRChecked(isChecked);
                toggleEditTextVisibility(isChecked, editTextWR);
            }
        });
        checkBoxReferralToHC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dataContainer.setReferralToHCChecked(isChecked);
                toggleEditTextVisibility(isChecked, editTextReferralToHC);
            }
        });
        checkBoxAdvice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dataContainer.setHealthAdviceChecked(isChecked);
                toggleEditTextVisibility(isChecked, editTextAdvice);
            }
        });
        checkBoxAdvocacy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dataContainer.setHealthAdvocacyChecked(isChecked);
                toggleEditTextVisibility(isChecked, editTextAdvocacy);
            }
        });
        checkBoxEncouragement.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dataContainer.setHealthEncouragementChecked(isChecked);
                toggleEditTextVisibility(isChecked, editTextEncouragement);
            }
        });
    }

    private void toggleEditTextVisibility(boolean isVisible, EditText editText) {
        if (isVisible) {
            editText.setVisibility(View.VISIBLE);
        } else {
            editText.setVisibility(View.GONE);
        }
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