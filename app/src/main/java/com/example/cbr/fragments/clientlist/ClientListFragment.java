package com.example.cbr.fragments.clientlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.cbr.databinding.FragmentClientlistBinding;
import com.example.cbr.fragments.base.BaseFragment;

public class ClientListFragment extends BaseFragment implements ClientListContract.View {

    private FragmentClientlistBinding binding;
    private ClientListContract.Presenter clientListPresenter;

    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setPresenter(new ClientListPresenter(this));
        // View binding so that findViewById() doesn't have to be used
        binding = FragmentClientlistBinding.inflate(inflater, container, false);

        setupButton();

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // destroy references to view
        binding = null;
    }

    private void setupButton() {
        binding.sampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clientListPresenter.onButtonClicked();
            }
        });
    }

    @Override
    public void displayString(String string) {
        binding.sampleText.setText(string);
    }


    @Override
    public void setPresenter(ClientListContract.Presenter presenter) {
        // set the presenter so the view can communicate with the presenter
        clientListPresenter = presenter;
    }

    public static ClientListFragment newInstance() {
        return new ClientListFragment();
    }

    public static String getFragmentTag() {
        // return the name of the fragment as a tag, primarily for switching between fragments
        return ClientListFragment.class.getSimpleName();
    }
}
