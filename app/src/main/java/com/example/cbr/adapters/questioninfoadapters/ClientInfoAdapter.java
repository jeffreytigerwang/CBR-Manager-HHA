package com.example.cbr.adapters.questioninfoadapters;

import android.content.Context;

import com.example.cbr.R;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.QuestionDataContainer;
import com.example.cbr.fragments.clientpage.ClientPageFragment.ClientPageFragmentInterface;
import com.example.cbr.models.ClientInfo;
import com.example.cbr.models.VisitGeneralQuestionSetData;
import com.example.cbr.util.StringsUtil;

import java.util.List;

public class ClientInfoAdapter extends BaseInfoAdapter {

    public ClientInfoAdapter(Context context, List<QuestionDataContainer> questionDataContainerList) {
        super(context, questionDataContainerList);
    }

    @Override
    public void onDataEntered() {

    }
}
