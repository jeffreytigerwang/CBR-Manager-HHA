package com.example.cbr.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cbr.adapters.PriorityListAdapter;
import com.example.cbr.adapters.questioninfoadapters.BaseInfoAdapter;
import com.example.cbr.adapters.questioninfoadapters.ClientInfoAdapter;
import com.example.cbr.adapters.questioninfoadapters.NewReferralAdapter;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.QuestionDataContainer;
import com.example.cbr.databinding.RecyclerviewTemplateBinding;
import com.example.cbr.fragments.base.BaseFragment;
import com.example.cbr.models.ClientInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuestionsPageFragment extends BaseFragment {

    private RecyclerviewTemplateBinding binding;

    private List<QuestionDataContainer> questionDataContainerList;

    private static final String QUESTION_PAGE_DATA_CONTAINERS = "questionPageDataContainers";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = RecyclerviewTemplateBinding.inflate(inflater, container, false);

        questionDataContainerList = getArguments().getParcelableArrayList(QUESTION_PAGE_DATA_CONTAINERS);

        RecyclerView recyclerView = binding.recyclerviewLayout;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);
        NewReferralAdapter newReferralAdapter = new NewReferralAdapter(getActivity(), questionDataContainerList);
        recyclerView.setAdapter(newReferralAdapter);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public static QuestionsPageFragment newInstance(ArrayList<QuestionDataContainer> viewHolderDataList) {
        Bundle bundle = new Bundle();

        QuestionsPageFragment questionsPageFragment = new QuestionsPageFragment();
        bundle.putParcelableArrayList(QUESTION_PAGE_DATA_CONTAINERS, viewHolderDataList);
        questionsPageFragment.setArguments(bundle);

        return questionsPageFragment;
    }
}
