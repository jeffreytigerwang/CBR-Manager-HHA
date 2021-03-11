package com.example.cbr.adapters.questioninfoadapters;

import android.content.Context;
import android.util.Log;

import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.QuestionDataContainer;

import java.util.List;

public class QuestionsAdapter extends BaseInfoAdapter {

    private final QuestionsFragmentPagerAdapter.ViewPagerContainer viewPagerContainer;

    public QuestionsAdapter(Context context, QuestionsFragmentPagerAdapter.ViewPagerContainer viewPagerContainer) {
        super(context, viewPagerContainer.getViewHolderDataList());
        this.viewPagerContainer = viewPagerContainer;
    }

    @Override
    void onDataChanged(int positionChanged) {
        viewPagerContainer.getOnViewPagerChangedListener().onChanged(positionChanged);
    }
}
