package com.example.cbr.adapters.questioninfoadapters;

import android.content.Context;

import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.QuestionDataContainer;

public class QuestionsAdapter extends BaseInfoAdapter {

    private final QuestionsFragmentPagerAdapter.ViewPagerContainer viewPagerContainer;

    public QuestionsAdapter(Context context, QuestionsFragmentPagerAdapter.ViewPagerContainer viewPagerContainer) {
        super(context, viewPagerContainer.getViewHolderDataList());
        this.viewPagerContainer = viewPagerContainer;
    }

    @Override
    void onDataChanged(int positionChanged, QuestionDataContainer questionDataContainer) {
        viewPagerContainer.getOnViewPagerChangedListener().onChanged(positionChanged, questionDataContainer);
    }
}
