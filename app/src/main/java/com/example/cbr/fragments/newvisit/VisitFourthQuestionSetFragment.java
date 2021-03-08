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
import com.example.cbr.databinding.FragmentVisitFourthQuestionSetBinding;
import com.example.cbr.models.VisitSocialQuestionSetData;
import com.example.cbr.util.Constants;

/*
* Fragment class is displayed if CBR is checked in question 1. and social is checked for question 2.
* */

public class VisitFourthQuestionSetFragment extends Fragment {

    private FragmentVisitFourthQuestionSetBinding binding;

    private final VisitSocialQuestionSetData dataContainer;

    private EditText editTextAdvice;
    private EditText editTextAdvocacy;
    private EditText editTextRef;
    private EditText editTextEncouragement;
    private EditText editTextSocialOutcome;

    private RadioGroup goalStatus;
    private CheckBox checkBoxAdvice;
    private CheckBox checkBoxAdvocacy;
    private CheckBox checkBoxRef;
    private CheckBox checkBoxEncouragement;

    public VisitFourthQuestionSetFragment(VisitSocialQuestionSetData dataContainer) {
        this.dataContainer = dataContainer;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentVisitFourthQuestionSetBinding.inflate(inflater, container, false);

        preLoadViews();

        setupCheckBoxes();
        setupRadioGroup();

        return binding.getRoot();
    }

    private void preLoadViews() {
        findViews();

        checkBoxAdvice.setChecked(dataContainer.isSocialAdviceChecked());
        toggleEditTextVisibility(dataContainer.isSocialAdviceChecked(), editTextAdvice);
        checkBoxAdvocacy.setChecked(dataContainer.isSocialAdvocacyChecked());
        toggleEditTextVisibility(dataContainer.isSocialAdvocacyChecked(), editTextAdvocacy);
        checkBoxRef.setChecked(dataContainer.isSocialReferralChecked());
        toggleEditTextVisibility(dataContainer.isSocialReferralChecked(), editTextRef);
        checkBoxEncouragement.setChecked(dataContainer.isSocialEncouragementChecked());
        toggleEditTextVisibility(dataContainer.isSocialEncouragementChecked(), editTextEncouragement);

        editTextAdvice.setText(dataContainer.getSocialAdviceDesc());
        editTextAdvocacy.setText(dataContainer.getSocialAdvocacyDesc());
        editTextRef.setText(dataContainer.getSocialReferralDesc());
        editTextEncouragement.setText(dataContainer.getSocialEncouragementDesc());
        editTextSocialOutcome.setText(dataContainer.getSocialOutcomeDesc());

        String goalStatus = dataContainer.getSocialGoalStatus();
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
        editTextAdvice = binding.newVisitSocialAdviceEditText;
        editTextAdvocacy = binding.newVisitSocialAdvocacyEditText;
        editTextRef = binding.newVisitSocialRefEditText;
        editTextEncouragement = binding.newVisitSocialEncouragementEditText;
        editTextSocialOutcome = binding.newVisitSocialOutcomeEditText;

        goalStatus = binding.newVisitSocialGoalStatusRadioGroup;

        checkBoxAdvice = binding.newVisitSocialAdviceCheckBox;
        checkBoxAdvocacy = binding.newVisitSocialAdvocacyCheckBox;
        checkBoxRef = binding.newVisitSocialRefCheckBox;
        checkBoxEncouragement = binding.newVisitSocialEncouragementCheckBox;
    }

    private void setupRadioGroup() {

        goalStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.newVisit_socialConcludedRadioButton) {
                    dataContainer.setSocialGoalStatus(Constants.CONCLUDED);

                } else if (checkedId == R.id.newVisit_socialCancelledRadioButton) {
                    dataContainer.setSocialGoalStatus(Constants.CANCELLED);

                } else if (checkedId == R.id.newVisit_socialOngoingRadioButton) {
                    dataContainer.setSocialGoalStatus(Constants.ONGOING);
                }
            }
        });
    }

    private void setupCheckBoxes() {

        checkBoxAdvice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dataContainer.setSocialAdviceChecked(isChecked);
                toggleEditTextVisibility(isChecked, editTextAdvice);
            }
        });
        checkBoxAdvocacy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dataContainer.setSocialAdvocacyChecked(isChecked);
                toggleEditTextVisibility(isChecked, editTextAdvocacy);
            }
        });
        checkBoxRef.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dataContainer.setSocialReferralChecked(isChecked);
                toggleEditTextVisibility(isChecked, editTextRef);
            }
        });
        checkBoxEncouragement.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dataContainer.setSocialEncouragementChecked(isChecked);
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

    public EditText getEditTextSocialOutcome() {
        return editTextSocialOutcome;
    }
}