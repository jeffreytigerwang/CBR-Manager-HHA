package com.example.cbr.adapters.questioninfoadapters;

import android.content.Context;
import android.util.Log;

import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.QuestionDataContainer;

import java.util.List;

public class NewReferralAdapter extends BaseInfoAdapter {

    public NewReferralAdapter(Context context, List<QuestionDataContainer> questionDataContainerList) {
        super(context, questionDataContainerList);
    }

    @Override
    void onDataChanged() {
        List<QuestionDataContainer> questionDataContainerList = getQuestionDataContainerList();
        Log.d("testing", "yep");
    }
}
