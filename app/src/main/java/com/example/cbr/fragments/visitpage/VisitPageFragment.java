package com.example.cbr.fragments.visitpage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cbr.R;
import com.example.cbr.adapters.questioninfoadapters.InfoAdapter;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.DividerViewContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.DoubleTextViewContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.HeaderViewContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.QuestionDataContainer;
import com.example.cbr.databinding.FragmentVisitpageBinding;
import com.example.cbr.fragments.base.BaseFragment;
import com.example.cbr.models.VisitGeneralQuestionSetData;

import java.util.ArrayList;
import java.util.List;

public class VisitPageFragment extends BaseFragment implements VisitPageContract.View {

    private FragmentVisitpageBinding binding;
    private VisitPageContract.Presenter visitPagePresenter;



    private VisitGeneralQuestionSetData visitInfo;
    private List<VisitGeneralQuestionSetData> visitsList;
    private InfoAdapter visitInfoAdapter;

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
        visitInfoAdapter = new InfoAdapter(this, generateDataContainerList());
        recyclerView.setAdapter(visitInfoAdapter);
    }

    private List<QuestionDataContainer> generateDataContainerList() {
        List<QuestionDataContainer> questionDataContainerList = new ArrayList<>();

        questionDataContainerList.add(new HeaderViewContainer(getString(R.string.visit_details)));

        questionDataContainerList.add(new DoubleTextViewContainer(getString(R.string.client_id), visitInfo.getClientId().toString()));
        questionDataContainerList.add(new DoubleTextViewContainer(getString(R.string.visit_id), visitInfo.getVisitId().toString()));

        questionDataContainerList.add(new DividerViewContainer());
        questionDataContainerList.add(new DoubleTextViewContainer(getString(R.string.visit_purpose), visitInfo.getPurposeOfVisit()));
        questionDataContainerList.add(new DoubleTextViewContainer(getString(R.string.visit_date), visitInfo.getDateOfVisit().toString()));
        questionDataContainerList.add(new DoubleTextViewContainer(getString(R.string.worker_name), visitInfo.getWorkerName()));
        questionDataContainerList.add(new DoubleTextViewContainer(getString(R.string.gps_location), visitInfo.getVisitGpsLocation()));
        questionDataContainerList.add(new DoubleTextViewContainer(getString(R.string.village_number), visitInfo.getVillageNumber()));
        questionDataContainerList.add(new DoubleTextViewContainer(getString(R.string.zone_location), visitInfo.getVisitZoneLocation()));

        return questionDataContainerList;
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
