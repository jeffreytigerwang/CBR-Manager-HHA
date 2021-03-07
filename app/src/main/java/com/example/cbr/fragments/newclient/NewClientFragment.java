package com.example.cbr.fragments.newclient;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cbr.R;
import com.example.cbr.databinding.FragmentNewclientBinding;
import com.example.cbr.fragments.base.BaseFragment;
import com.example.cbr.models.ClientDisability;
import com.example.cbr.models.ClientEducationAspect;
import com.example.cbr.models.ClientHealthAspect;
import com.example.cbr.models.ClientInfo;
import com.example.cbr.models.ClientSocialAspect;
import com.example.cbr.retrofit.JsonPlaceHolderApi;
import com.example.cbr.retrofit.RetrofitInit;

import java.util.concurrent.ThreadLocalRandom;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NewClientFragment extends BaseFragment implements NewClientContract.View {

    private int clientId;

    private FragmentNewclientBinding binding;
    private NewClientContract.Presenter newClientPresenter;

    // Init API
    private Retrofit retrofit;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setPresenter(new NewClientPresenter(this));
        binding = FragmentNewclientBinding.inflate(inflater, container, false);

        // In onCreate lifecycle, grab all the information from the UI part (i.e. EditText, RatioButton)
        // Note: in fragment, you cannot use findViewById directly. Add getView() instead
        // https://stackoverflow.com/questions/6495898/findviewbyid-in-fragment

        // Init Retrofit & NodeJs stuff
        retrofit = RetrofitInit.getInstance();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        // List of API calls like GET & POST

        populateLocationSpinner();
        populateRateClientHealthSpinner();
        populateRateClientEducationSpinner();
        populateRateClientSocialStatusSpinner();
        setupRecordClientButton();
        return binding.getRoot();
    }


    private void populateLocationSpinner() {
        Spinner spinner = binding.newClientZoneLocationSpinner;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.zone_locations_array, android.R.layout.simple_spinner_item);
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
                String location = binding.newClientZoneLocationSpinner.getSelectedItem().toString();

                if (binding.newClientVillageNumberEditText.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Village Number cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                Integer villageNumber = Integer.parseInt(binding.newClientVillageNumberEditText.getText().toString());
                String dateText = binding.newClientDateEditText.getText().toString();
                String firstName = binding.newClientFirstNameEditText.getText().toString();
                String lastName = binding.newClientLastNameEditText.getText().toString();

                if (binding.newClientAgeEditText.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Age cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                Integer age = Integer.parseInt(binding.newClientAgeEditText.getText().toString());
                String contactNumber = binding.newClientContactNumberEditText.getText().toString();
                boolean caregiverPresentForInterview = binding.newClientCaregiverIsPresentCheckBox.isChecked();

                if (binding.newClientCaregiverContactNumberEditText.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Caregiver Contact Number cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                Integer caregiverContactNumber = Integer.parseInt(binding.newClientCaregiverContactNumberEditText.getText().toString());

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

                clientId = ThreadLocalRandom.current().nextInt(100000000, 999999999);

                createClientBasicInfo(clientId, firstName, lastName, gpsLocation, location, villageNumber,
                        age, contactNumber, caregiverPresentForInterview, caregiverContactNumber);

                ClientDisability clientDisability = new ClientDisability(clientId, amputeeDisability, polioDisability, spinalCordInjuryDisability, cerebralPalsyDisability,
                        spinaBifidaDisability, hydrocephalusDisability, visualImpairmentDisability, hearingImpairmentDisability, doNotKnowDisability, otherDisability);

                ClientHealthAspect clientHealthAspect = new ClientHealthAspect(clientId, rateHealth, describeHealth, setGoalForHealth);

                ClientEducationAspect clientEducationAspect = new ClientEducationAspect(clientId, rateEducation, describeEducation, setGoalForEducation);

                ClientSocialAspect clientSocialAspect = new ClientSocialAspect(clientId, rateSocialStatus, describeSocialStatus, setGoalForSocialStatus);

                createClientDisability(clientDisability);

                createClientHealthAspect(clientHealthAspect);

                createClientEducationAspect(clientEducationAspect);

                createClientSocialAspect(clientSocialAspect);
            }
        });

    }

    private void createClientSocialAspect(ClientSocialAspect clientSocialAspect) {
        Call<ClientSocialAspect> call = jsonPlaceHolderApi.createClientSocialAspect(clientSocialAspect);

        call.enqueue(new Callback<ClientSocialAspect>() {
            @Override
            public void onResponse(Call<ClientSocialAspect> call, Response<ClientSocialAspect> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Social Aspect Record Fail", Toast.LENGTH_SHORT).show();
                    return;
                }

                ClientSocialAspect clientSocialAspectResponse = response.body();
                Toast.makeText(getActivity(),  "Social Aspect Record Successful", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ClientSocialAspect> call, Throwable t) {

            }
        });
    }

    private void createClientEducationAspect(ClientEducationAspect clientEducationAspect) {
        Call<ClientEducationAspect> call = jsonPlaceHolderApi.createClientEducationAspect(clientEducationAspect);

        call.enqueue(new Callback<ClientEducationAspect>() {
            @Override
            public void onResponse(Call<ClientEducationAspect> call, Response<ClientEducationAspect> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Education Aspect Record Fail", Toast.LENGTH_SHORT).show();
                    return;
                }

                ClientEducationAspect clientEducationAspectResponse = response.body();
                Toast.makeText(getActivity(),  "Education Aspect Record Successful", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ClientEducationAspect> call, Throwable t) {

            }
        });
    }

    private void createClientHealthAspect(ClientHealthAspect clientHealthAspect) {
        Call<ClientHealthAspect> call = jsonPlaceHolderApi.createClientHealthAspect(clientHealthAspect);

        call.enqueue(new Callback<ClientHealthAspect>() {
            @Override
            public void onResponse(Call<ClientHealthAspect> call, Response<ClientHealthAspect> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Health Aspect Record Fail", Toast.LENGTH_SHORT).show();
                    return;
                }

                ClientHealthAspect clientHealthAspectResponse = response.body();
                Toast.makeText(getActivity(),  "Health Aspect Record Successful", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ClientHealthAspect> call, Throwable t) {

            }
        });
    }


    private void createClientDisability(ClientDisability clientDisability) {

        Call<ClientDisability> call = jsonPlaceHolderApi.createClientDisability(clientDisability);

        call.enqueue(new Callback<ClientDisability>() {
            @Override
            public void onResponse(Call<ClientDisability> call, Response<ClientDisability> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Disability Record Fail", Toast.LENGTH_SHORT).show();
                    return;
                }

                ClientDisability clientInfoResponse = response.body();
                Toast.makeText(getActivity(),  "Disability Record Successful", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ClientDisability> call, Throwable t) {

            }
        });
    }

    private void createClientBasicInfo(Integer clientId, String firstName, String lastName, String gpsLocation, String location,
                                       Integer villageNumber, Integer age, String contactNumber, boolean caregiverPresentForInterview,
                                       Integer caregiverContactNumber) {

        Call<ClientInfo> call = jsonPlaceHolderApi.createClient(clientId, firstName, lastName, gpsLocation, location,
                villageNumber, age, contactNumber, caregiverPresentForInterview, caregiverContactNumber);

        call.enqueue(new Callback<ClientInfo>() {
            @Override
            public void onResponse(Call<ClientInfo> call, Response<ClientInfo> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Record Fail", Toast.LENGTH_SHORT).show();
                    return;
                }

                ClientInfo clientInfoResponse = response.body();
                Toast.makeText(getActivity(), clientInfoResponse.getFirstName() + " " +
                        clientInfoResponse.getLastName() + "\n" + "Record Successful", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ClientInfo> call, Throwable t) {

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
