package com.example.cbr.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.cbr.adapters.BaseInfoAdapter;
import com.example.cbr.databinding.FragmentClientpageBinding;
import com.example.cbr.databinding.RecyclerviewTemplateBinding;
import com.example.cbr.fragments.base.BaseFragment;
import com.example.cbr.fragments.clientpage.ClientPagePresenter;
import com.example.cbr.models.ClientInfo;

import java.util.List;

public class QuestionsPageFragment extends BaseFragment {

    private RecyclerviewTemplateBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = RecyclerviewTemplateBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public static QuestionsPageFragment newInstance(List<BaseInfoAdapter.ViewHolderData> viewHolderDataList) {
        Bundle bundle = new Bundle();

        QuestionsPageFragment questionsPageFragment = new QuestionsPageFragment();
        questionsPageFragment.setArguments(bundle);

        return questionsPageFragment;
    }
}
