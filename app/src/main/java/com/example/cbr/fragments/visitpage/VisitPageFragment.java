package com.example.cbr.fragments.visitpage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cbr.adapters.questioninfoadapters.VisitInfoAdapter;
import com.example.cbr.databinding.FragmentVisitpageBinding;
import com.example.cbr.fragments.base.BaseFragment;
import com.example.cbr.models.VisitGeneralQuestionSetData;

import java.util.List;

public class VisitPageFragment extends BaseFragment implements VisitPageContract.View {

    private FragmentVisitpageBinding binding;
    private VisitPageContract.Presenter visitPagePresenter;



    private VisitGeneralQuestionSetData visitInfo;
    private List<VisitGeneralQuestionSetData> visitsList;
    private VisitInfoAdapter visitInfoAdapter;

    private static final String VISIT_PAGE_BUNDLE = "visitPageBundle";

    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setPresenter(new VisitPagePresenter(this));
        binding = FragmentVisitpageBinding.inflate(inflater, container, false);

        visitInfo = (VisitGeneralQuestionSetData) getArguments().getSerializable(VISIT_PAGE_BUNDLE);
        setupRecyclerView();

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = binding.visitpageList;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);
        visitInfoAdapter = new VisitInfoAdapter(getActivity(), visitInfo);
        recyclerView.setAdapter(visitInfoAdapter);
    }

    @Override
    public void setPresenter(VisitPageContract.Presenter presenter) {
        visitPagePresenter = presenter;
    }

    public static VisitPageFragment newInstance(VisitGeneralQuestionSetData visitGeneralQuestionSetData) {
        VisitPageFragment visitPageFragment = new VisitPageFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(VISIT_PAGE_BUNDLE, visitGeneralQuestionSetData);
        visitPageFragment.setArguments(bundle);

        return visitPageFragment;
    }

    public static String getFragmentTag() {
        return VisitPageFragment.class.getSimpleName();
    }
}
