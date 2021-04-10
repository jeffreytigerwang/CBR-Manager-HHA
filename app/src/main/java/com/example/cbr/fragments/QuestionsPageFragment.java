package com.example.cbr.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cbr.adapters.questioninfoadapters.InfoAdapter;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.QuestionDataContainer;
import com.example.cbr.databinding.RecyclerviewTemplateBinding;
import com.example.cbr.fragments.base.BaseFragment;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;
import static com.example.cbr.util.Constants.CAMERA_REQUEST_CODE;

public class QuestionsPageFragment extends BaseFragment {

    private RecyclerviewTemplateBinding binding;

    private InfoAdapter questionsAdapter;

    private static final String QUESTION_PAGE_DATA_CONTAINERS = "questionPageDataContainers";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = RecyclerviewTemplateBinding.inflate(inflater, container, false);

        ArrayList<QuestionDataContainer> questionDataContainerList = getArguments().getParcelableArrayList(QUESTION_PAGE_DATA_CONTAINERS);

        RecyclerView recyclerView = binding.recyclerviewLayout;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);
        questionsAdapter = new InfoAdapter(this, questionDataContainerList);
        recyclerView.setAdapter(questionsAdapter);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            questionsAdapter.onActivityResult(requestCode, resultCode, data);
        }
    }

    public static QuestionsPageFragment newInstance(ArrayList<QuestionDataContainer> questionDataContainerList, int position) {
        Bundle bundle = new Bundle();

        QuestionsPageFragment questionsPageFragment = new QuestionsPageFragment();
        bundle.putParcelableArrayList(QUESTION_PAGE_DATA_CONTAINERS, questionDataContainerList);
        questionsPageFragment.setArguments(bundle);

        return questionsPageFragment;
    }
}
