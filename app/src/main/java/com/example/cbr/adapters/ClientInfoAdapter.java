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
    private ArrayList<TextViewHolderData> clientInfoList = new ArrayList<>();

    private final int TEXT_VIEW_TYPE = 0;
    private final int HEADER_VIEW_TYPE = 1;

    public ClientInfoAdapter(Context context, ClientInfo clientInfo) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.clientInfo = clientInfo;

        generateList();
    }

    private void generateList() {
        clientInfoList.add(new TextViewHolderData(context.getString(R.string.consent_to_interview), boolToYesOrNo(clientInfo.isConsentToInterview())));
        clientInfoList.add(new TextViewHolderData(context.getString(R.string.gps_location), clientInfo.getGpsLocation()));
        clientInfoList.add(new TextViewHolderData(context.getString(R.string.location), clientInfo.getLocation()));
        clientInfoList.add(new TextViewHolderData(context.getString(R.string.village_number), clientInfo.getVillageNumber()));
        clientInfoList.add(new TextViewHolderData(context.getString(R.string.date), clientInfo.getDate()));
        clientInfoList.add(new TextViewHolderData(context.getString(R.string.first_name), clientInfo.getFirstName()));
        clientInfoList.add(new TextViewHolderData(context.getString(R.string.last_name), clientInfo.getLastName()));
        clientInfoList.add(new TextViewHolderData(context.getString(R.string.age), clientInfo.getAge().toString()));
        clientInfoList.add(new TextViewHolderData(context.getString(R.string.contact_number), clientInfo.getContactNumber()));
    }

    private String boolToYesOrNo(boolean bool) {
        if (bool) {
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
        }
        return new TextViewHolder(layoutInflater.inflate(R.layout.recyclerview_text, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case TEXT_VIEW_TYPE:
                ((TextViewHolder) holder).bind(clientInfoList.get(position));
                break;
            case HEADER_VIEW_TYPE:
                ((HeaderViewHolder) holder).bind("-header-");
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return clientInfoList.size();
    }

    class TextViewHolder extends RecyclerView.ViewHolder {
        private TextView firstTextView;
        private TextView secondTextView;

        public TextViewHolder(View itemView) {
            super(itemView);
            firstTextView = itemView.findViewById(R.id.recyclerview_firstText);
            secondTextView = itemView.findViewById(R.id.recyclerview_secondText);
        }

        public void bind(TextViewHolderData textViewHolderData) {
            firstTextView.setText(textViewHolderData.getFirstText());
            secondTextView.setText(textViewHolderData.getSecondText());
        }
    }

    class TextViewHolderData {
        private String firstText;
        private String secondText;

        TextViewHolderData(String firstText, String secondText) {
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

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        private TextView headerTextView;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            headerTextView = itemView.findViewById(R.id.recyclerview_headerText);
        }

        public void bind(String headerText) {
            headerTextView.setText(headerText);
        }
    }

    class HeaderViewHolderData {
        private String headerText;

        HeaderViewHolderData(String headerText) {
            this.headerText = headerText;
        }

        public String getHeaderText() {
            return headerText;
        }
    }
}
