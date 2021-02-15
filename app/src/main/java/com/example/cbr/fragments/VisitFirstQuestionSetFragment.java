package com.example.cbr.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.cbr.models.VisitCheckContainer;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import static com.example.cbr.models.Constants.CBR;
import static com.example.cbr.models.Constants.DCR;
import static com.example.cbr.models.Constants.DCRFU;

public class VisitFirstQuestionSetFragment extends Fragment {

    private static final String LOG_TAG = "FirstQuestionSetFragment";

    private CheckBox health;
    private CheckBox education;
    private CheckBox social;

    private final VisitCheckContainer visitCheckContainer;
    private FragmentActivity activity;
    private EditText date;
    private EditText cbrWorkerName;
    private EditText location;
    private EditText villageNumber;

    public VisitFirstQuestionSetFragment(VisitCheckContainer visitCheckContainer) {
        this.visitCheckContainer = visitCheckContainer;
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

        String selectedItem = spinnerLocation.getSelectedItem().toString();
        visitCheckContainer.setSiteLocation(selectedItem);
    }

    private void setupEditText(View view) {
        date = view.findViewById(R.id.editTextDate);
        cbrWorkerName = view.findViewById(R.id.editTextPersonName);
        location = view.findViewById(R.id.editTextLocationOfVisit);
        villageNumber = view.findViewById(R.id.editTextVillageNumber);

        Date currentTime = Calendar.getInstance().getTime();

        date.setText(currentTime.toString());
    }

    private void setupCheckBox(View view) {
        health = view.findViewById(R.id.checkBoxHealth);
        education = view.findViewById(R.id.checkBoxEducation);
        social = view.findViewById(R.id.checkBoxSocial);

        health.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                visitCheckContainer.setHealthChecked(isChecked);
            }
        });
        education.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                visitCheckContainer.setEducationChecked(isChecked);
            }
        });
        social.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                visitCheckContainer.setSocialChecked(isChecked);
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
                    visitCheckContainer.setPurposeOfVisit(CBR);

                    toggleQuestionTwo(question2, "#000000", true);
                    toggleRecordButton(View.VISIBLE, View.GONE);

                } else if (checkedId == R.id.radioButtonDCR) {
                    visitCheckContainer.setPurposeOfVisit(DCR);
                    toggleRecordButton(View.GONE, View.VISIBLE);

                } else if (checkedId == R.id.radioButtonDCRFU) {
                    visitCheckContainer.setPurposeOfVisit(DCRFU);
                    toggleRecordButton(View.GONE, View.VISIBLE);
                }
            }
        });
    }

    private void toggleRecordButton(int nextVisibility, int recordVisibility) {
        Button next = Objects.requireNonNull(activity).findViewById(R.id.buttonVisitNext);
        Button record = activity.findViewById(R.id.buttonVisitRecord);

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

        visitCheckContainer.setHealthChecked(false);
        visitCheckContainer.setEducationChecked(false);
        visitCheckContainer.setSocialChecked(false);
    }

    public EditText getDate() {
        return date;
    }

    public EditText getCbrWorkerName() {
        return cbrWorkerName;
    }

    public EditText getLocation() {
        return location;
    }

    public EditText getVillageNumber() {
        return villageNumber;
    }
}