package com.example.cbr.fragments;

import com.example.cbr.models.ClientInfo;
import com.example.cbr.retrofit.JsonPlaceHolderApi;
import com.example.cbr.retrofit.RetrofitInit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DashboardPagePresenter implements DashboardPageContract.Presenter {

    private Retrofit retrofit;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    private DashboardPageContract.View dashboardPageView;

    private HashMap<String, Double> overallRiskMap;

    public DashboardPagePresenter(DashboardPageContract.View dashboardPageView) {

        retrofit = RetrofitInit.getInstance();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        this.dashboardPageView = dashboardPageView;

        overallRiskMap = new HashMap<String, Double>();
        overallRiskMap.put("Low risk", Double.valueOf(0));
        overallRiskMap.put("Medium risk",Double.valueOf(0.5));
        overallRiskMap.put("High risk", Double.valueOf(3));
        overallRiskMap.put("Critical risk", Double.valueOf(11));

    }


    @Override
    public List<ClientInfo> getTopPriority() throws IOException {
        Call<List<ClientInfo>> call = jsonPlaceHolderApi.getClientsInfo();
        Response<List<ClientInfo>> response = call.execute();

        List<ClientInfo> priorityList = response.body();

        for(ClientInfo clientInfo : priorityList) {
            double overallRisk = 0;
            overallRisk += overallRiskMap.get(clientInfo.getRateEducation()) +
                    overallRiskMap.get(clientInfo.getRateHealth()) +
                    overallRiskMap.get(clientInfo.getRateSocialStatus());
            clientInfo.setOverallRisk(overallRisk);
        }
        Collections.sort(priorityList);
        return priorityList;

    }

    @Override
    public List<ClientInfo> getOutstandingReferral() throws IOException {
        Call<List<ClientInfo>> call = jsonPlaceHolderApi.getClientsInfo();
        Response<List<ClientInfo>> response = call.execute();

        return response.body();
    }
}
