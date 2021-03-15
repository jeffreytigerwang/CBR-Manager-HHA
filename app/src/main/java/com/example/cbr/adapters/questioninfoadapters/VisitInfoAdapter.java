package com.example.cbr.adapters.questioninfoadapters;

import android.content.Context;

import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.QuestionDataContainer;

import java.util.List;

public class VisitInfoAdapter extends BaseInfoAdapter {

    public VisitInfoAdapter(Context context, List<QuestionDataContainer> questionDataContainerList) {
        super(context, questionDataContainerList);

    }

    @Override
    void onDataChanged(int positionChanged, QuestionDataContainer questionDataContainer) {
        // no items that require input from user
    }
}