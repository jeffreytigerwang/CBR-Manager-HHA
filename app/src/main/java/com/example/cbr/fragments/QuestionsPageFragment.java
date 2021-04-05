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

import com.example.cbr.adapters.questioninfoadapters.QuestionsAdapter;
import com.example.cbr.adapters.questioninfoadapters.QuestionsFragmentPagerAdapter;
import com.example.cbr.databinding.RecyclerviewTemplateBinding;
import com.example.cbr.fragments.base.BaseFragment;

import static android.app.Activity.RESULT_OK;
import static com.example.cbr.util.Constants.CAMERA_REQUEST_CODE;

public class QuestionsPageFragment extends BaseFragment {

    private RecyclerviewTemplateBinding binding;

    private QuestionsAdapter questionsAdapter;
    private QuestionsFragmentPagerAdapter.ViewPagerContainer viewPagerContainer;

    private static final String QUESTION_PAGE_DATA_CONTAINERS = "questionPageDataContainers";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = RecyclerviewTemplateBinding.inflate(inflater, container, false);

        viewPagerContainer = (QuestionsFragmentPagerAdapter.ViewPagerContainer) getArguments().getSerializable(QUESTION_PAGE_DATA_CONTAINERS);

        RecyclerView recyclerView = binding.recyclerviewLayout;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);
        questionsAdapter = new QuestionsAdapter(getActivity(), viewPagerContainer);
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

    public static QuestionsPageFragment newInstance(QuestionsFragmentPagerAdapter.ViewPagerContainer viewPagerContainer) {
        Bundle bundle = new Bundle();

        QuestionsPageFragment questionsPageFragment = new QuestionsPageFragment();
        bundle.putSerializable(QUESTION_PAGE_DATA_CONTAINERS, viewPagerContainer);
        questionsPageFragment.setArguments(bundle);

        return questionsPageFragment;
    }
}
