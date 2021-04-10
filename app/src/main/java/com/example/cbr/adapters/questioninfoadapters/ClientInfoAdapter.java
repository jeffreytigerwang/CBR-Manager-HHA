package com.example.cbr.adapters.questioninfoadapters;

import android.content.Context;

import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.QuestionDataContainer;

import java.util.List;

public class ClientInfoAdapter extends BaseInfoAdapter {

    public ClientInfoAdapter(Context context, List<QuestionDataContainer> questionDataContainerList) {
        super(context, questionDataContainerList);
    }

    @Override
    public void onDataChanged(int positionChanged, QuestionDataContainer questionDataContainer) {
        // no items that require input from user
    }
}
