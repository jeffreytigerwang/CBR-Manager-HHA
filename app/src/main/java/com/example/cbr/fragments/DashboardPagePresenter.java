package com.example.cbr.fragments;

import com.example.cbr.models.ClientDisability;
import com.example.cbr.models.ClientEducationAspect;
import com.example.cbr.models.ClientHealthAspect;
import com.example.cbr.models.ClientInfo;
import com.example.cbr.models.ClientSocialAspect;
import com.example.cbr.models.ReferralInfo;
import com.example.cbr.models.VisitGeneralQuestionSetData;
import com.example.cbr.retrofit.JsonPlaceHolderApi;
import com.example.cbr.retrofit.RetrofitInit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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

    private final String NO_RECORDED = "Not Recorded";

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
            overallRisk += overallRiskMap.getOrDefault(clientInfo.getRateEducation(), 0.0) +
                    overallRiskMap.getOrDefault(clientInfo.getRateHealth(), 0.0) +
                    overallRiskMap.getOrDefault(clientInfo.getRateSocialStatus(), 0.0);
            clientInfo.setOverallRisk(overallRisk);
        }
        Collections.sort(priorityList);
        Collections.reverse(priorityList);
        return priorityList;

    }

    @Override
    public List<ClientInfo> getOutstandingReferral() throws IOException {
        Call<List<ClientInfo>> clientsCall = jsonPlaceHolderApi.getClientsInfo();
        Response<List<ClientInfo>> clientsResponse = clientsCall.execute();

        Call<List<ReferralInfo>> referralsCall = jsonPlaceHolderApi.getReferralInfo();
        Response<List<ReferralInfo>> referralsResponse = referralsCall.execute();

        List<ClientInfo> clientList = clientsResponse.body();
        List<ClientInfo> result = new ArrayList<>();
        List<ReferralInfo> referrals = referralsResponse.body();

        for(ReferralInfo referral : referrals) {
            if(!referral.isResolved()){
                for(ClientInfo client: clientList) {

                    if(client.getClientId().equals(referral.getClientId())
                        && !result.contains(client)) {

                        result.add(client);
                        break;
                    }
                }
            }
        }
        setGeneralAspect(result);

        return result;
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

            boolean isHealthSet = false;
            boolean isEducationSet = false;
            boolean isSocialSet = false;

            // Check is there data from disability table to match with clientId
            for (int j = 0; j < clientDisabilityList.size(); j++) {
                if (clientDisabilityList.get(j).getClientId().equals(clientInfo.getClientId())) {
                    clientInfo.setAmputeeDisability(clientDisabilityList.get(j).isAmputeeDisability());
                    clientInfo.setPolioDisability(clientDisabilityList.get(j).isPolioDisability());
                    clientInfo.setSpinalCordInjuryDisability(clientDisabilityList.get(j).isSpinalCordInjuryDisability());
                    clientInfo.setCerebralPalsyDisability(clientDisabilityList.get(j).isCerebralPalsyDisability());
                    clientInfo.setSpinaBifidaDisability(clientDisabilityList.get(j).isSpinaBifidaDisability());
                    clientInfo.setHydrocephalusDisability(clientDisabilityList.get(j).isHydrocephalusDisability());
                    clientInfo.setVisualImpairmentDisability(clientDisabilityList.get(j).isVisualImpairmentDisability());
                    clientInfo.setHearingImpairmentDisability(clientDisabilityList.get(j).isHearingImpairmentDisability());
                    clientInfo.setDoNotKnowDisability(clientDisabilityList.get(j).isDoNotKnowDisability());
                    clientInfo.setOtherDisability(clientDisabilityList.get(j).isOtherDisability());
                }
            }

            // Check is there data from HealthAspect table to match with clientId
            for (int j = 0; j < clientHealthAspectList.size(); j++) {
                if (clientHealthAspectList.get(j).getClientId().equals(clientInfo.getClientId())) {
                    clientInfo.setRateHealth(clientHealthAspectList.get(j).getRateHealth());
                    clientInfo.setDescribeHealth(clientHealthAspectList.get(j).getDescribeHealth());
                    clientInfo.setSetGoalForHealth(clientHealthAspectList.get(j).getSetGoalForHealth());
                    isHealthSet = true;
                }
            }

            // Check is there data from EducationAspect table to match with clientId
            for (int j = 0; j < clientEducationAspectList.size(); j++) {
                if (clientEducationAspectList.get(j).getClientId().equals(clientInfo.getClientId())) {
                    clientInfo.setRateEducation(clientEducationAspectList.get(j).getRateEducation());
                    clientInfo.setDescribeEducation(clientEducationAspectList.get(j).getDescribeEducation());
                    clientInfo.setSetGoalForEducation(clientEducationAspectList.get(j).getSetGoalForEducation());
                    isEducationSet = true;
                }
            }

            // Check is there data from SocialAspect table to match with clientId
            for (int j = 0; j < clientSocialAspectList.size(); j++) {
                if (clientSocialAspectList.get(j).getClientId().equals(clientInfo.getClientId())) {
                    clientInfo.setRateSocialStatus(clientSocialAspectList.get(j).getRateSocialStatus());
                    clientInfo.setDescribeSocialStatus(clientSocialAspectList.get(j).getDescribeSocialStatus());
                    clientInfo.setSetGoalForSocialStatus(clientSocialAspectList.get(j).getSetGoalForSocialStatus());
                    isSocialSet = true;
                }
            }

            // If there is no information to match the HealthAspect table, set all information to "not recorded"
            if (!isHealthSet) {
                clientInfo.setRateHealth(NO_RECORDED);
                clientInfo.setDescribeHealth(NO_RECORDED);
                clientInfo.setSetGoalForHealth(NO_RECORDED);
            }

            // If there is no information to match the EducationAspect table, set all information to "not recorded"
            if (!isEducationSet) {
                clientInfo.setRateEducation(NO_RECORDED);
                clientInfo.setDescribeEducation(NO_RECORDED);
                clientInfo.setSetGoalForEducation(NO_RECORDED);
            }

            // If there is no information to match the SocialAspect table, set all information to "not recorded"
            if (!isSocialSet) {
                clientInfo.setRateSocialStatus(NO_RECORDED);
                clientInfo.setDescribeSocialStatus(NO_RECORDED);
                clientInfo.setSetGoalForSocialStatus(NO_RECORDED);
            }

        }

    }


    @Override
    public List<String> getDatesOfLastVisits(List<ClientInfo> clientInfoList) throws IOException {

        List<String> dates = new ArrayList<>();
        Call<List<VisitGeneralQuestionSetData>> call = jsonPlaceHolderApi.getVisitGeneralQuestionSetData();
        Response<List<VisitGeneralQuestionSetData>> response = call.execute();
        List<VisitGeneralQuestionSetData> visits = response.body();

        for(ClientInfo client : clientInfoList) {
            Date dateOfLastVisit = new Date(Long.MIN_VALUE);
            for(VisitGeneralQuestionSetData visit : visits) {
                if(client.getClientId().equals(visit.getClientId())) {
                    if(dateOfLastVisit.before(visit.getDateOfVisit())) {
                        dateOfLastVisit = visit.getDateOfVisit();
                    }
                }
            }
            if(dateOfLastVisit.equals(new Date(Long.MIN_VALUE))) {
                dates.add(client.getDateJoined());
            } else {
                dates.add(String.valueOf(dateOfLastVisit));
            }
        }

        return dates;

    }
}
