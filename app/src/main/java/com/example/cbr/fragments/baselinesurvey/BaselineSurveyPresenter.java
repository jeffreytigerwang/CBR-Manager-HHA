package com.example.cbr.fragments.baselinesurvey;

import android.content.Context;

import com.example.cbr.retrofit.JsonPlaceHolderApi;
import com.example.cbr.retrofit.RetrofitInit;

import retrofit2.Retrofit;

public class BaselineSurveyPresenter implements BaselineSurveyContract.Presenter {
    private BaselineSurveyContract.View baselineSurveyView;
    private final JsonPlaceHolderApi jsonPlaceHolderApi;
    private final Context context;

    public BaselineSurveyPresenter(BaselineSurveyContract.View baselineSurveyView, Context context) {
        this.baselineSurveyView = baselineSurveyView;
        this.context = context;
        Retrofit retrofit = RetrofitInit.getInstance();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
    }
}
