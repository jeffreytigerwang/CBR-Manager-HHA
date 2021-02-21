package com.example.cbr.fragments.clientpage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cbr.R;
import com.example.cbr.activities.NewVisitActivity;
import com.example.cbr.adapters.ClientInfoAdapter;
import com.example.cbr.databinding.FragmentClientpageBinding;
import com.example.cbr.fragments.base.BaseFragment;
import com.example.cbr.fragments.clientlist.ClientListFragment;
import com.example.cbr.models.ClientInfo;
import com.example.cbr.models.VisitGeneralQuestionSetData;
import com.example.cbr.retrofit.JsonPlaceHolderApi;
import com.example.cbr.retrofit.RetrofitInit;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ClientPageFragment extends BaseFragment implements ClientPageContract.View {

    private FragmentClientpageBinding binding;
    private ClientPageContract.Presenter clientListPresenter;
    private ClientPageFragmentInterface clientPageFragmentInterface;

    private ClientInfo clientInfo;
    private List<VisitGeneralQuestionSetData> visitsList;
    private ClientInfoAdapter clientInfoAdapter;

    private static final String CLIENT_PAGE_BUNDLE = "clientPageBundle";

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            clientPageFragmentInterface = (ClientPageFragmentInterface) context;
        } catch (ClassCastException e) {
            Log.e(getFragmentTag(), "Activity should implement ClientPageFragmentInterface");
        }
    }

    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setPresenter(new ClientPagePresenter(this));
        binding = FragmentClientpageBinding.inflate(inflater, container, false);

        clientInfo = (ClientInfo) getArguments().getSerializable(CLIENT_PAGE_BUNDLE);

        try {
            visitsList = clientListPresenter.getVisits();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setupClientInfoCard();
        setupRecyclerView();

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setupClientInfoCard() {
        binding.clientPageNameText.setText(clientInfo.getFullName());
        binding.clientPageLocationText.setText(clientInfo.getZoneLocation());
        binding.clientPageAgeText.setText(getString(R.string.age_clientpage, clientInfo.getAge().toString()));
        binding.clientPageDisabilityText.setText(getString(R.string.disability_clientpage, clientInfo.getDisabilityListFormatted()));

        binding.clientPageNewVisitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = NewVisitActivity.makeLaunchIntent(getActivity(), -1);
                startActivity(intent);
            }
        });
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = binding.clientPageInfoList;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);
        clientInfoAdapter = new ClientInfoAdapter(getActivity(), clientInfo, visitsList, clientPageFragmentInterface);
        recyclerView.setAdapter(clientInfoAdapter);
    }

    @Override
    public void setPresenter(ClientPageContract.Presenter presenter) {
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
        return ClientPageFragment.class.getSimpleName();
    }

    public interface ClientPageFragmentInterface {
        void swapToVisitPage(VisitGeneralQuestionSetData visitGeneralQuestionSetData);
    }
}
