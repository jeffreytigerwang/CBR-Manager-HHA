package com.example.cbr.fragments.home;


import com.example.cbr.models.ClientDisability;
import com.example.cbr.models.ClientEducationAspect;
import com.example.cbr.models.ClientHealthAspect;
import com.example.cbr.models.ClientInfo;
import com.example.cbr.models.ClientSocialAspect;
import com.example.cbr.models.ReferralInfo;
import com.example.cbr.models.VisitEducationQuestionSetData;
import com.example.cbr.models.VisitGeneralQuestionSetData;
import com.example.cbr.models.VisitHealthQuestionSetData;
import com.example.cbr.models.VisitSocialQuestionSetData;
import com.example.cbr.retrofit.JsonPlaceHolderApi;
import com.example.cbr.retrofit.RetrofitInit;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomePagePresenter implements HomePageContract.Presenter {
    private HomePageContract.View homePageView;
    private Retrofit retrofit;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    public HomePagePresenter(HomePageContract.View homePageView) {
        retrofit = RetrofitInit.getInstance();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        this.homePageView = homePageView;
    }

    @Override
    public List<ClientInfo> getAllClients() throws IOException {
        Call<List<ClientInfo>> call = jsonPlaceHolderApi.getClientsInfo();
        Response<List<ClientInfo>> response = call.execute();
        return response.body();
    }

    @Override
    public List<ClientDisability> getAllClientDisabilities() throws IOException {
        Call<List<ClientDisability>> call = jsonPlaceHolderApi.getClientDisability();
        Response<List<ClientDisability>> response = call.execute();
        return response.body();
    }

    @Override
    public List<ClientEducationAspect> getAllClientEducationAspect() throws IOException {
        Call<List<ClientEducationAspect>> call = jsonPlaceHolderApi.getClientEducationAspect();
        Response<List<ClientEducationAspect>> response = call.execute();
        return response.body();
    }

    @Override
    public List<ClientHealthAspect> getAllClientHealthAspect() throws IOException {
        Call<List<ClientHealthAspect>> call = jsonPlaceHolderApi.getClientHealthAspect();
        Response<List<ClientHealthAspect>> response = call.execute();
        return response.body();
    }

    @Override
    public List<ClientSocialAspect> getAllClientSocialAspect() throws IOException {
        Call<List<ClientSocialAspect>> call = jsonPlaceHolderApi.getClientSocialAspect();
        Response<List<ClientSocialAspect>> response = call.execute();
        return response.body();
    }

    @Override
    public List<ReferralInfo> getAllReferrals() throws IOException {
        Call<List<ReferralInfo>> call = jsonPlaceHolderApi.getReferralInfo();
        Response<List<ReferralInfo>> response = call.execute();
        return response.body();
    }

    @Override
    public List<VisitEducationQuestionSetData> getAllVisitEducationQuestions() throws IOException {
        Call<List<VisitEducationQuestionSetData>> call = jsonPlaceHolderApi.getVisitEducationQuestionSetData();
        Response<List<VisitEducationQuestionSetData>> response = call.execute();
        return response.body();
    }

    @Override
    public List<VisitGeneralQuestionSetData> getAllVisitGeneralQuestions() throws IOException {
        Call<List<VisitGeneralQuestionSetData>> call = jsonPlaceHolderApi.getVisitGeneralQuestionSetData();
        Response<List<VisitGeneralQuestionSetData>> response = call.execute();
        return response.body();
    }

    @Override
    public List<VisitHealthQuestionSetData> getAllVisitHealthQuestions() throws IOException {
        Call<List<VisitHealthQuestionSetData>> call = jsonPlaceHolderApi.getVisitHealthQuestionSetData();
        Response<List<VisitHealthQuestionSetData>> response = call.execute();
        return response.body();
    }

    @Override
    public List<VisitSocialQuestionSetData> getAllVisitSocialQuestions() throws IOException {
        Call<List<VisitSocialQuestionSetData>> call = jsonPlaceHolderApi.getVisitSocialQuestionSetData();
        Response<List<VisitSocialQuestionSetData>> response = call.execute();

        return response.body();
    }
}
