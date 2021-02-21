package com.example.cbr.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cbr.R;
import com.example.cbr.model.ClientInfo;

import java.util.ArrayList;

public class ClientInfoAdapter extends BaseInfoAdapter {

    private final ClientInfo clientInfo;

    public ClientInfoAdapter(Context context, ClientInfo clientInfo) {
        super(context);
        this.clientInfo = clientInfo;

        generateList();
    }

    @Override
    protected void generateList() {
        addHeader(context.getString(R.string.basic_information));
        addTextViewHolder(context.getString(R.string.consent_to_interview), boolToText(clientInfo.isConsentToInterview()));
        addTextViewHolder(context.getString(R.string.gps_location), clientInfo.getGpsLocation());
        addTextViewHolder(context.getString(R.string.location), clientInfo.getLocation());
        addTextViewHolder(context.getString(R.string.village_number), clientInfo.getVillageNumber());
        addTextViewHolder(context.getString(R.string.date), clientInfo.getDate());
        addTextViewHolder(context.getString(R.string.first_name), clientInfo.getFirstName());
        addTextViewHolder(context.getString(R.string.last_name), clientInfo.getLastName());
        addTextViewHolder(context.getString(R.string.age), clientInfo.getAge().toString());
        addTextViewHolder(context.getString(R.string.contact_number), clientInfo.getContactNumber());

        addTextViewHolder(context.getString(R.string.caregiver_present_for_interview), boolToText(clientInfo.isCaregiverPresentForInterview()));
        addTextViewHolder(context.getString(R.string.caregiver_contact_number), clientInfo.getCaregiverContactNumber());

        addDivider();
        addTextViewHolder(context.getString(R.string.disabilities), clientInfo.getDisabilityList().toString());

        addHeader(context.getString(R.string.health));
        addTextViewHolder(context.getString(R.string.please_rate_how_you_consider_the_client_s_health_to_be), clientInfo.getRateHealth());
        addTextViewHolder(context.getString(R.string.please_describe_what_they_require), clientInfo.getDescribeHealth());
        addTextViewHolder(context.getString(R.string.individual_goal), clientInfo.getSetGoalForHealth());

        addHeader(context.getString(R.string.education));
        addTextViewHolder(context.getString(R.string.please_rate_how_you_consider_the_client_s_education_status_to_be), clientInfo.getRateEducation());
        addTextViewHolder(context.getString(R.string.please_describe_what_they_require), clientInfo.getDescribeEducation());
        addTextViewHolder(context.getString(R.string.individual_goal), clientInfo.getSetGoalForEducation());

        addHeader(context.getString(R.string.social_status));
        addTextViewHolder(context.getString(R.string.please_rate_how_you_consider_the_client_s_social_status_to_be), clientInfo.getRateSocialStatus());
        addTextViewHolder(context.getString(R.string.please_describe_what_they_require), clientInfo.getDescribeSocialStatus());
        addTextViewHolder(context.getString(R.string.individual_goal), clientInfo.getSetGoalForSocialStatus());
    }
}
