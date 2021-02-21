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

public class ClientListFragment extends BaseFragment implements ClientListContract.View {

    // Init API
    private Retrofit retrofit;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

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

        try {
            getClientsInfo();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RecyclerView recyclerView = binding.recyclerViewClientlist;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        ClientListAdapter adapter = new ClientListAdapter(getActivity(), clientInfoArrayList, clientListFragmentInterface);
        recyclerView.setAdapter(adapter);

        binding.sampleText.setText(R.string.patient_list);

        View view = binding.getRoot();
        return view;
    }


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
