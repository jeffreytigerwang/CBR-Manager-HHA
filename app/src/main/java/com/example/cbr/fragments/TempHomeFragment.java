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

public class TempHomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private TempHomeFragmentInterface tempHomeFragmentInterface;

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

        setupAddNewClientButton();

        return binding.getRoot();
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
