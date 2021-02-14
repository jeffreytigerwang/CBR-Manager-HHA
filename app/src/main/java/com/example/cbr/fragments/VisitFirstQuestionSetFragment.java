package com.example.cbr.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.cbr.R;
import com.example.cbr.models.VisitRecord;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class VisitFirstQuestionSetFragment extends Fragment {

    private static final String LOG_TAG = "FirstQuestionSetFragment";

    private CheckBox health;
    private CheckBox education;
    private CheckBox social;
    private Button record;
    private Button next;

    private final VisitRecord visitRecord;
    private FragmentActivity activity;

    public VisitFirstQuestionSetFragment(VisitRecord visitRecord) {
        this.visitRecord = visitRecord;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_visit_first_question_set, container, false);

        activity = getActivity();

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
                activity,
                android.R.layout.simple_spinner_item,
                locations
        );

        spinnerLocation.setAdapter(adapter);

        spinnerLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

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
                visitRecord.setHealthChecked(isChecked);
            }
        });
        education.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                visitRecord.setEducationChecked(isChecked);
            }
        });
        social.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                visitRecord.setSocialChecked(isChecked);
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

                    toggleRecordButton(View.VISIBLE, View.GONE);

                } else if (checkedId == R.id.radioButtonDCR) {
                    Log.d(LOG_TAG, "onCheckedChanged: checked DCR");
                    toggleRecordButton(View.GONE, View.VISIBLE);

                } else if (checkedId == R.id.radioButtonDCRFU) {
                    Log.d(LOG_TAG, "onCheckedChanged: checked DCRFU");
                    toggleRecordButton(View.GONE, View.VISIBLE);
                }
            }
        });
    }

    private void toggleRecordButton(int nextVisibility, int recordVisibility) {
        next = Objects.requireNonNull(activity).findViewById(R.id.buttonVisitNext);
        record = activity.findViewById(R.id.buttonVisitRecord);

        next.setVisibility(nextVisibility);
        record.setVisibility(recordVisibility);
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