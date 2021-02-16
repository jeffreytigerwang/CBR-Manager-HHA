package com.example.cbr.fragments.clientpage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.cbr.R;
import com.example.cbr.databinding.FragmentClientpageBinding;
import com.example.cbr.fragments.base.BaseFragment;
import com.example.cbr.model.ClientInfo;

public class ClientPageFragment extends BaseFragment implements ClientPageContract.View {

    private FragmentClientpageBinding binding;
    private ClientPageContract.Presenter clientListPresenter;

    private ClientInfo clientInfo;

    private static final String CLIENT_PAGE_BUNDLE = "clientPageBundle";

    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setPresenter(new ClientPagePresenter(this));
        // View binding so that findViewById() doesn't have to be used
        binding = FragmentClientpageBinding.inflate(inflater, container, false);

        setClientInfo();

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // destroy references to view
        binding = null;
    }

    private void setClientInfo() {
        clientInfo = (ClientInfo) getArguments().getSerializable(CLIENT_PAGE_BUNDLE);

        binding.clientPageNameText.setText(clientInfo.getFullName());
        binding.clientPageLocationText.setText(clientInfo.getLocation());
        binding.clientPageAgeText.setText(getString(R.string.age_clientpage, clientInfo.getAge().toString()));
        binding.clientPageDisabilityText.setText(getString(R.string.disability_clientpage, clientInfo.getDisabilityList()));
    }

    @Override
    public void setPresenter(ClientPageContract.Presenter presenter) {
        // set the presenter so the view can communicate with the presenter
        clientListPresenter = presenter;
    }

    public static ClientPageFragment newInstance(ClientInfo clientInfo) {
        ClientPageFragment clientPageFragment = new ClientPageFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(CLIENT_PAGE_BUNDLE, clientInfo);
        clientPageFragment.setArguments(bundle);

        return clientPageFragment;
    }

    public static String getFragmentTag() {
        // return the name of the fragment as a tag, primarily for switching between fragments
        return ClientPageFragment.class.getSimpleName();
    }
}
