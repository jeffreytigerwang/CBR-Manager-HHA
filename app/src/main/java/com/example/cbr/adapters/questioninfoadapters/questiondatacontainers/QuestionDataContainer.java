package com.example.cbr.adapters.questioninfoadapters.questiondatacontainers;

import java.io.Serializable;
import java.util.List;

public class QuestionDataContainer implements Serializable {
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

    public int getViewType() {
        return viewType;
    }
}