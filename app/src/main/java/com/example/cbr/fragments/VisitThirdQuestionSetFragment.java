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

public class VisitThirdQuestionSetFragment extends Fragment {

    private EditText editTextAdvice;
    private EditText editTextAdvocacy;
    private EditText editTextRef;
    private EditText editTextEncouragement;
    private EditText editTextEducationOutcome;

    private final VisitRecord visitRecord;

    public VisitThirdQuestionSetFragment(VisitRecord visitRecord) {
        this.visitRecord = visitRecord;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View inflate = inflater.inflate(R.layout.fragment_visit_third_question_set, container, false);

        setupCheckBoxes(inflate);
        setupEditTexts(inflate);
        setupRadioGroup(inflate);

        return inflate;
    }

    private void setupRadioGroup(final View view) {
        RadioGroup goalStatus = view.findViewById(R.id.radioGroupEducationGoalStatus);

        goalStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                TextView question13 = view.findViewById(R.id.textViewQ16);
                if (checkedId == R.id.radioButtonEducationConcluded) {

                    question13.setVisibility(View.VISIBLE);
                    editTextEducationOutcome.setVisibility(View.VISIBLE);
                } else {
                    question13.setVisibility(View.GONE);
                    editTextEducationOutcome.setVisibility(View.GONE);
                }
            }
        });
    }

    private void setupEditTexts(View view) {
        editTextAdvice = view.findViewById(R.id.editTextEducationAdvice);
        editTextAdvocacy = view.findViewById(R.id.editTextEducationAdvocacy);
        editTextRef = view.findViewById(R.id.editTextEducationRef);
        editTextEncouragement = view.findViewById(R.id.editTextEducationEncouragement);
        editTextEducationOutcome = view.findViewById(R.id.editTextEducationOutcome);
    }

    private void setupCheckBoxes(View view) {
        CheckBox checkBoxAdvice = view.findViewById(R.id.checkBoxEducationAdvice);
        CheckBox checkBoxAdvocacy = view.findViewById(R.id.checkBoxEducationAdvocacy);
        CheckBox checkBoxRef = view.findViewById(R.id.checkBoxEducationRef);
        CheckBox checkBoxEncouragement = view.findViewById(R.id.checkBoxEducationEncouragement);

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
        checkBoxRef.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextRef.setVisibility(View.VISIBLE);
                } else {
                    editTextRef.setVisibility(View.GONE);
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