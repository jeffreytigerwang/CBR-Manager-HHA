package com.example.cbr.fragments.newvisit;

import android.content.Context;
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
import com.example.cbr.models.VisitGeneralQuestionSetData;
import com.example.cbr.util.Constants;

public class VisitFirstQuestionSetFragment extends Fragment {

    private static final String LOG_TAG = "FirstQuestionSetFragment";

    private final ActivityNewVisitBinding containerBinding;
    private FragmentVisitFirstQuestionSetBinding binding;

    private final VisitGeneralQuestionSetData dataContainer;
    private final Context parentContext;
    private FragmentActivity activity;

    private CheckBox health;
    private CheckBox education;
    private CheckBox social;

    private EditText date;
    private EditText cbrWorkerName;
    private EditText location;
    private EditText villageNumber;

    private RadioGroup questionOne;
    private Spinner spinnerLocation;
    private TextView question2;

    public VisitFirstQuestionSetFragment(
            ActivityNewVisitBinding containerBinding,
            VisitGeneralQuestionSetData dataContainer, Context parentContext) {
        this.containerBinding = containerBinding;
        this.dataContainer = dataContainer;
        this.parentContext = parentContext;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentVisitFirstQuestionSetBinding.inflate(inflater, container, false);
        activity = getActivity();

        preLoadViews();

        setupRadioGroup();
        setupCheckBox();
        setupSpinner();

        return binding.getRoot();
    }

    private void preLoadViews() {
        findViews();

        date.setText(dataContainer.getDateOfVisit().toString());

        cbrWorkerName.setText(dataContainer.getWorkerName());
    }

    private void findViews() {
        date = binding.newVisitDateEditText;
        cbrWorkerName = binding.newVisitWorkerNameEditText;
        location = binding.newVisitLocationOfVisitEditText;
        villageNumber = binding.newVisitVillageNumberEditText;

        questionOne = binding.newVisitPurposeRadioGroup;

        health = binding.newVisitHealthCheckBox;
        education = binding.newVisitEducationCheckBox;
        social = binding.newVisitSocialCheckBox;

        question2 = binding.newVisitQ2TextView;

        spinnerLocation = binding.newVisitLocationSpinner;
    }

    private void setupSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                activity,
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.zone_locations_array)
        );

        spinnerLocation.setAdapter(adapter);

        dataContainer.setVisitZoneLocation(spinnerLocation.getSelectedItem().toString());
    }

    private void setupCheckBox() {
        health.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dataContainer.setHealthChecked(isChecked);
            }
        });
        education.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dataContainer.setEducationChecked(isChecked);
            }
        });
        social.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dataContainer.setSocialChecked(isChecked);
            }
        });
    }

    private void setupRadioGroup() {
        questionOne.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                resetQuestionTwo();

                if (checkedId == R.id.newVisit_CBRRadioButton) {
                    dataContainer.setPurposeOfVisit(Constants.CBR);

                    int unlockedColor = ContextCompat.getColor(parentContext, R.color.black);
                    toggleQuestionTwo(unlockedColor, true);
                    toggleRecordButton(View.VISIBLE, View.GONE);

                } else if (checkedId == R.id.newVisit_DCRradioButton) {
                    dataContainer.setPurposeOfVisit(Constants.DCR);
                    toggleRecordButton(View.GONE, View.VISIBLE);

                } else if (checkedId == R.id.newVisit_DCRFURadioButton) {
                    dataContainer.setPurposeOfVisit(Constants.DCRFU);
                    toggleRecordButton(View.GONE, View.VISIBLE);
                }
            }
        });
    }

    private void toggleRecordButton(int nextVisibility, int recordVisibility) {
        Button next = containerBinding.newVisitNextButton;
        Button record = containerBinding.newVisitRecordButton;

        next.setVisibility(nextVisibility);
        record.setVisibility(recordVisibility);
    }


    private void toggleQuestionTwo(int color, boolean toggle) {
        question2.setTextColor(color);
        health.setTextColor(color);
        education.setTextColor(color);
        social.setTextColor(color);

        health.setClickable(toggle);
        education.setClickable(toggle);
        social.setClickable(toggle);
    }

    private void resetQuestionTwo() {
        int lockedColor = ContextCompat.getColor(parentContext, R.color.colorLocked);
        toggleQuestionTwo(lockedColor, false);

        health.setChecked(false);
        education.setChecked(false);
        social.setChecked(false);

        dataContainer.setHealthChecked(false);
        dataContainer.setEducationChecked(false);
        dataContainer.setSocialChecked(false);
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