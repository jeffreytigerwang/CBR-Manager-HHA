package com.example.cbr.fragments.clientlist;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cbr.adapters.ClientListAdapter;
import com.example.cbr.databinding.FragmentClientlistBinding;
import com.example.cbr.fragments.base.BaseFragment;
import com.example.cbr.models.ClientInfo;
import com.example.cbr.retrofit.JsonPlaceHolderApi;
import com.example.cbr.retrofit.RetrofitInit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import com.example.cbr.R;

import retrofit2.Retrofit;

public class ClientListFragment extends BaseFragment implements ClientListContract.View {

    Retrofit retrofit;
    JsonPlaceHolderApi jsonPlaceHolderApi;

    private ArrayList<ClientInfo> clientInfoArrayList;

    private FragmentClientlistBinding binding;
    private ClientListFragmentInterface clientListFragmentInterface;
    private ClientListContract.Presenter clientListPresenter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            clientListFragmentInterface = (ClientListFragmentInterface) context;
        } catch (ClassCastException e) {
            Log.e(getFragmentTag(), "Activity should implement ClientListFragmentInterface");
        }
    }

    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setPresenter(new ClientListPresenter(this));


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // Init Retrofit & NodeJs stuff
        retrofit = RetrofitInit.getInstance();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        clientInfoArrayList = new ArrayList<>();

        // View binding so that findViewById() doesn't have to be used
        binding = FragmentClientlistBinding.inflate(inflater, container, false);

        ClientInfo john = new ClientInfo(true, "Sample text0", "sample text0", "sample text0",
                "sample text0", "sample text0", "sample text0", 40, "sample text0",
                true, "sample text0", true, true, true, true, true, true, true, true, true, true, "sample text0", "sample text0", "sample text0", "sample text0", "sample text0", "sample text0", "sample text0", "sample text0", "sample text0");
        try {
            getClientsInfo();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ClientInfo john1 = new ClientInfo(true, "sample text1", "sample text1", "sample text1",
                "sample text1", "sample text1", "sample text1", 40, "sample text1",
                true, "sample text1", true, true, true, true, true, true, true, true, true, true, "sample text1", "sample text1", "sample text1", "sample text1", "sample text1", "sample text1", "sample text1", "sample text1", "sample text1");

        ClientInfo john2 = new ClientInfo(true, "sample text2", "sample text2", "sample text2",
                "sample text2", "sample text2", "sample text2", 40, "sample text2",
                true, "sample text2", true, true, true, true, true, true, true, true, false, true, "sample text2", "sample text2", "sample text2", "sample text2", "sample text2", "sample text2", "sample text2", "sample text2", "sample text2");

        System.out.println(clientInfoArrayList.get(0).getFirstName());
        System.out.println(clientInfoArrayList.get(0).getLastName());
        System.out.println(clientInfoArrayList.get(0).getCaregiverContactNumber());
        System.out.println(clientInfoArrayList.get(0).getGpsLocation());

        ClientInfo empty = new ClientInfo();
        ClientInfo empty1 = new ClientInfo();
        ClientInfo empty2 = new ClientInfo();
        ClientInfo empty3 = new ClientInfo();

        ArrayList<ClientInfo> peopleList = new ArrayList<>();
        peopleList.add(john);
        peopleList.add(john1);
        peopleList.add(john2);
        peopleList.add(empty);
        peopleList.add(empty1);
        peopleList.add(empty2);
        peopleList.add(empty3);

        RecyclerView recyclerView = binding.recyclerViewClientlist;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        ClientListAdapter adapter = new ClientListAdapter(getActivity(), clientInfoArrayList, clientListFragmentInterface);
        recyclerView.setAdapter(adapter);

//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
//                linearLayoutManager.getOrientation());
//        recyclerView.addItemDecoration(dividerItemDecoration);
//        adapter = new ClientListAdapter(getActivity(), R.layout.item_clientlist, clientInfoArrayList);
//        listView.setAdapter(adapter);

        View view = binding.getRoot();
        return view;
    }


//    private void getClientsInfo() {
//        Call<List<com.example.cbr.models.ClientInfo>> call = jsonPlaceHolderApi.getClientsInfo();
//
//        call.enqueue(new Callback<List<com.example.cbr.models.ClientInfo>>() {
//            @Override
//            public void onResponse(Call<List<com.example.cbr.models.ClientInfo>> call, Response<List<com.example.cbr.models.ClientInfo>> response) {
//
//                if (!response.isSuccessful()) {
//                    Toast.makeText(getActivity(), "ClientInfo Get Fail", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                List<com.example.cbr.models.ClientInfo> clientInfoList = response.body();
//
//                for (com.example.cbr.models.ClientInfo clientInfo: clientInfoList) {
//                    clientInfoArrayList.add(clientInfo);
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<List<com.example.cbr.models.ClientInfo>> call, Throwable t) {
//
//            }
//        });
//    }

    private void getClientsInfo() throws IOException {
        Call<List<ClientInfo>> call = jsonPlaceHolderApi.getClientsInfo();

        Response<List<ClientInfo>> response = call.execute();
        List<ClientInfo> clientInfoList = response.body();

        clientInfoArrayList.addAll(clientInfoList);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void setPresenter(ClientListContract.Presenter presenter) {
        clientListPresenter = presenter;
    }

    public static ClientListFragment newInstance() {
        return new ClientListFragment();
    }

    public static String getFragmentTag() {
        return ClientListFragment.class.getSimpleName();
    }

    public interface ClientListFragmentInterface {
        void swapToClientPage(ClientInfo clientInfo);
    }
}
