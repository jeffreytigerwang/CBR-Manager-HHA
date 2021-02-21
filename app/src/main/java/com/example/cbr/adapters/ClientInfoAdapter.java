package com.example.cbr.adapters;

import android.content.Context;
import android.view.View;

import com.example.cbr.R;
import com.example.cbr.fragments.clientpage.ClientPageFragment.ClientPageFragmentInterface;
import com.example.cbr.models.ClientInfo;
import com.example.cbr.models.VisitGeneralQuestionSetData;

import java.util.List;

public class ClientInfoAdapter extends BaseInfoAdapter {

    private final ClientInfo clientInfo;
    private final List<VisitGeneralQuestionSetData> visitsList;
    private final ClientPageFragmentInterface clientPageFragmentInterface;

    public ClientInfoAdapter(Context context, ClientInfo clientInfo, List<VisitGeneralQuestionSetData> visitsList, ClientPageFragmentInterface clientPageFragmentInterface) {
        super(context);
        this.clientInfo = clientInfo;
        this.visitsList = visitsList;
        this.clientPageFragmentInterface = clientPageFragmentInterface;

        generateList();
    }

    @Override
    protected void generateList() {
        addClientInfo();
        addVisitsInfo();
    }

    private void addClientInfo() {
        addHeaderViewType(context.getString(R.string.basic_information));
        addTextViewType(context.getString(R.string.consent_to_interview), boolToText(clientInfo.isConsentToInterview()));
        addTextViewType(context.getString(R.string.gps_location), clientInfo.getGpsLocation());
        addTextViewType(context.getString(R.string.location), clientInfo.getZoneLocation());
        addTextViewType(context.getString(R.string.village_number), clientInfo.getVillageNumber());
        addTextViewType(context.getString(R.string.date), clientInfo.getDateJoined());
        addTextViewType(context.getString(R.string.first_name), clientInfo.getFirstName());
        addTextViewType(context.getString(R.string.last_name), clientInfo.getLastName());
        addTextViewType(context.getString(R.string.age), clientInfo.getAge().toString());
        addTextViewType(context.getString(R.string.contact_number), clientInfo.getContactNumber());
        addTextViewType(context.getString(R.string.caregiver_present_for_interview), boolToText(clientInfo.isCaregiverPresentForInterview()));
        addTextViewType(context.getString(R.string.caregiver_contact_number), clientInfo.getCaregiverContactNumber());
        addTextViewType(context.getString(R.string.disabilities), clientInfo.getDisabilityListFormatted().toString());

        addHeaderViewType(context.getString(R.string.health));
        addTextViewType(context.getString(R.string.please_rate_how_you_consider_the_client_s_health_to_be), clientInfo.getRateHealth());
        addTextViewType(context.getString(R.string.please_describe_what_they_require), clientInfo.getDescribeHealth());
        addTextViewType(context.getString(R.string.individual_goal), clientInfo.getSetGoalForHealth());

        addHeaderViewType(context.getString(R.string.education));
        addTextViewType(context.getString(R.string.please_rate_how_you_consider_the_client_s_education_status_to_be), clientInfo.getRateEducation());
        addTextViewType(context.getString(R.string.please_describe_what_they_require), clientInfo.getDescribeEducation());
        addTextViewType(context.getString(R.string.individual_goal), clientInfo.getSetGoalForEducation());

        addHeaderViewType(context.getString(R.string.social_status));
        addTextViewType(context.getString(R.string.please_rate_how_you_consider_the_client_s_social_status_to_be), clientInfo.getRateSocialStatus());
        addTextViewType(context.getString(R.string.please_describe_what_they_require), clientInfo.getDescribeSocialStatus());
        addTextViewType(context.getString(R.string.individual_goal), clientInfo.getSetGoalForSocialStatus());
    }

    private void addVisitsInfo() {
        if (visitsList.isEmpty()) {
            return;
        }

        addHeaderViewType(context.getString(R.string.visits));
        addDividerViewType();
        for (final VisitGeneralQuestionSetData visitGeneralQuestionSetData: visitsList) {
            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clientPageFragmentInterface.swapToVisitPage(visitGeneralQuestionSetData);
                }
            };

            addClickableViewType(context.getString(R.string.visits_list, visitGeneralQuestionSetData.getDateOfVisit()), listener);
            addDividerViewType();
        }
    }
}
