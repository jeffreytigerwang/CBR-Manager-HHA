package com.example.cbr.fragments.newreferral;

import com.example.cbr.models.ReferralInfo;
import com.example.cbr.retrofit.JsonPlaceHolderApi;
import com.example.cbr.retrofit.RetrofitInit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NewReferralPresenter implements NewReferralContract.Presenter {

    private NewReferralContract.View newReferralView;

    // Init API for calls to the database
    private Retrofit retrofit;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    public NewReferralPresenter(NewReferralContract.View newReferralView) {
        this.newReferralView = newReferralView;

        // Init Retrofit & NodeJs stuff
        retrofit = RetrofitInit.getInstance();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
    }

    @Override
    public void createReferralInfo(ReferralInfo referralInfo) {
        Call<ReferralInfo> call = jsonPlaceHolderApi.createReferralInfo(referralInfo);

        call.enqueue(new Callback<ReferralInfo>() {
            @Override
            public void onResponse(Call<ReferralInfo> call, Response<ReferralInfo> response) {

                if (!response.isSuccessful()) {
                    return;
                }

                ReferralInfo referralResponse = response.body();

            }

            @Override
            public void onFailure(Call<ReferralInfo> call, Throwable t) {

            }
        });
    }
}
