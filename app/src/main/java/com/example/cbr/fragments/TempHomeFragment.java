package com.example.cbr.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.cbr.activities.NewVisitActivity;
import com.example.cbr.databinding.FragmentHomeBinding;
import com.example.cbr.models.VisitRecord;

public class TempHomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private TempHomeFragmentInterface tempHomeFragmentInterface;

    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        setupAddNewClientButton();
        setupAddNewVisitButton();
        setupDashboardButton();
        setupAddNewReferralButton();
        setupViewAllClientsButton();
        setupSyncButton();

        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            tempHomeFragmentInterface = (TempHomeFragmentInterface) context;
        } catch (ClassCastException e) {
            Log.e(getFragmentTag(), "Activity should implement tempHomeFragmentInterface");
        }
    }

    private void setupSyncButton() {
        Button button = binding.buttonSync;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void setupViewAllClientsButton() {
        Button button = binding.buttonClientList;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempHomeFragmentInterface.swapToClientList();
            }
        });
    }

    private void setupAddNewReferralButton() {
        Button button = binding.buttonNewReferral;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void setupDashboardButton() {
        Button button = binding.buttonDashboard;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void setupAddNewVisitButton() {
        Button button = binding.buttonNewVisit;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = NewVisitActivity.makeLaunchIntent(
                        getActivity(),
                        -1,
                        new VisitRecord()
                );
                startActivity(intent);
            }
        });
    }

    private void setupAddNewClientButton() {

        Button button = binding.buttonNewClient;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        void swapToClientList();
    }
}
