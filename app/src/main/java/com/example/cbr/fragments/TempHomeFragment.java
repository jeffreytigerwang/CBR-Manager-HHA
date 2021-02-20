package com.example.cbr.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.cbr.databinding.FragmentHomeBinding;
import com.example.cbr.model.ClientInfo;

import java.util.ArrayList;
import java.util.List;

public class TempHomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private TempHomeFragmentInterface tempHomeFragmentInterface;
    private List<ClientInfo> priorityList;
    private List<ClientInfo> outstandingList;

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

        setupPriorityList();
        setupOutstandingList();

        //hardcode example clients for demonstration
        populateLists();


        setupAddNewClientButton();


        return binding.getRoot();
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

    private void setupPriorityList() {
        priorityList = new ArrayList<>();


    }

    private void setupOutstandingList() {
        outstandingList = new ArrayList<>();
    }

    private void setupAddNewClientButton() {

        Button button = binding.buttonNewClient;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempHomeFragmentInterface.swapToNewClient();
            }
        });
    }

    public static TempHomeFragment newInstance() {
        return new TempHomeFragment();
    }

    public static String getFragmentTag() {
        return TempHomeFragment.class.getSimpleName();
    }

    public interface TempHomeFragmentInterface {
        void swapToNewClient();
    }


}
