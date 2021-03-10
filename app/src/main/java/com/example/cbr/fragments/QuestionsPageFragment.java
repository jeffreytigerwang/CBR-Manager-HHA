package com.example.cbr.fragments;

import android.os.Bundle;

import com.example.cbr.fragments.base.BaseFragment;

public class QuestionsPageFragment extends BaseFragment {

    public static QuestionsPageFragment newInstance() {
        Bundle bundle = new Bundle();

        QuestionsPageFragment questionsPageFragment = new QuestionsPageFragment();
        questionsPageFragment.setArguments(bundle);

        return questionsPageFragment;
    }
}
