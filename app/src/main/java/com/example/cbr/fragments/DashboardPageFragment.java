package com.example.cbr.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cbr.adapters.OutstandingListAdapter;
import com.example.cbr.adapters.PriorityListAdapter;
import com.example.cbr.databinding.FragmentDashboardBinding;
import com.example.cbr.models.ClientInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DashboardPageFragment extends Fragment implements DashboardPageContract.View {

    private FragmentDashboardBinding binding;
    private DashboardFragmentInterface dashboardFragmentInterface;
    private List<ClientInfo> priorityList;
    private List<ClientInfo> outstandingList;
    private DashboardPageContract.Presenter presenter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            dashboardFragmentInterface = (DashboardFragmentInterface) context;
        } catch (ClassCastException e) {
            Log.e(getFragmentTag(), "Activity should implement DashboardFragmentInterface");
        }
    }

    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setPresenter(new DashboardPagePresenter(this));

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);

        populatePriorityList();
        populateOutstandingList();

        setupNewClientButton();
        setupSyncButton();


        return binding.getRoot();
    }

    private void setupSyncButton() {
        Button button = binding.dashboardSyncButton;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Syncing...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupNewClientButton() {
        Button button = binding.dashboardNewClientButton;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dashboardFragmentInterface.swapToNewClient();
            }
        });
    }


    private void populatePriorityList() {
        priorityList = new ArrayList<>();

        try {

            priorityList.addAll(presenter.getTopPriority());

        } catch (Exception e) {
            e.printStackTrace();
        }
        setupPriorityListView();

    }

    private void populateOutstandingList() {
        outstandingList = new ArrayList<>();

        try {
            outstandingList.addAll(presenter.getOutstandingReferral());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setupOutstandingListView();
    }

    private void setupPriorityListView() {
        List<String> dateOfLastVisits = new ArrayList<>();

        try {
            dateOfLastVisits.addAll(presenter.getDatesOfLastVisits(priorityList));
        } catch (IOException e) {
            e.printStackTrace();
        }

        RecyclerView recyclerView = binding.dashboardPriorityList;
        LinearLayoutManager priorityLayout = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(priorityLayout);
        PriorityListAdapter priorityListAdapter = new PriorityListAdapter(getActivity(), priorityList,
                dashboardFragmentInterface, dateOfLastVisits);
        recyclerView.setAdapter(priorityListAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                priorityLayout.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void setupOutstandingListView() {

        RecyclerView recyclerView = binding.dashboardOutstandingList;
        LinearLayoutManager outstandingLayout = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(outstandingLayout);

        OutstandingListAdapter outstandingListAdapter = new OutstandingListAdapter(getActivity(), outstandingList, dashboardFragmentInterface);
        recyclerView.setAdapter(outstandingListAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                outstandingLayout.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

    }

    @Override
    public void setPresenter(DashboardPageContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public static DashboardPageFragment newInstance() {
        return new DashboardPageFragment();
    }

    public static String getFragmentTag() {
        return DashboardPageFragment.class.getSimpleName();
    }

    public interface DashboardFragmentInterface {
        void swapToClientPage(ClientInfo clientInfo);
        void swapToNewClient();
    }



}
