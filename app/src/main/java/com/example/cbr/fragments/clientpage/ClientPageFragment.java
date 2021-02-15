package com.example.cbr.fragments.clientpage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.cbr.databinding.FragmentClientlistBinding;
import com.example.cbr.databinding.FragmentClientpageBinding;
import com.example.cbr.fragments.base.BaseFragment;

public class ClientPageFragment extends BaseFragment implements ClientPageContract.View {

    private FragmentClientpageBinding binding;
    private ClientPageContract.Presenter clientListPresenter;

    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setPresenter(new ClientPagePresenter(this));
        // View binding so that findViewById() doesn't have to be used
        binding = FragmentClientpageBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // destroy references to view
        binding = null;
    }

    @Override
    public void setPresenter(ClientPageContract.Presenter presenter) {
        // set the presenter so the view can communicate with the presenter
        clientListPresenter = presenter;
    }

    public static ClientPageFragment newInstance() {
        return new ClientPageFragment();
    }

    public static String getFragmentTag() {
        // return the name of the fragment as a tag, primarily for switching between fragments
        return ClientPageFragment.class.getSimpleName();
    }
}
