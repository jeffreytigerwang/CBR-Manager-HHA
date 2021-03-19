package com.example.cbr.fragments.newvisit;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.cbr.R;
import com.example.cbr.databinding.FragmentVisitFirstQuestionSetBinding;
import com.example.cbr.models.VisitGeneralQuestionSetData;
import com.example.cbr.util.Constants;
import com.example.cbr.util.CustomExceptions;
import com.example.cbr.util.LocationUtil;
import com.example.cbr.util.StringsUtil;

/**
* Initial fragment class to display general questions (7 questions)
* */

public class VisitFirstQuestionSetFragment extends Fragment {

    private static final String LOG_TAG = "FirstQuestionSetFragment";

    private FragmentVisitFirstQuestionSetBinding binding;

    private final VisitGeneralQuestionSetData dataContainer;
    private final Context context;
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

    private String latLongLocation;

    public VisitFirstQuestionSetFragment(VisitGeneralQuestionSetData dataContainer, Context context) {
        this.dataContainer = dataContainer;
        this.context = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentVisitFirstQuestionSetBinding.inflate(inflater, container, false);
        activity = getActivity();

        preLoadViews();

        requestLocationPermissions();

        setupRadioGroup();
        setupCheckBox();
        setupSpinner();

        return binding.getRoot();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == Constants.LOCATION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                setLatLongLocation();
            } else {
                Toast.makeText(context, R.string.location_permission_not_granted,
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void requestLocationPermissions() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED
                &&
                ActivityCompat.checkSelfPermission(context,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[] {Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION}, Constants.LOCATION_REQUEST_CODE);
        } else {
            setLatLongLocation();
        }
    }

    private void setLatLongLocation() {
        try {
            LocationUtil locationUtil = new LocationUtil(context);
            latLongLocation = getString(R.string.lat_long_location,
                    locationUtil.getLatitude(), locationUtil.getLongitude());
            Log.d(LOG_TAG, "onRequestPermissionsResult: latLongLocation=" + latLongLocation);
            location.setText(latLongLocation);
        } catch (CustomExceptions.GPSNotEnabled gpsNotEnabled) {
            Log.i(LOG_TAG, "onRequestPermissionsResult: " + gpsNotEnabled.getMessage());
        }
    }


    private void preLoadViews() {
        findViews();

        final String dateUK = StringsUtil.dateToUKFormat(dataContainer.getDateOfVisit());
        date.setText(dateUK);

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

        spinnerLocation = binding.newVisitLocationSpinner;
    }

    private void setupSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
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
                if (checkedId == R.id.newVisit_CBRRadioButton) {
                    dataContainer.setPurposeOfVisit(Constants.CBR);

                } else if (checkedId == R.id.newVisit_DCRradioButton) {
                    dataContainer.setPurposeOfVisit(Constants.DCR);

                } else if (checkedId == R.id.newVisit_DCRFURadioButton) {
                    dataContainer.setPurposeOfVisit(Constants.DCRFU);
                }
            }
        });
    }

    public String getDate() {
        return date.getText().toString();
    }

    public String getCbrWorkerName() {
        return cbrWorkerName.getText().toString();
    }

    public String getLocation() {
        return location.getText().toString();
    }

    public String getVillageNumber() {
        return villageNumber.getText().toString();
    }
}