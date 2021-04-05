package com.example.cbr.fragments.clientpage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cbr.R;
import com.example.cbr.activities.NewVisitActivity;
import com.example.cbr.adapters.questioninfoadapters.ClientInfoAdapter;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.ClickableViewContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.DividerViewContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.DoubleTextViewContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.HeaderViewContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.QuestionDataContainer;
import com.example.cbr.databinding.FragmentClientpageBinding;
import com.example.cbr.fragments.base.BaseFragment;
import com.example.cbr.models.ClientInfo;
import com.example.cbr.models.VisitGeneralQuestionSetData;
import com.example.cbr.util.StringsUtil;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.example.cbr.util.Constants.CAMERA_REQUEST_CODE;

public class ClientPageFragment extends BaseFragment implements ClientPageContract.View {

    private FragmentClientpageBinding binding;
    private ClientPageContract.Presenter clientListPresenter;
    private ClientPageFragmentInterface clientPageFragmentInterface;

    private ClientInfo clientInfo;
    private List<VisitGeneralQuestionSetData> visitsList;
    private ClientInfoAdapter clientInfoAdapter;

    private static final String CLIENT_PAGE_BUNDLE = "clientPageBundle";

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            clientPageFragmentInterface = (ClientPageFragmentInterface) context;
        } catch (ClassCastException e) {
            Log.e(getFragmentTag(), "Activity should implement ClientPageFragmentInterface");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setPresenter(new ClientPagePresenter(this));
        binding = FragmentClientpageBinding.inflate(inflater, container, false);

        clientInfo = (ClientInfo) getArguments().getSerializable(CLIENT_PAGE_BUNDLE);

        try {
            visitsList = clientListPresenter.getVisits();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setupClientInfoCard();
        setupRecyclerView();

        return binding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            clientInfoAdapter.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setupClientInfoCard() {
        binding.clientPageGenderText.setText(getString(R.string.gender_clientpage, clientInfo.getGender()));
        binding.clientPageNameText.setText(clientInfo.getFullName());
        binding.clientPageLocationText.setText(clientInfo.getZoneLocation());
        binding.clientPageAgeText.setText(getString(R.string.age_clientpage, clientInfo.getAge().toString()));
        binding.clientPageDisabilityText.setText(getString(R.string.disability_clientpage, clientInfo.getDisabilityListFormatted()));

        binding.clientPageNewVisitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = NewVisitActivity.makeLaunchIntent(getActivity(),
                        clientInfo);
                startActivity(intent);
            }
        });

        binding.clientPageNewReferralButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clientPageFragmentInterface.swapToReferralPage(clientInfo);
            }
        });
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = binding.clientPageInfoList;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);
        clientInfoAdapter = new ClientInfoAdapter(getActivity(), generateDataContainerList());
        recyclerView.setAdapter(clientInfoAdapter);
    }

    private List<QuestionDataContainer> generateDataContainerList() {
        // Add client info to the data container list
        List<QuestionDataContainer> questionDataContainerList = new ArrayList<>();
        questionDataContainerList.add(new HeaderViewContainer(getString(R.string.basic_information)));
        questionDataContainerList.add(new DoubleTextViewContainer(getString(R.string.consent_to_interview), StringsUtil.boolToText(clientInfo.isConsentToInterview())));

        questionDataContainerList.add(new DoubleTextViewContainer(getString(R.string.gps_location), clientInfo.getGpsLocation()));
        questionDataContainerList.add(new DoubleTextViewContainer(getString(R.string.location), clientInfo.getZoneLocation()));
        questionDataContainerList.add(new DoubleTextViewContainer(getString(R.string.village_number), clientInfo.getVillageNumber()));
        questionDataContainerList.add(new DoubleTextViewContainer(getString(R.string.date), clientInfo.getDateJoined()));
        questionDataContainerList.add(new DoubleTextViewContainer(getString(R.string.first_name), clientInfo.getFirstName()));
        questionDataContainerList.add(new DoubleTextViewContainer(getString(R.string.last_name), clientInfo.getLastName()));
        questionDataContainerList.add(new DoubleTextViewContainer(getString(R.string.age), clientInfo.getAge().toString()));
        questionDataContainerList.add(new DoubleTextViewContainer(getString(R.string.contact_number), clientInfo.getContactNumber()));
        questionDataContainerList.add(new DoubleTextViewContainer(getString(R.string.caregiver_present_for_interview), StringsUtil.boolToText(clientInfo.isCaregiverPresentForInterview())));
        questionDataContainerList.add(new DoubleTextViewContainer(getString(R.string.caregiver_contact_number), clientInfo.getCaregiverContactNumber()));
        questionDataContainerList.add(new DoubleTextViewContainer(getString(R.string.disabilities), clientInfo.getDisabilityListFormatted()));

        questionDataContainerList.add(new HeaderViewContainer(getString((R.string.health))));
        questionDataContainerList.add(new DoubleTextViewContainer(getString(R.string.please_rate_how_you_consider_the_client_s_health_to_be), clientInfo.getRateHealth()));
        questionDataContainerList.add(new DoubleTextViewContainer(getString(R.string.please_describe_what_they_require), clientInfo.getDescribeHealth()));
        questionDataContainerList.add(new DoubleTextViewContainer(getString(R.string.individual_goal), clientInfo.getSetGoalForHealth()));

        questionDataContainerList.add(new HeaderViewContainer(getString((R.string.education))));
        questionDataContainerList.add(new DoubleTextViewContainer(getString(R.string.please_rate_how_you_consider_the_client_s_education_status_to_be), clientInfo.getRateEducation()));
        questionDataContainerList.add(new DoubleTextViewContainer(getString(R.string.please_describe_what_they_require), clientInfo.getDescribeEducation()));
        questionDataContainerList.add(new DoubleTextViewContainer(getString(R.string.individual_goal), clientInfo.getSetGoalForEducation()));

        questionDataContainerList.add(new HeaderViewContainer(getString((R.string.social_status))));
        questionDataContainerList.add(new DoubleTextViewContainer(getString(R.string.please_rate_how_you_consider_the_client_s_social_status_to_be), clientInfo.getRateSocialStatus()));
        questionDataContainerList.add(new DoubleTextViewContainer(getString(R.string.please_describe_what_they_require), clientInfo.getDescribeSocialStatus()));
        questionDataContainerList.add(new DoubleTextViewContainer(getString(R.string.individual_goal), clientInfo.getSetGoalForSocialStatus()));

        if (visitsList.isEmpty()) {
            return questionDataContainerList;
        }

        // add visit info to the data container list, if the list is not empty
        questionDataContainerList.add(new HeaderViewContainer(getString(R.string.visits)));
        questionDataContainerList.add(new DividerViewContainer());
        for (final VisitGeneralQuestionSetData visitGeneralQuestionSetData: visitsList) {
            ClickableViewContainer.ClickableViewHolderBehavior clickableViewHolderBehavior = new ClickableViewContainer.ClickableViewHolderBehavior() {
                @Override
                public void onClick() {
                    clientPageFragmentInterface.swapToVisitPage(visitGeneralQuestionSetData);
                }
            };

            questionDataContainerList.add(new ClickableViewContainer(getString(R.string.visits_list, visitGeneralQuestionSetData.getDateOfVisit()), clickableViewHolderBehavior));
            questionDataContainerList.add(new DividerViewContainer());
        }

        return questionDataContainerList;
    }

    @Override
    public void setPresenter(ClientPageContract.Presenter presenter) {
        clientListPresenter = presenter;
    }

    public static ClientPageFragment newInstance(ClientInfo clientInfo) {
        ClientPageFragment clientPageFragment = new ClientPageFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(CLIENT_PAGE_BUNDLE, clientInfo);
        clientPageFragment.setArguments(bundle);

        return clientPageFragment;
    }

    public static String getFragmentTag() {
        return ClientPageFragment.class.getSimpleName();
    }

    public interface ClientPageFragmentInterface {
        void swapToVisitPage(VisitGeneralQuestionSetData visitGeneralQuestionSetData);
        void swapToReferralPage(ClientInfo clientInfo);
    }
}
