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
import com.example.cbr.models.VisitRecord;

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

    public VisitSecondQuestionSetFragment(VisitRecord visitRecord) {
        this.visitRecord = visitRecord;
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
        RadioGroup goalStatus = view.findViewById(R.id.radioGroupHealthGoalStatus);

        goalStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                TextView question10 = view.findViewById(R.id.textViewQ10);
                if (checkedId == R.id.radioButtonHealthConcluded) {

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
        editTextWheelChair = view.findViewById(R.id.editTextHealthWheelchair);
        editTextProsthetic = view.findViewById(R.id.editTextHealthProsthetic);
        editTextOrthotic = view.findViewById(R.id.editTextHealthOrthotic);
        editTextWR = view.findViewById(R.id.editTextHealthWR);
        editTextReferralToHC = view.findViewById(R.id.editTextHealthReferralToHC);
        editTextAdvice = view.findViewById(R.id.editTextHealthAdvice);
        editTextAdvocacy = view.findViewById(R.id.editTextHealthAdvocacy);
        editTextEncouragement = view.findViewById(R.id.editTextHealthEncouragement);
        editTextHealthOutcome = view.findViewById(R.id.editTextHealthOutcome);

        // TODO: 2021-02-11 save to db
    }

    private void setupCheckBoxes(final View view) {
        CheckBox checkBoxWheelchair = view.findViewById(R.id.checkBoxHealthWheelchair);
        CheckBox checkBoxProsthetic = view.findViewById(R.id.checkBoxHealthProsthetic);
        CheckBox checkBoxOrthotic = view.findViewById(R.id.checkBoxHealthOrthotic);
        CheckBox checkBoxWR = view.findViewById(R.id.checkBoxHealthWR);
        CheckBox checkBoxReferralToHC = view.findViewById(R.id.checkBoxHealthReferralToHC);
        CheckBox checkBoxAdvice = view.findViewById(R.id.checkBoxHealthAdvice);
        CheckBox checkBoxAdvocacy = view.findViewById(R.id.checkBoxHealthAdvocacy);
        CheckBox checkBoxEncouragement = view.findViewById(R.id.checkBoxHealthEncouragement);

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