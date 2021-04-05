package com.example.cbr.adapters.questioninfoadapters;

import android.content.Context;

import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.QuestionDataContainer;

import java.util.ArrayList;

public class QuestionsAdapter extends BaseInfoAdapter {

    private final ArrayList<QuestionDataContainer> questionDataContainerList;

    public QuestionsAdapter(Context context, ArrayList<QuestionDataContainer> questionDataContainerList) {
        super(context, questionDataContainerList);
        this.questionDataContainerList = questionDataContainerList;
    }
}
