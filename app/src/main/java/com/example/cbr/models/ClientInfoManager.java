package com.example.cbr.models;

import android.os.StrictMode;

import androidx.annotation.NonNull;

import com.example.cbr.retrofit.JsonPlaceHolderApi;
import com.example.cbr.retrofit.RetrofitInit;
import com.example.cbr.util.StringsUtil;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ClientInfoManager implements Iterable<ClientInfo>{

    // Init API for calls to the database
    private Retrofit retrofit;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    private ArrayList<ClientInfo> clientInfoArrayList;


    private void getClientsInfo() throws IOException {
        Call<List<ClientInfo>> call = jsonPlaceHolderApi.getClientsInfo();

        Response<List<ClientInfo>> response = call.execute();
        List<ClientInfo> clientInfoList = response.body();

        clientInfoArrayList.addAll(clientInfoList);
    }

    private void getClientGeneralAspect() throws IOException {
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


        for (int i = 0; i < clientInfoArrayList.size(); i++) {
            ClientInfo clientInfo = clientInfoArrayList.get(i);

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

            clientInfoArrayList.set(i, clientInfo);
        }

    }

    public String getDateOfLastVisit(ClientInfo clientInfo) throws IOException {

        Call<List<VisitGeneralQuestionSetData>> call = jsonPlaceHolderApi.getVisitGeneralQuestionSetData();
        Response<List<VisitGeneralQuestionSetData>> response = call.execute();
        List<VisitGeneralQuestionSetData> visits = response.body();


        Date dateOfLastVisit = new Date(Long.MIN_VALUE);
        for(VisitGeneralQuestionSetData visit : visits) {
            if(clientInfo.getClientId().equals(visit.getClientId())) {
                if(dateOfLastVisit.before(visit.getDateOfVisit())) {
                    dateOfLastVisit = visit.getDateOfVisit();
                }
            }
        }
        if(dateOfLastVisit.equals(new Date(Long.MIN_VALUE))) {
            Date date = null;
            try {
                date = new SimpleDateFormat("YYYY-MM-DD").parse(clientInfo.getDateJoined());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(date == null) {
                return clientInfo.getDateJoined();
            }
            return StringsUtil.dateToUKFormat(date);

        } else {
            return StringsUtil.dateToUKFormat(dateOfLastVisit);
        }

    }



    public ClientInfo findClientByName(String name){
        for (ClientInfo clientInfo : clientInfoArrayList){
            if (clientInfo.getFullName().equals(name)){
                return clientInfo;
            }
        }
        return null;
    }

    /*
        Singleton Support
    */
    private static ClientInfoManager instance;

    private ClientInfoManager(){

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // prevent anyone else from instantiating object
        retrofit = RetrofitInit.getInstance();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        clientInfoArrayList = new ArrayList<>();

        try {
            getClientsInfo();
            getClientGeneralAspect();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static ClientInfoManager getInstance(){
        if(instance == null){
            instance = new ClientInfoManager();
        }
        return instance;
    }

    public ArrayList<ClientInfo> getClientInfoArrayList(){
        return clientInfoArrayList;
    }

    @NonNull
    @Override
    public Iterator<ClientInfo> iterator() {
        return getClientInfoArrayList().iterator();
    }
}
