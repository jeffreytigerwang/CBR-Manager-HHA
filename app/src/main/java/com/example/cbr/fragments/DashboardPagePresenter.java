package com.example.cbr.fragments;

import com.example.cbr.models.ClientDisability;
import com.example.cbr.models.ClientEducationAspect;
import com.example.cbr.models.ClientHealthAspect;
import com.example.cbr.models.ClientInfo;
import com.example.cbr.models.ClientSocialAspect;
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
        overallRiskMap.put("low risk", Double.valueOf(0));
        overallRiskMap.put("medium risk",Double.valueOf(0.5));
        overallRiskMap.put("high risk", Double.valueOf(3));
        overallRiskMap.put("critical risk", Double.valueOf(11));

    }



    @Override
    public List<ClientInfo> getTopPriority() throws IOException {
        Call<List<ClientInfo>> call = jsonPlaceHolderApi.getClientsInfo();
        Response<List<ClientInfo>> response = call.execute();

        List<ClientInfo> priorityList = response.body();
        setGeneralAspect(priorityList);

        for(ClientInfo clientInfo : priorityList) {
            double overallRisk = 0;
            overallRisk += overallRiskMap.get(clientInfo.getRateEducation()) +
                    overallRiskMap.get(clientInfo.getRateHealth()) +
                    overallRiskMap.get(clientInfo.getRateSocialStatus());
            clientInfo.setOverallRisk(overallRisk);
        }
        Collections.sort(priorityList);
        Collections.reverse(priorityList);
        return priorityList;

    }

    @Override
    public List<ClientInfo> getOutstandingReferral() throws IOException {
        Call<List<ClientInfo>> call = jsonPlaceHolderApi.getClientsInfo();
        Response<List<ClientInfo>> response = call.execute();

        return response.body();
    }

    @Override
    public void setGeneralAspect(List<ClientInfo> clientInfoList) throws IOException {

        Call<List<ClientDisability>> callDisable = jsonPlaceHolderApi.getClientDisability();
        Call<List<ClientHealthAspect>> callHealth = jsonPlaceHolderApi.getClientHealthAspect();
        Call<List<ClientEducationAspect>> callEducation = jsonPlaceHolderApi.getClientEducationAspect();
        Call<List<ClientSocialAspect>> callSocial = jsonPlaceHolderApi.getClientSocialAspect();

        Response<List<ClientDisability>> responseDisable = callDisable.execute();
        List<ClientDisability> clientDisabilityList = responseDisable.body();

        Response<List<ClientHealthAspect>> responseHealth = callHealth.execute();
        List<ClientHealthAspect> clientHealthAspectList = responseHealth.body();

        Response<List<ClientEducationAspect>> responseEducation = callEducation.execute();
        List<ClientEducationAspect> clientEducationAspectList = responseEducation.body();

        Response<List<ClientSocialAspect>> responseSocial = callSocial.execute();
        List<ClientSocialAspect> clientSocialAspectList = responseSocial.body();


        for (int i = 0; i < clientInfoList.size(); i++) {
            ClientInfo clientInfo = clientInfoList.get(i);

            clientInfo.setAmputeeDisability(clientDisabilityList.get(i).isAmputeeDisability());
            clientInfo.setPolioDisability(clientDisabilityList.get(i).isPolioDisability());
            clientInfo.setSpinalCordInjuryDisability(clientDisabilityList.get(i).isSpinalCordInjuryDisability());
            clientInfo.setCerebralPalsyDisability(clientDisabilityList.get(i).isCerebralPalsyDisability());
            clientInfo.setSpinaBifidaDisability(clientDisabilityList.get(i).isSpinaBifidaDisability());
            clientInfo.setHydrocephalusDisability(clientDisabilityList.get(i).isHydrocephalusDisability());
            clientInfo.setVisualImpairmentDisability(clientDisabilityList.get(i).isVisualImpairmentDisability());
            clientInfo.setHearingImpairmentDisability(clientDisabilityList.get(i).isHearingImpairmentDisability());
            clientInfo.setDoNotKnowDisability(clientDisabilityList.get(i).isDoNotKnowDisability());
            clientInfo.setOtherDisability(clientDisabilityList.get(i).isOtherDisability());

            clientInfo.setRateHealth(clientHealthAspectList.get(i).getRateHealth());
            clientInfo.setDescribeHealth(clientHealthAspectList.get(i).getDescribeHealth());
            clientInfo.setSetGoalForHealth(clientHealthAspectList.get(i).getSetGoalForHealth());

            clientInfo.setRateEducation(clientEducationAspectList.get(i).getRateEducation());
            clientInfo.setDescribeEducation(clientEducationAspectList.get(i).getDescribeEducation());
            clientInfo.setSetGoalForEducation(clientEducationAspectList.get(i).getSetGoalForEducation());

            clientInfo.setRateSocialStatus(clientSocialAspectList.get(i).getRateSocialStatus());
            clientInfo.setDescribeSocialStatus(clientSocialAspectList.get(i).getDescribeSocialStatus());
            clientInfo.setSetGoalForSocialStatus(clientSocialAspectList.get(i).getSetGoalForSocialStatus());

        }

    }
}
