package com.example.cbr.fragments.clientlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.cbr.R;
import com.example.cbr.databinding.FragmentClientlistBinding;
import com.example.cbr.fragments.base.BaseFragment;
import com.example.cbr.models.ClientInfo;
import com.example.cbr.models.ClientSocialAspect;
import com.example.cbr.retrofit.JsonPlaceHolderApi;
import com.example.cbr.retrofit.RetrofitInit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ClientListFragment extends BaseFragment implements ClientListContract.View {

    // Init API
    Retrofit retrofit;
    JsonPlaceHolderApi jsonPlaceHolderApi;

    private ArrayList<com.example.cbr.models.ClientInfo> clientInfoArrayList;

    private ClientListAdapter adapter;

    private FragmentClientlistBinding binding;
    private ClientListContract.Presenter clientListPresenter;
    ListView listView;
    String mTitle[] = {"Facebook", "Whatsapp", "Twitter", "Instagram", "Youtube"};
    String mDescription[] = {"Facebook Description", "Whatsapp Description", "Twitter Description", "Instagram Description", "Youtube Description"};
    int images[] = {R.drawable.ic_baseline_home_24, R.drawable.ic_baseline_notifications_24, R.drawable.ic_baseline_list_24, R.drawable.ic_baseline_home_24, R.drawable.ic_baseline_home_24};

    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setPresenter(new ClientListPresenter(this));

        // Init Retrofit & NodeJs stuff
        retrofit = RetrofitInit.getInstance();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        clientInfoArrayList = new ArrayList<>();

        // View binding so that findViewById() doesn't have to be used
        binding = FragmentClientlistBinding.inflate(inflater, container, false);
        displayString("Patient List");


        getClientsInfo();


//        System.out.println(clientInfoArrayList.get(0).getFirstName());
//        System.out.println(clientInfoArrayList.get(0).getLastName());
//        System.out.println(clientInfoArrayList.get(0).getCaregiverContactNumber());
//        System.out.println(clientInfoArrayList.get(0).getGpsLocation());

        ListView listView = binding.listViewClientlist;
        ClientInfo john = new ClientInfo(true, "Sample text", "Sample text", "Sample text",
                "Sample text", "Sample text", "Sample text", 40, "Sample text",
                true, "Sample text", true, true, true, true, true, true, true, true, true, true, "Sample text", "Sample text", "Sample text", "Sample text", "Sample text", "Sample text", "Sample text", "Sample text", "Sample text");

        ClientInfo john1 = new ClientInfo(true, "Sample text", "Sample text", "Sample text",
                "Sample text", "Sample text", "Sample text", 40, "Sample text",
                true, "Sample text", true, true, true, true, true, true, true, true, true, true, "Sample text", "Sample text", "Sample text", "Sample text", "Sample text", "Sample text", "Sample text", "Sample text", "Sample text");
        ClientInfo john2 = new ClientInfo(true, "Sample text", "Sample text", "Sample text",
                "Sample text", "Sample text", "Sample text", 40, "Sample text",
                true, "Sample text", true, true, true, true, true, true, true, true, true, true, "Sample text", "Sample text", "Sample text", "Sample text", "Sample text", "Sample text", "Sample text", "Sample text", "Sample text");

        ArrayList<ClientInfo> peopleList = new ArrayList<>();
        peopleList.add(john);
        peopleList.add(john1);
        peopleList.add(john2);


        adapter = new ClientListAdapter(getActivity(), R.layout.item_clientlist, peopleList);
        listView.setAdapter(adapter);

        View view = binding.getRoot();
        return view;
    }


    private void getClientsInfo() {
        Call<List<com.example.cbr.models.ClientInfo>> call = jsonPlaceHolderApi.getClientsInfo();

        call.enqueue(new Callback<List<com.example.cbr.models.ClientInfo>>() {
            @Override
            public void onResponse(Call<List<com.example.cbr.models.ClientInfo>> call, Response<List<com.example.cbr.models.ClientInfo>> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(getActivity(), "ClientInfo Get Fail", Toast.LENGTH_SHORT).show();
                    return;
                }

                List<com.example.cbr.models.ClientInfo> clientInfoList = response.body();

                for (com.example.cbr.models.ClientInfo clientInfo: clientInfoList) {
                    clientInfoArrayList.add(clientInfo);
                }

            }

            @Override
            public void onFailure(Call<List<com.example.cbr.models.ClientInfo>> call, Throwable t) {

            }
        });
    }

//    private void getClientsInfo() throws IOException {
//        Call<List<ClientInfo>> call = jsonPlaceHolderApi.getClientsInfo();
//
//        Response<List<ClientInfo>> response = call.execute();
//
//    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // destroy references to view
        binding = null;
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
