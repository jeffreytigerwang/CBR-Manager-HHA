package com.example.cbr.fragments.newvisit;

import android.content.Context;
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
import com.example.cbr.databinding.FragmentVisitFourthQuestionSetBinding;
import com.example.cbr.models.VisitSocialQuestionSetData;

import static com.example.cbr.models.Constants.CANCELLED;
import static com.example.cbr.models.Constants.CONCLUDED;
import static com.example.cbr.models.Constants.ONGOING;

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
    private TextView question16;

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
        checkBoxEncouragement.setChecked(dataContainer.isSocialEncouragementChecked());
        toggleEditTextVisibility(dataContainer.isSocialEncouragementChecked(), editTextEncouragement);

        editTextAdvice.setText(dataContainer.getSocialAdviceDesc());
        editTextAdvocacy.setText(dataContainer.getSocialAdvocacyDesc());
        editTextEncouragement.setText(dataContainer.getSocialEncouragementDesc());
        editTextSocialOutcome.setText(dataContainer.getSocialOutcomeDesc());

        String goalStatus = dataContainer.getSocialGoalStatus();
        if (goalStatus.equalsIgnoreCase(CANCELLED)) {
            this.goalStatus.check(R.id.newVisit_healthCancelledRadioButton);
        } else if (goalStatus.equalsIgnoreCase(ONGOING)) {
            this.goalStatus.check(R.id.newVisit_healthOngoingRadioButton);
        } else if (goalStatus.equalsIgnoreCase(CONCLUDED)) {
            this.goalStatus.check(R.id.newVisit_healthConcludedRadioButton);
            question16.setVisibility(View.VISIBLE);
            editTextSocialOutcome.setVisibility(View.VISIBLE);
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

        question16 = binding.newVisitQ16TextView;
    }

    private void setupRadioGroup() {

        goalStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.newVisit_socialConcludedRadioButton) {
                    dataContainer.setSocialGoalStatus(CONCLUDED);
                    question16.setVisibility(View.VISIBLE);
                    editTextSocialOutcome.setVisibility(View.VISIBLE);
                } else {
                    question16.setVisibility(View.GONE);
                    editTextSocialOutcome.setVisibility(View.GONE);
                }
                if (checkedId == R.id.newVisit_socialCancelledRadioButton) {
                    dataContainer.setSocialGoalStatus(CANCELLED);
                }
                if (checkedId == R.id.newVisit_socialOngoingRadioButton) {
                    dataContainer.setSocialGoalStatus(ONGOING);
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
                dataContainer.setSocialRefChecked(isChecked);
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