package com.example.cbr.fragments.newclient;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;

import com.example.cbr.R;
import com.example.cbr.databinding.FragmentNewclientBinding;
import com.example.cbr.fragments.base.BaseFragment;
import com.example.cbr.model.ClientInfo;

import java.util.Calendar;
import java.util.Date;

public class NewClientFragment extends BaseFragment implements NewClientContract.View {

    private FragmentNewclientBinding binding;
    private NewClientContract.Presenter newClientPresenter;

    private CheckBox consentToInterview;
    private CheckBox caregiverPresentForInterview;
    private CheckBox amputee;
    private CheckBox polio;
    private CheckBox spinalCordInjury;
    private CheckBox cerebralPalsy;
    private CheckBox spinaBifida;
    private CheckBox hydrocephalus;
    private CheckBox visualImpairment;
    private CheckBox hearingImpairment;
    private CheckBox doNotKnow;
    private CheckBox other;

    private EditText gpsLocation;
    private EditText villageNumber;
    private EditText dateJoined;
    private EditText firstName;
    private EditText lastName;
    private EditText age;
    private EditText contactNumber;
    private EditText caregiverContactNumber;
    private EditText describeHealth;
    private EditText setGoalForHealth;
    private EditText describeEducation;
    private EditText setGoalForEducation;
    private EditText describeSocialStatus;
    private EditText setGoalForSocialStatus;

    private Spinner zoneLocation;
    private Spinner rateHealth;
    private Spinner rateEducation;
    private Spinner rateSocialStatus;

    private RadioGroup gender;
    private String chosenGender;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setPresenter(new NewClientPresenter(this));
        binding = FragmentNewclientBinding.inflate(inflater, container, false);

        preLoadViews();
        setupZoneLocationSpinner();
        setupRateHealthSpinner();
        setupRateEducationSpinner();
        setupRateSocialStatusSpinner();
        setupRecordClientButton();
        return binding.getRoot();
    }

    private void preLoadViews() {
        findViews();

        Date currentTime = Calendar.getInstance().getTime();
        dateJoined.setText(currentTime.toString());
    }

    private void findViews() {
        consentToInterview = binding.newClientConsentToInterviewCheckBox;
        caregiverPresentForInterview = binding.newClientCaregiverIsPresentCheckBox;
        amputee = binding.newClientAmputeeDisabilityCheckBox;
        polio = binding.newClientPolioDisabilityCheckBox;
        spinalCordInjury = binding.newClientSpinalCordInjuryDisabilityCheckBox;
        cerebralPalsy = binding.newClientCerebralPalsyDisabilityCheckBox;
        spinaBifida = binding.newClientSpinaBifidaDisabilityCheckBox;
        hydrocephalus = binding.newClientHydrocephalusDisabilityCheckBox;
        visualImpairment = binding.newClientVisualImpairmentDisabilityCheckBox;
        hearingImpairment = binding.newClientHearingImpairmentDisabilityCheckBox;
        doNotKnow = binding.newClientDoNotKnowDisabilityCheckBox;
        other = binding.newClientOtherDisabilityCheckBox;

        gpsLocation = binding.newClientGpsLocationEditText;
        villageNumber = binding.newClientVillageNumberEditText;
        dateJoined = binding.newClientDateEditText;
        firstName = binding.newClientFirstNameEditText;
        lastName = binding.newClientLastNameEditText;
        age = binding.newClientAgeEditText;
        contactNumber = binding.newClientContactNumberEditText;
        caregiverContactNumber = binding.newClientCaregiverContactNumberEditText;
        describeHealth = binding.newClientDescribeClientHealthEditText;
        setGoalForHealth = binding.newClientSetGoalForClientHealthEditText;
        describeEducation = binding.newClientDescribeClientEducationEditText;
        setGoalForEducation = binding.newClientSetGoalForClientEducationEditText;
        describeSocialStatus = binding.newClientDescribeClientSocialStatusEditText;
        setGoalForSocialStatus = binding.newClientSetGoalForClientSocialStatusEditText;

        zoneLocation = binding.newClientZoneLocationSpinner;
        rateHealth = binding.newClientRateClientHealthSpinner;
        rateEducation = binding.newClientRateClientEducationSpinner;
        rateSocialStatus = binding.newClientRateClientSocialStatusSpinner;

        gender = binding.newClientGenderRadioGroup;
    }

    private void setupZoneLocationSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.zone_locations_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        zoneLocation.setAdapter(adapter);
    }

    private void setupRateHealthSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.client_ratings_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rateHealth.setAdapter(adapter);
    }

    private void setupRateEducationSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.client_ratings_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rateEducation.setAdapter(adapter);
    }

    private void setupRateSocialStatusSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.client_ratings_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        rateSocialStatus.setAdapter(adapter);
    }

    private void setupRadioGroup() {
        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.newClient_maleRadioButton) {
                    chosenGender = "male";
                } else if (checkedId == R.id.newClient_femaleRadioButton) {
                    chosenGender = "female";
                }
            }
        });
    }

    private void setupRecordClientButton() {
        Button button = binding.newClientRecordClientButton;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClientInfo clientInfo = new ClientInfo(
                        consentToInterview.isChecked(),
                        gpsLocation.getText().toString(),
                        zoneLocation.getSelectedItem().toString(),
                        villageNumber.getText().toString(),
                        dateJoined.getText().toString(),
                        firstName.getText().toString(),
                        lastName.getText().toString(),
                        chosenGender,
                        Integer.parseInt(age.getText().toString()),
                        contactNumber.getText().toString(),
                        caregiverPresentForInterview.isChecked(),
                        caregiverContactNumber.getText().toString(),
                        amputee.isChecked(),
                        polio.isChecked(),
                        spinalCordInjury.isChecked(),
                        cerebralPalsy.isChecked(),
                        spinaBifida.isChecked(),
                        hydrocephalus.isChecked(),
                        visualImpairment.isChecked(),
                        hearingImpairment.isChecked(),
                        doNotKnow.isChecked(),
                        other.isChecked(),
                        rateHealth.getSelectedItem().toString(),
                        describeHealth.getText().toString(),
                        setGoalForHealth.getText().toString(),
                        rateEducation.getSelectedItem().toString(),
                        describeEducation.getText().toString(),
                        setGoalForEducation.getText().toString(),
                        rateSocialStatus.getSelectedItem().toString(),
                        describeSocialStatus.getText().toString(),
                        setGoalForSocialStatus.getText().toString());
            }
        });
    }

    @Override
    public void setPresenter(NewClientContract.Presenter presenter) {
        newClientPresenter = presenter;
    }


    public static NewClientFragment newInstance() {
        return new NewClientFragment();
    }

    public static String getFragmentTag() {
        return NewClientFragment.class.getSimpleName();
    }
}
