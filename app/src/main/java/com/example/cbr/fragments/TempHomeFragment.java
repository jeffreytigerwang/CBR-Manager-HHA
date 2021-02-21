package com.example.cbr.fragments;

import android.content.Context;
import android.os.Bundle;
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
import com.example.cbr.databinding.FragmentHomeBinding;
import com.example.cbr.models.ClientInfo;

import java.util.ArrayList;

public class TempHomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private TempHomeFragmentInterface tempHomeFragmentInterface;
    private ArrayList<ClientInfo> priorityList;
    private ArrayList<ClientInfo> outstandingList;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            tempHomeFragmentInterface = (TempHomeFragmentInterface) context;
        } catch (ClassCastException e) {
            Log.e(getFragmentTag(), "Activity should implement tempHomeFragmentInterface");
        }
    }

    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        priorityList = new ArrayList<>();
        outstandingList = new ArrayList<>();


        //hardcode example clients for demonstration
        populateLists();

        setupPriorityListView();
        setupOutstandingList();
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
                tempHomeFragmentInterface.swapToNewClient();
            }
        });
    }


    private void populateLists() {
        ClientInfo john = new ClientInfo(true, "Sample text0", "sample text0", "sample text0",
                "sample text0", "sample text0", "sample text0", 40, "sample text0",
                true, "sample text0", true, true, true, true, true, true, true, true, true, true, "sample text0", "sample text0", "sample text0", "sample text0", "sample text0", "sample text0", "sample text0", "sample text0", "sample text0");

        ClientInfo john1 = new ClientInfo(true, "sample text1", "sample text1", "sample text1",
                "sample text1", "sample text1", "sample text1", 40, "sample text1",
                true, "sample text1", true, true, true, true, true, true, true, true, true, true, "sample text1", "sample text1", "sample text1", "sample text1", "sample text1", "sample text1", "sample text1", "sample text1", "sample text1");

        ClientInfo john2 = new ClientInfo(true, "sample text2", "sample text2", "sample text2",
                "sample text2", "sample text2", "sample text2", 40, "sample text2",
                true, "sample text2", true, true, true, true, true, true, true, true, false, true, "sample text2", "sample text2", "sample text2", "sample text2", "sample text2", "sample text2", "sample text2", "sample text2", "sample text2");

        ClientInfo empty = new ClientInfo();
        ClientInfo empty1 = new ClientInfo();
        ClientInfo empty2 = new ClientInfo();
        ClientInfo empty3 = new ClientInfo();

        priorityList.add(john);
        priorityList.add(john1);
        priorityList.add(john2);
        priorityList.add(empty);
        priorityList.add(empty1);
        priorityList.add(empty2);
        priorityList.add(empty3);

        outstandingList.add(john);
        outstandingList.add(john1);
        outstandingList.add(john2);
        outstandingList.add(empty);
        outstandingList.add(empty1);
        outstandingList.add(empty2);
        outstandingList.add(empty3);
    }

    private void setupPriorityListView() {

        RecyclerView recyclerView = binding.dashboardPriorityList;
        LinearLayoutManager priorityLayout = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(priorityLayout);
        PriorityListAdapter priorityListAdapter = new PriorityListAdapter(getActivity(), priorityList, tempHomeFragmentInterface);
        recyclerView.setAdapter(priorityListAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                priorityLayout.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void setupOutstandingList() {

        RecyclerView recyclerView = binding.dashboardOutstandingList;
        LinearLayoutManager outstandingLayout = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(outstandingLayout);
        OutstandingListAdapter outstandingListAdapter = new OutstandingListAdapter(getActivity(), outstandingList, tempHomeFragmentInterface);
        recyclerView.setAdapter(outstandingListAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                outstandingLayout.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

    }

    public static TempHomeFragment newInstance() {
        return new TempHomeFragment();
    }

    public static String getFragmentTag() {
        return TempHomeFragment.class.getSimpleName();
    }

    public interface TempHomeFragmentInterface {
        void swapToClientPage(ClientInfo clientInfo);
        void swapToNewClient();
    }



}
