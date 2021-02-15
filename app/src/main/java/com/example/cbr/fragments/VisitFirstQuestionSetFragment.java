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

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.cbr.R;
import com.example.cbr.databinding.ActivityNewVisitBinding;
import com.example.cbr.databinding.FragmentVisitFirstQuestionSetBinding;
import com.example.cbr.models.VisitCheckContainer;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;

import static com.example.cbr.models.Constants.CBR;
import static com.example.cbr.models.Constants.DCR;
import static com.example.cbr.models.Constants.DCRFU;

public class VisitFirstQuestionSetFragment extends Fragment {

    private static final String LOG_TAG = "FirstQuestionSetFragment";

    private final ActivityNewVisitBinding containerBinding;
    private FragmentVisitFirstQuestionSetBinding binding;

    private final VisitCheckContainer visitCheckContainer;
    private FragmentActivity activity;

    private CheckBox health;
    private CheckBox education;
    private CheckBox social;

    private EditText date;
    private EditText cbrWorkerName;
    private EditText location;
    private EditText villageNumber;

    public VisitFirstQuestionSetFragment(ActivityNewVisitBinding containerBinding, VisitCheckContainer visitCheckContainer) {
        this.containerBinding = containerBinding;
        this.visitCheckContainer = visitCheckContainer;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentVisitFirstQuestionSetBinding.inflate(inflater, container, false);
        activity = getActivity();

        setupRadioGroup();
        setupCheckBox();
        setupEditText();
        setupSpinner();

        return binding.getRoot();
    }

    private void setupSpinner() {
        Spinner spinnerLocation = binding.spinnerLocation;

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

    private void setupEditText() {
        date = binding.editTextDate;
        cbrWorkerName = binding.editTextPersonName;
        location = binding.editTextLocationOfVisit;
        villageNumber = binding.editTextVillageNumber;

        Date currentTime = Calendar.getInstance().getTime();

        date.setText(currentTime.toString());
    }

    private void setupCheckBox() {
        health = binding.checkBoxHealth;
        education = binding.checkBoxEducation;
        social = binding.checkBoxSocial;

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

    private void setupRadioGroup() {
        RadioGroup questionOne = binding.radioGroupPurpose;

        questionOne.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                TextView question2 = binding.textViewQ2;

                resetQuestionTwo(question2);

                if (checkedId == R.id.radioButtonCBR) {
                    visitCheckContainer.setPurposeOfVisit(CBR);

                    int unlockedColor = ContextCompat.getColor(getContext(), R.color.cbrBlack);
                    toggleQuestionTwo(question2, unlockedColor, true);
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
        Button next = containerBinding.buttonVisitNext;
        Button record = containerBinding.buttonVisitRecord;

        next.setVisibility(nextVisibility);
        record.setVisibility(recordVisibility);
    }


    private void toggleQuestionTwo(TextView question2, int color, boolean toggle) {
        question2.setTextColor(color);
        health.setTextColor(color);
        education.setTextColor(color);
        social.setTextColor(color);

        health.setClickable(toggle);
        education.setClickable(toggle);
        social.setClickable(toggle);
    }

    private void resetQuestionTwo(TextView question2) {
        int lockedColor = ContextCompat.getColor(getContext(), R.color.colorLocked);
        toggleQuestionTwo(question2, lockedColor, false);

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