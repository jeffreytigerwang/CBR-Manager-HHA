package com.example.cbr.adapters.questioninfoadapters.questiondatacontainers;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class QuestionDataContainer implements Parcelable, Serializable {
    public static final int SINGLE_TEXT_VIEW_TYPE = 0;
    public static final int DOUBLE_TEXT_VIEW_TYPE = 1;
    public static final int HEADER_VIEW_TYPE = 2;
    public static final int CLICKABLE_VIEW_TYPE = 3;
    public static final int DIVIDER_VIEW_TYPE = 4;
    public static final int EDIT_TEXT_VIEW_TYPE = 5;
    public static final int RADIO_GROUP_VIEW_TYPE = 6;
    public static final int SPINNER_VIEW_TYPE = 7;
    public static final int CHECK_BOX_VIEW_TYPE = 8;

    private final int viewType;

    public QuestionDataContainer(int viewType) {
        this.viewType = viewType;
    }

    protected QuestionDataContainer(Parcel in) {
        viewType = in.readInt();
    }

    public int getViewType() {
        return viewType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(viewType);
    }

    public static final Creator<QuestionDataContainer> CREATOR = new Creator<QuestionDataContainer>() {
        @Override
        public QuestionDataContainer createFromParcel(Parcel in) {
            return new QuestionDataContainer(in);
        }

        @Override
        public QuestionDataContainer[] newArray(int size) {
            return new QuestionDataContainer[size];
        }
    };
}