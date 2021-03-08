package com.example.cbr.fragments.newvisit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cbr.R;
import com.example.cbr.databinding.FragmentVisitThirdQuestionSetBinding;
import com.example.cbr.models.VisitEducationQuestionSetData;
import com.example.cbr.util.Constants;

/*
* Fragment class is displayed if CBR is checked in question 1. and education is checked for question 2.
* */

public class VisitThirdQuestionSetFragment extends Fragment {

    private FragmentVisitThirdQuestionSetBinding binding;

    private final VisitEducationQuestionSetData dataContainer;

    private EditText editTextAdvice;
    private EditText editTextAdvocacy;
    private EditText editTextRef;
    private EditText editTextEncouragement;
    private EditText editTextEducationOutcome;

    private CheckBox checkBoxEncouragement;
    private CheckBox checkBoxAdvice;
    private CheckBox checkBoxAdvocacy;
    private CheckBox checkBoxRef;
    private RadioGroup goalStatus;

    public VisitThirdQuestionSetFragment(VisitEducationQuestionSetData dataContainer) {
        this.dataContainer = dataContainer;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentVisitThirdQuestionSetBinding.inflate(inflater, container, false);

        preLoadViews();

        setupCheckBoxes();
        setupRadioGroup();

        return binding.getRoot();
    }

    private void preLoadViews() {
        findViews();

        checkBoxAdvice.setChecked(dataContainer.isEducationAdviceChecked());
        toggleEditTextVisibility(dataContainer.isEducationAdviceChecked(), editTextAdvice);
        checkBoxAdvocacy.setChecked(dataContainer.isEducationAdvocacyChecked());
        toggleEditTextVisibility(dataContainer.isEducationAdvocacyChecked(), editTextAdvocacy);
        checkBoxEncouragement.setChecked(dataContainer.isEducationEncouragementChecked());
        toggleEditTextVisibility(dataContainer.isEducationEncouragementChecked(), editTextEncouragement);

        editTextAdvice.setText(dataContainer.getEducationAdviceDesc());
        editTextAdvocacy.setText(dataContainer.getEducationAdvocacyDesc());
        editTextEncouragement.setText(dataContainer.getEducationEncouragementDesc());
        editTextEducationOutcome.setText(dataContainer.getEducationOutcomeDesc());

        String goalStatus = dataContainer.getEducationGoalStatus();
        if (goalStatus.equalsIgnoreCase(Constants.CANCELLED)) {
            this.goalStatus.check(R.id.newVisit_healthCancelledRadioButton);
        } else if (goalStatus.equalsIgnoreCase(Constants.ONGOING)) {
            this.goalStatus.check(R.id.newVisit_healthOngoingRadioButton);
        } else if (goalStatus.equalsIgnoreCase(Constants.CONCLUDED)) {
            this.goalStatus.check(R.id.newVisit_healthConcludedRadioButton);
        }
    }

    private void toggleEditTextVisibility(boolean isVisible, EditText editText) {
        if (isVisible) {
            editText.setVisibility(View.VISIBLE);
        } else {
            editText.setVisibility(View.GONE);
        }
    }

    private void findViews() {
        editTextAdvice = binding.newVisitEducationAdviceEditText;
        editTextAdvocacy = binding.newVisitEducationAdvocacyEditText;
        editTextRef = binding.newVisitEducationRefEditText;
        editTextEncouragement = binding.newVisitEducationEncouragementEditText;
        editTextEducationOutcome = binding.newVisitEducationOutcomeEditText;

        goalStatus = binding.newVisitEducationGoalStatusRadioGroup;

        checkBoxAdvice = binding.newVisitEducationAdviceCheckBox;
        checkBoxAdvocacy = binding.newVisitEducationAdvocacyCheckBox;
        checkBoxRef = binding.newVisitEducationRefcheckBox;
        checkBoxEncouragement = binding.newVisitEducationEncouragementCheckBox;
    }

    private void setupRadioGroup() {
        goalStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.newVisit_educationConcludedRadioButton) {
                    dataContainer.setEducationGoalStatus(Constants.CONCLUDED);

                } else if (checkedId == R.id.newVisit_educationCancelledRadioButton) {
                    dataContainer.setEducationGoalStatus(Constants.CANCELLED);

                } else if (checkedId == R.id.newVisit_educationOngoingRadioButton) {
                    dataContainer.setEducationGoalStatus(Constants.ONGOING);
                }
            }
        });
    }

    private void setupCheckBoxes() {
        checkBoxAdvice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dataContainer.setEducationAdviceChecked(isChecked);
                toggleEditTextVisibility(isChecked, editTextAdvice);
            }
        });
        checkBoxAdvocacy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dataContainer.setEducationAdvocacyChecked(isChecked);
                toggleEditTextVisibility(isChecked, editTextAdvocacy);
            }
        });
        checkBoxRef.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dataContainer.setEducationReferralChecked(isChecked);
                toggleEditTextVisibility(isChecked, editTextRef);
            }
        });
        checkBoxEncouragement.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dataContainer.setEducationEncouragementChecked(isChecked);
                toggleEditTextVisibility(isChecked, editTextEncouragement);
            }
        });
    }

    public EditText getEditTextAdvice() {
        return editTextAdvice;
    }

    public EditText getEditTextAdvocacy() {
        return editTextAdvocacy;
    }

    public EditText getEditTextRef() {
        return editTextRef;
    }

    public EditText getEditTextEncouragement() {
        return editTextEncouragement;
    }

    public EditText getEditTextEducationOutcome() {
        return editTextEducationOutcome;
    }
}