package com.example.cbr.fragments;

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

public class VisitSecondQuestionSet extends Fragment {

    private EditText editTextWheelChair;
    private EditText editTextProsthetic;
    private EditText editTextOrthotic;
    private EditText editTextWR;
    private EditText editTextReferralToHC;
    private EditText editTextAdvice;
    private EditText editTextAdvocacy;
    private EditText editTextEncouragement;
    private EditText editTextHealthOutcome;

    public VisitSecondQuestionSet() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View inflate = inflater.inflate(R.layout.fragment_visit_second_question_set, container, false);

        setupCheckBoxes(inflate);
        setupEditTexts(inflate);
        setupRadioGroup(inflate);

        return inflate;
    }

    private void setupRadioGroup(final View view) {
        RadioGroup goalStatus = view.findViewById(R.id.radioGroupGoalStatus);

        goalStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                TextView question10 = view.findViewById(R.id.textViewQ10);
                if (checkedId == R.id.radioButtonConcluded) {

                    question10.setVisibility(View.VISIBLE);
                    editTextHealthOutcome.setVisibility(View.VISIBLE);
                } else {
                    question10.setVisibility(View.GONE);
                    editTextHealthOutcome.setVisibility(View.GONE);
                }
            }
        });
    }

    private void setupEditTexts(View view) {
        editTextWheelChair = view.findViewById(R.id.editTextWheelchair);
        editTextProsthetic = view.findViewById(R.id.editTextProsthetic);
        editTextOrthotic = view.findViewById(R.id.editTextOrthotic);
        editTextWR = view.findViewById(R.id.editTextWR);
        editTextReferralToHC = view.findViewById(R.id.editTextReferralToHC);
        editTextAdvice = view.findViewById(R.id.editTextAdvice);
        editTextAdvocacy = view.findViewById(R.id.editTextAdvocacy);
        editTextEncouragement = view.findViewById(R.id.editTextEncouragement);
        editTextHealthOutcome = view.findViewById(R.id.editTextHealthOutcome);

        // TODO: 2021-02-11 save to db
    }

    private void setupCheckBoxes(final View view) {
        CheckBox checkBoxWheelchair = view.findViewById(R.id.checkBoxWheelchair);
        CheckBox checkBoxProsthetic = view.findViewById(R.id.checkBoxProsthetic);
        CheckBox checkBoxOrthotic = view.findViewById(R.id.checkBoxOrthotic);
        CheckBox checkBoxWR = view.findViewById(R.id.checkBoxWR);
        CheckBox checkBoxReferralToHC = view.findViewById(R.id.checkBoxReferralToHC);
        CheckBox checkBoxAdvice = view.findViewById(R.id.checkBoxAdvice);
        CheckBox checkBoxAdvocacy = view.findViewById(R.id.checkBoxAdvocacy);
        CheckBox checkBoxEncouragement = view.findViewById(R.id.checkBoxEncouragement);

        // TODO: 2021-02-11 save isChecked?
        checkBoxWheelchair.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextWheelChair.setVisibility(View.VISIBLE);
                } else {
                    editTextWheelChair.setVisibility(View.GONE);
                }
            }
        });
        checkBoxProsthetic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextProsthetic.setVisibility(View.VISIBLE);
                } else {
                    editTextProsthetic.setVisibility(View.GONE);
                }
            }
        });
        checkBoxOrthotic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextOrthotic.setVisibility(View.VISIBLE);
                } else {
                    editTextOrthotic.setVisibility(View.GONE);
                }
            }
        });
        checkBoxWR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextWR.setVisibility(View.VISIBLE);
                } else {
                    editTextWR.setVisibility(View.GONE);
                }
            }
        });
        checkBoxReferralToHC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextReferralToHC.setVisibility(View.VISIBLE);
                } else {
                    editTextReferralToHC.setVisibility(View.GONE);
                }
            }
        });
        checkBoxAdvice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextAdvice.setVisibility(View.VISIBLE);
                } else {
                    editTextAdvice.setVisibility(View.GONE);
                }
            }
        });
        checkBoxAdvocacy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextAdvocacy.setVisibility(View.VISIBLE);
                } else {
                    editTextAdvocacy.setVisibility(View.GONE);
                }
            }
        });
        checkBoxEncouragement.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextEncouragement.setVisibility(View.VISIBLE);
                } else {
                    editTextEncouragement.setVisibility(View.GONE);
                }
            }
        });
    }
}