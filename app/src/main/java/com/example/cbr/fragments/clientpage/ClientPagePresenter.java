package com.example.cbr.fragments.clientpage;

import com.example.cbr.models.ReferralInfo;
import com.example.cbr.models.VisitGeneralQuestionSetData;
import com.example.cbr.retrofit.JsonPlaceHolderApi;
import com.example.cbr.retrofit.RetrofitInit;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ClientPagePresenter implements ClientPageContract.Presenter {

    // Init API
    private Retrofit retrofit;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    private ClientPageContract.View clientPageView;

    public ClientPagePresenter(ClientPageContract.View clientPageView) {
        this.clientPageView = clientPageView;

        // Init Retrofit & NodeJs stuff
        retrofit = RetrofitInit.getInstance();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
    }

    @Override
    public List<VisitGeneralQuestionSetData> getVisits() throws IOException {
        Call<List<VisitGeneralQuestionSetData>> call = jsonPlaceHolderApi.getVisitGeneralQuestionSetData();

        Response<List<VisitGeneralQuestionSetData>> response = call.execute();
        return response.body();
    }

    @Override
    public List<ReferralInfo> getReferrals() throws IOException {
        Call<List<ReferralInfo>> call = jsonPlaceHolderApi.getReferralInfo();

        Response<List<ReferralInfo>> response = call.execute();
        return response.body();
    }
}
