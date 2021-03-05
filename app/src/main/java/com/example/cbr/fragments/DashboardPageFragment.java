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

public class DashboardPageFragment extends Fragment {

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


        ClientInfo priorityPerson1 = new ClientInfo();
        priorityPerson1.setFirstName("John");
        priorityPerson1.setLastName("Smith");
        priorityPerson1.setRateHealth("Critical Level Health");
        priorityPerson1.setZoneLocation("BidiBidi Zone 1");
        
        ClientInfo priorityPerson2 = new ClientInfo();
        priorityPerson2.setFirstName("Jane");
        priorityPerson2.setLastName("Doe");
        priorityPerson2.setRateHealth("Critical Level Health, Critical Level Education");
        priorityPerson2.setZoneLocation("Palorinya Zone 2");

        ClientInfo priorityPerson3 = new ClientInfo();
        priorityPerson3.setFirstName("Jackson");
        priorityPerson3.setLastName("Lee");
        priorityPerson3.setRateHealth("Critical Level Social Status");
        priorityPerson3.setZoneLocation("BidiBidi Zone 4");

        ClientInfo outstandingPerson1 = new ClientInfo();
        outstandingPerson1.setFirstName("William");
        outstandingPerson1.setLastName("Liu");
        outstandingPerson1.setZoneLocation("Palorinya Basecamp");

        ClientInfo outstandingPerson2 = new ClientInfo();
        outstandingPerson2.setFirstName("Elizabeth");
        outstandingPerson2.setLastName("Nguyen");
        outstandingPerson2.setZoneLocation("Palorinya Zone 3");


        ClientInfo outstandingPerson3 = new ClientInfo();
        outstandingPerson3.setFirstName("Cameron");
        outstandingPerson3.setLastName("Ng");
        outstandingPerson3.setZoneLocation("BidiBidi Zone 5");


        priorityList.add(priorityPerson1);
        priorityList.add(priorityPerson2);
        priorityList.add(priorityPerson3);


        outstandingList.add(outstandingPerson1);
        outstandingList.add(outstandingPerson2);
        outstandingList.add(outstandingPerson3);

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

    public static DashboardPageFragment newInstance() {
        return new DashboardPageFragment();
    }

    public static String getFragmentTag() {
        return DashboardPageFragment.class.getSimpleName();
    }

    public interface TempHomeFragmentInterface {
        void swapToClientPage(ClientInfo clientInfo);
        void swapToNewClient();
    }



}
