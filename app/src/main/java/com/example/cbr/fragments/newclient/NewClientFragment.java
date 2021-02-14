package com.example.cbr.fragments.newclient;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;

import com.example.cbr.R;
import com.example.cbr.databinding.FragmentNewclientBinding;
import com.example.cbr.fragments.base.BaseFragment;
import com.example.cbr.model.ClientInfo;

public class NewClientFragment extends BaseFragment implements NewClientContract.View {

    private FragmentNewclientBinding binding;
    private NewClientContract.Presenter newClientPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setPresenter(new NewClientPresenter(this));
        binding = FragmentNewclientBinding.inflate(inflater, container, false);
        populateLocationSpinner();
        populateRateClientHealthSpinner();
        populateRateClientEducationSpinner();
        populateRateClientSocialStatusSpinner();
        setupRecordClientButton();
        return binding.getRoot();
    }

    private void populateLocationSpinner() {
        Spinner spinner = binding.newClientLocationSpinner;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.locations_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void populateRateClientHealthSpinner() {
        Spinner spinner = binding.newClientRateClientHealthSpinner;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.client_ratings_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void populateRateClientEducationSpinner() {
        Spinner spinner = binding.newClientRateClientEducationSpinner;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.client_ratings_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void populateRateClientSocialStatusSpinner() {
        Spinner spinner = binding.newClientRateClientSocialStatusSpinner;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.client_ratings_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void setupRecordClientButton() {
        Button button = binding.newClientRecordClientButton;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean consentToInterview = binding.newClientConsentToInterviewCheckBox.isChecked();
                String gpsLocation = binding.newClientGpsLocationEditText.getText().toString();
                String location = binding.newClientLocationSpinner.getSelectedItem().toString();
                String villageNumber = binding.newClientVillageNumberEditText.getText().toString();
                String dateText = binding.newClientDateEditText.getText().toString();
                String firstName = binding.newClientFirstNameEditText.getText().toString();
                String lastName = binding.newClientLastNameEditText.getText().toString();
                Integer age = Integer.parseInt(binding.newClientAgeEditText.getText().toString());
                String contactNumber = binding.newClientContactNumberEditText.getText().toString();
                boolean caregiverPresentForInterview = binding.newClientCaregiverIsPresentCheckBox.isChecked();
                String caregiverContactNumber = binding.newClientCaregiverContactNumberEditText.getText().toString();
                boolean amputeeDisability = binding.newClientAmputeeDisabilityCheckBox.isChecked();
                boolean polioDisability = binding.newClientPolioDisabilityCheckBox.isChecked();
                boolean spinalCordInjuryDisability = binding.newClientSpinalCordInjuryDisabilityCheckBox.isChecked();
                boolean cerebralPalsyDisability = binding.newClientCerebralPalsyDisabilityCheckBox.isChecked();
                boolean spinaBifidaDisability = binding.newClientSpinaBifidaDisabilityCheckBox.isChecked();
                boolean hydrocephalusDisability = binding.newClientHydrocephalusDisabilityCheckBox.isChecked();
                boolean visualImpairmentDisability = binding.newClientVisualImpairmentDisabilityCheckBox.isChecked();
                boolean hearingImpairmentDisability = binding.newClientHearingImpairmentDisabilityCheckBox.isChecked();
                boolean doNotKnowDisability = binding.newClientDoNotKnowDisabilityCheckBox.isChecked();
                boolean otherDisability = binding.newClientOtherDisabilityCheckBox.isChecked();
                String rateHealth = binding.newClientRateClientHealthSpinner.getSelectedItem().toString();
                String describeHealth = binding.newClientDescribeClientHealthEditText.getText().toString();
                String setGoalForHealth = binding.newClientSetGoalForClientHealthEditText.getText().toString();
                String rateEducation = binding.newClientRateClientEducationSpinner.getSelectedItem().toString();
                String describeEducation = binding.newClientDescribeClientEducationEditText.getText().toString();
                String setGoalForEducation = binding.newClientSetGoalForClientEducationEditText.getText().toString();
                String rateSocialStatus = binding.newClientRateClientSocialStatusSpinner.getSelectedItem().toString();
                String describeSocialStatus = binding.newClientDescribeClientSocialStatusEditText.getText().toString();
                String setGoalForSocialStatus = binding.newClientSetGoalForClientSocialStatusEditText.getText().toString();

                ClientInfo clientInfo = new ClientInfo(
                        consentToInterview,
                        gpsLocation,
                        location,
                        villageNumber,
                        dateText,
                        firstName,
                        lastName,
                        age,
                        contactNumber,
                        caregiverPresentForInterview,
                        caregiverContactNumber,
                        amputeeDisability,
                        polioDisability,
                        spinalCordInjuryDisability,
                        cerebralPalsyDisability,
                        spinaBifidaDisability,
                        hydrocephalusDisability,
                        visualImpairmentDisability,
                        hearingImpairmentDisability,
                        doNotKnowDisability,
                        otherDisability,
                        rateHealth,
                        describeHealth,
                        setGoalForHealth,
                        rateEducation,
                        describeEducation,
                        setGoalForEducation,
                        rateSocialStatus,
                        describeSocialStatus,
                        setGoalForSocialStatus);
            }
        });
    }


    @Override
    public void displayString(String string) {
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
