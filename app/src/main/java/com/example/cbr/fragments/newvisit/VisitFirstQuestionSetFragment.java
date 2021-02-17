package com.example.cbr.fragments.newvisit;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.example.cbr.models.Constants;
import com.example.cbr.models.VisitCheckContainer;

import java.util.Calendar;
import java.util.Date;

import static com.example.cbr.models.Constants.CBR;
import static com.example.cbr.models.Constants.DATE_OF_VISIT_KEY;
import static com.example.cbr.models.Constants.DCR;
import static com.example.cbr.models.Constants.DCRFU;
import static com.example.cbr.models.Constants.IS_EDUCATION_CHECKED_KEY;
import static com.example.cbr.models.Constants.IS_HEALTH_CHECKED_KEY;
import static com.example.cbr.models.Constants.IS_SOCIAL_CHECKED_KEY;
import static com.example.cbr.models.Constants.LOCATION_OF_VISIT_KEY;
import static com.example.cbr.models.Constants.NAME_OF_CBR_WORKER_KEY;
import static com.example.cbr.models.Constants.PURPOSE_OF_VISIT_KEY;
import static com.example.cbr.models.Constants.SITE_LOCATION_SPINNER_SELECTED_POSITION_KEY;
import static com.example.cbr.models.Constants.VILLAGE_NUMBER_KEY;

public class VisitFirstQuestionSetFragment extends Fragment {

    private static final String LOG_TAG = "FirstQuestionSetFragment";

    private final ActivityNewVisitBinding containerBinding;
    private FragmentVisitFirstQuestionSetBinding binding;

    private final VisitCheckContainer visitCheckContainer;
    private FragmentActivity activity;
    private final Context context;

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
            VisitCheckContainer visitCheckContainer,
            Context context) {
        this.containerBinding = containerBinding;
        this.visitCheckContainer = visitCheckContainer;
        this.context = context;
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

        SharedPreferences sharedPref = context.getSharedPreferences(Constants.QUESTION_SET_1_PREF_NAME, Context.MODE_PRIVATE);

        boolean isHealthChecked = sharedPref.getBoolean(IS_HEALTH_CHECKED_KEY, false);
        boolean isEducationChecked = sharedPref.getBoolean(IS_EDUCATION_CHECKED_KEY, false);
        boolean isSocialChecked = sharedPref.getBoolean(IS_SOCIAL_CHECKED_KEY, false);
        String purpose = sharedPref.getString(PURPOSE_OF_VISIT_KEY, "");
        String dateOfVisit = sharedPref.getString(DATE_OF_VISIT_KEY, "");
        String workerName = sharedPref.getString(NAME_OF_CBR_WORKER_KEY, "");
        String locationOfVisit = sharedPref.getString(LOCATION_OF_VISIT_KEY, "");
        int siteLocationSpinnerSelectedPosition = sharedPref.getInt(
                SITE_LOCATION_SPINNER_SELECTED_POSITION_KEY, 0);
        String villageNumber = sharedPref.getString(VILLAGE_NUMBER_KEY, "");

        if (purpose.equalsIgnoreCase(CBR)) {
            questionOne.check(R.id.newVisit_CBRRadioButton);

            int unlockedColor = ContextCompat.getColor(context, R.color.cbrBlack);
            toggleQuestionTwo(unlockedColor, true);

        } else if (purpose.equalsIgnoreCase(DCR)) {
            questionOne.check(R.id.newVisit_DCRradioButton);
        } else if (purpose.equalsIgnoreCase(DCRFU)) {
            questionOne.check(R.id.newVisit_DCRFURadioButton);
        }
        health.setChecked(isHealthChecked);
        education.setChecked(isEducationChecked);
        social.setChecked(isSocialChecked);

        if (dateOfVisit.isEmpty()) {
            Date currentTime = Calendar.getInstance().getTime();
            date.setText(currentTime.toString());
        } else {
            date.setText(dateOfVisit);
        }

        if (workerName.isEmpty()) {
            // TODO: 2021-02-15 fill worker name
        } else {
            cbrWorkerName.setText(workerName);
        }
        this.location.setText(locationOfVisit);
        spinnerLocation.setSelection(siteLocationSpinnerSelectedPosition);
        this.villageNumber.setText(villageNumber);
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

        spinnerLocation = binding.newVisitLocationspinner;
    }

    private void setupSpinner() {
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
        visitCheckContainer.setSiteLocationSpinnerSelectedPosition(spinnerLocation.getSelectedItemPosition());
    }

    private void setupCheckBox() {
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
        questionOne.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                resetQuestionTwo();

                if (checkedId == R.id.newVisit_CBRRadioButton) {
                    visitCheckContainer.setPurposeOfVisit(CBR);

                    int unlockedColor = ContextCompat.getColor(getContext(), R.color.cbrBlack);
                    toggleQuestionTwo(unlockedColor, true);
                    toggleRecordButton(View.VISIBLE, View.GONE);

                } else if (checkedId == R.id.newVisit_DCRradioButton) {
                    visitCheckContainer.setPurposeOfVisit(DCR);
                    toggleRecordButton(View.GONE, View.VISIBLE);

                } else if (checkedId == R.id.newVisit_DCRFURadioButton) {
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
        int lockedColor = ContextCompat.getColor(getContext(), R.color.colorLocked);
        toggleQuestionTwo(lockedColor, false);

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