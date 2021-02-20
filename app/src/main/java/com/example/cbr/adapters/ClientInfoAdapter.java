package com.example.cbr.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cbr.R;
import com.example.cbr.model.ClientInfo;

import java.util.ArrayList;

public class ClientInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater layoutInflater;
    private Context context;
    private ClientInfo clientInfo;
    private ArrayList<ViewHolderData> clientInfoList = new ArrayList<>();

    private final int TEXT_VIEW_TYPE = 0;
    private final int HEADER_VIEW_TYPE = 1;
    private final int DIVIDER_VIEW_TYPE = 2;

    public ClientInfoAdapter(Context context, ClientInfo clientInfo) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.clientInfo = clientInfo;

        generateList();
    }

    private void generateList() {
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

    private void addTextViewHolder(String firstText, String secondText) {
        clientInfoList.add(new TextViewHolderData(firstText, secondText));
    }

    private void addDivider() {
        clientInfoList.add(new DividerViewHolderData());
    }

    private void addHeader(String text) {
        clientInfoList.add(new HeaderViewHolderData(text));
    }

    private String boolToText(Boolean bool) {
        if (bool == null) {
            return context.getString(R.string.na);
        } else if (bool) {
            return context.getString(R.string.yes);
        } else {
            return context.getString(R.string.no);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case TEXT_VIEW_TYPE:
                return new TextViewHolder(layoutInflater.inflate(R.layout.recyclerview_text, parent, false));
            case HEADER_VIEW_TYPE:
                return new HeaderViewHolder(layoutInflater.inflate(R.layout.recyclerview_header, parent, false));
            case DIVIDER_VIEW_TYPE:
                return new DividerViewHolder(layoutInflater.inflate(R.layout.recyclerview_divider, parent, false));
        }
        return new TextViewHolder(layoutInflater.inflate(R.layout.recyclerview_text, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case TEXT_VIEW_TYPE:
                ((TextViewHolder) holder).bind((TextViewHolderData) clientInfoList.get(position));
                break;
            case HEADER_VIEW_TYPE:
                ((HeaderViewHolder) holder).bind(((HeaderViewHolderData) clientInfoList.get(position)));
            case DIVIDER_VIEW_TYPE:
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return clientInfoList.get(position).getViewType();
    }

    @Override
    public int getItemCount() {
        return clientInfoList.size();
    }

    class TextViewHolder extends RecyclerView.ViewHolder {
        private final TextView firstTextView;
        private final TextView secondTextView;

        public TextViewHolder(View itemView) {
            super(itemView);
            firstTextView = itemView.findViewById(R.id.recyclerview_firstText);
            secondTextView = itemView.findViewById(R.id.recyclerview_secondText);
        }

        public void bind(TextViewHolderData textViewHolderData) {
            firstTextView.setText(textViewHolderData.getFirstText());

            if (textViewHolderData.getSecondText() == null || textViewHolderData.getSecondText().isEmpty()) {
                secondTextView.setText(context.getString(R.string.na));
            } else {
                secondTextView.setText(textViewHolderData.getSecondText());
            }
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        private final TextView headerTextView;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            headerTextView = itemView.findViewById(R.id.recyclerview_headerText);
        }

        public void bind(HeaderViewHolderData headerViewHolderData) {
            headerTextView.setText(headerViewHolderData.getHeaderText());
        }
    }

    class DividerViewHolder extends RecyclerView.ViewHolder {
        public DividerViewHolder(View itemView) {
            super(itemView);
        }
    }

    class ViewHolderData {
        private final int viewType;

        ViewHolderData(int viewType) {
            this.viewType = viewType;
        }

        public int getViewType() {
            return viewType;
        }
    }

    class TextViewHolderData extends ViewHolderData {
        private final String firstText;
        private final String secondText;

        TextViewHolderData(String firstText, String secondText) {
            super(TEXT_VIEW_TYPE);
            this.firstText = firstText;
            this.secondText = secondText;
        }

        public String getFirstText() {
            return firstText;
        }

        public String getSecondText() {
            return secondText;
        }
    }

    class HeaderViewHolderData extends ViewHolderData {
        private final String headerText;

        HeaderViewHolderData(String headerText) {
            super(HEADER_VIEW_TYPE);
            this.headerText = headerText;
        }

        public String getHeaderText() {
            return headerText;
        }
    }

    class DividerViewHolderData extends ViewHolderData {
        DividerViewHolderData() {
            super(DIVIDER_VIEW_TYPE);
        }
    }
}
