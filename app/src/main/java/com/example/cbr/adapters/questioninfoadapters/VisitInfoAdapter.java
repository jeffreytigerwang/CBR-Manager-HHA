package com.example.cbr.adapters.questioninfoadapters;

import android.content.Context;

import com.example.cbr.R;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.QuestionDataContainer;
import com.example.cbr.models.VisitGeneralQuestionSetData;

import java.util.List;

public class VisitInfoAdapter extends BaseInfoAdapter {

    public VisitInfoAdapter(Context context, List<QuestionDataContainer> questionDataContainerList) {
        super(context, questionDataContainerList);

    }

    @Override
    void onDataEntered() {

    }
}
