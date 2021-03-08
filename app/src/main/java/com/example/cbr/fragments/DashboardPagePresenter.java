package com.example.cbr.fragments;

import com.example.cbr.models.ClientInfo;
import com.example.cbr.retrofit.JsonPlaceHolderApi;
import com.example.cbr.retrofit.RetrofitInit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DashboardPagePresenter implements DashboardPageContract.Presenter {

    private Retrofit retrofit;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    private DashboardPageContract.View dashboardPageView;

    public DashboardPagePresenter(DashboardPageContract.View dashboardPageView) {

        retrofit = RetrofitInit.getInstance();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        this.dashboardPageView = dashboardPageView;
    }


    @Override
    public List<ClientInfo> getTopPriority() throws IOException {
        Call<List<ClientInfo>> call = jsonPlaceHolderApi.getClientsInfo();
        Response<List<ClientInfo>> response = call.execute();

        List<ClientInfo> priorityList = new ArrayList<>();

        for(ClientInfo clientInfo : response.body()) {
            if(!clientInfo.getRateHealth().equals("Low") || !clientInfo.getRateEducation().equals("Low")
                    || !clientInfo.getRateSocialStatus().equals("Low")) {
                priorityList.add(clientInfo);
            }
        }

        return priorityList;

    }

    @Override
    public List<ClientInfo> getOutstandingReferral() throws IOException {
        Call<List<ClientInfo>> call = jsonPlaceHolderApi.getClientsInfo();
        Response<List<ClientInfo>> response = call.execute();

        List<ClientInfo> outstandingList = response.body();



        return outstandingList;
    }
}
