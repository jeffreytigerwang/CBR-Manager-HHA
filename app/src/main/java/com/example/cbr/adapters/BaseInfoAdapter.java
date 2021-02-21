package com.example.cbr.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cbr.R;

import java.util.ArrayList;

/**
 * Base class for adapters that display information, currently supports headers, dividers, and two text fields
 * Subclasses should implement generateList() by calling addTextViewHolder(), addDivider(), etc in order of appearance in generateList()
 */
public abstract class BaseInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    LayoutInflater layoutInflater;
    private final ArrayList<ViewHolderData> infoList = new ArrayList<>();

    final int TEXT_VIEW_TYPE = 0;
    final int HEADER_VIEW_TYPE = 1;
    final int DIVIDER_VIEW_TYPE = 2;

    BaseInfoAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    /**
     * Implement and call generateList() in the subclass
     */
    abstract void generateList();

    void addTextViewHolder(String firstText, String secondText) {
        infoList.add(new TextViewHolderData(firstText, secondText));
    }

    void addDivider() {
        infoList.add(new DividerViewHolderData());
    }

    void addHeader(String text) {
        infoList.add(new HeaderViewHolderData(text));
    }

    String boolToText(Boolean bool) {
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
                return new ClientInfoAdapter.TextViewHolder(layoutInflater.inflate(R.layout.recyclerview_text, parent, false));
            case HEADER_VIEW_TYPE:
                return new ClientInfoAdapter.HeaderViewHolder(layoutInflater.inflate(R.layout.recyclerview_header, parent, false));
            case DIVIDER_VIEW_TYPE:
                return new ClientInfoAdapter.DividerViewHolder(layoutInflater.inflate(R.layout.recyclerview_divider, parent, false));
        }
        return new ClientInfoAdapter.TextViewHolder(layoutInflater.inflate(R.layout.recyclerview_text, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case TEXT_VIEW_TYPE:
                ((TextViewHolder) holder).bind((TextViewHolderData) infoList.get(position));
                break;
            case HEADER_VIEW_TYPE:
                ((HeaderViewHolder) holder).bind(((HeaderViewHolderData) infoList.get(position)));
            case DIVIDER_VIEW_TYPE:
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return infoList.get(position).getViewType();
    }

    @Override
    public int getItemCount() {
        return infoList.size();
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
