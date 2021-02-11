package com.example.cbr.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.cbr.R;
import com.example.cbr.models.VisitRecord;

import java.util.Calendar;
import java.util.Date;

public class VisitFirstQuestionSet extends Fragment {

    private static final String LOG_TAG = "FirstQuestionSetFragment";

    private CheckBox health;
    private CheckBox education;
    private CheckBox social;
    private VisitRecord visitRecord;

    public VisitFirstQuestionSet() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_visit_first_question_set, container, false);

        visitRecord = VisitRecord.getInstance();

        setupRadioGroup(view);
        setupCheckBox(view);
        setupEditText(view);
        setupSpinner(view);

        return view;
    }

    private void setupSpinner(View view) {
        Spinner spinnerLocation = view.findViewById(R.id.spinnerLocation);

        String[] locations = new String[] {
                "BidiBidi Zone 1",
                "BidiBidi Zone 2",
                "BidiBidi Zone 3",
                "BidiBidi Zone 4",
                "BidiBidi Zone 5",
                "Palorinya Basecamp",
                "Palorinya Zone 1",
                "Palorinya Zone 2",
                "Palorinya Zone 3"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_spinner_item,
                locations
        );

        spinnerLocation.setAdapter(adapter);

        spinnerLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO: 2021-02-10 save value after next is pressed
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setupEditText(View view) {
        EditText date = view.findViewById(R.id.editTextDate);
        EditText CBRWorkerName = view.findViewById(R.id.editTextPersonName);
        EditText location = view.findViewById(R.id.editTextLocation);
        EditText villageNumber = view.findViewById(R.id.editTextVillageNumber);
        Date currentTime = Calendar.getInstance().getTime();

        date.setText(currentTime.toString());
        // TODO: 2021-02-09 fill CBR worker name
        // TODO: 2021-02-10 save location and village number after next is pressed
    }

    private void setupCheckBox(View view) {
        health = view.findViewById(R.id.checkBoxHealth);
        education = view.findViewById(R.id.checkBoxEducation);
        social = view.findViewById(R.id.checkBoxSocial);

        health.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    visitRecord.setHealthChecked(true);
                }
            }
        });
        education.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO: 2021-02-09 make question 11-13 available
                if (isChecked) {
                    visitRecord.setEducationChecked(true);
                }
            }
        });
        social.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO: 2021-02-09 make question 14-16 available
                if (isChecked) {
                    visitRecord.setSocialChecked(true);
                }
            }
        });
    }

    private void setupRadioGroup(final View view) {
        RadioGroup questionOne = view.findViewById(R.id.radioGroupPurpose);

        questionOne.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                TextView question2 = view.findViewById(R.id.textViewQ2);

                resetQuestionTwo(question2);

                if (checkedId == R.id.radioButtonCBR) {
                    Log.d(LOG_TAG, "onCheckedChanged: checked CBR");

                    toggleQuestionTwo(question2, "#000000", true);

                } else if (checkedId == R.id.radioButtonDCR) {
                    Log.d(LOG_TAG, "onCheckedChanged: checked DCR");


                } else if (checkedId == R.id.radioButtonDCRFU) {
                    Log.d(LOG_TAG, "onCheckedChanged: checked DCRFU");
                }
            }
        });
    }

    private void toggleQuestionTwo(TextView question2, String color, boolean toggle) {
        question2.setTextColor(Color.parseColor(color));
        health.setTextColor(Color.parseColor(color));
        education.setTextColor(Color.parseColor(color));
        social.setTextColor(Color.parseColor(color));

        health.setClickable(toggle);
        education.setClickable(toggle);
        social.setClickable(toggle);
    }

    private void resetQuestionTwo(TextView question2) {
        toggleQuestionTwo(question2, "#808080", false);

        health.setChecked(false);
        education.setChecked(false);
        social.setChecked(false);

        visitRecord.setHealthChecked(false);
        visitRecord.setEducationChecked(false);
        visitRecord.setSocialChecked(false);
    }
}