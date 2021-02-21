package com.example.cbr.fragments.visitpage;

import com.example.cbr.models.VisitGeneralQuestionSetData;
import com.example.cbr.retrofit.JsonPlaceHolderApi;
import com.example.cbr.retrofit.RetrofitInit;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class VisitPagePresenter implements VisitPageContract.Presenter {
    private VisitPageContract.View visitPageView;

    public VisitPagePresenter(VisitPageContract.View visitPageView) {
        this.visitPageView = visitPageView;

    }
}
