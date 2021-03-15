package com.example.cbr.fragments.newvisit;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.cbr.models.VisitEducationQuestionSetData;
import com.example.cbr.models.VisitGeneralQuestionSetData;
import com.example.cbr.models.VisitHealthQuestionSetData;
import com.example.cbr.models.VisitSocialQuestionSetData;
import com.example.cbr.retrofit.JsonPlaceHolderApi;
import com.example.cbr.retrofit.RetrofitInit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.cbr.R.string.education_question_record_fail;
import static com.example.cbr.R.string.education_question_record_successful;
import static com.example.cbr.R.string.general_question_record_fail;
import static com.example.cbr.R.string.general_question_record_successful;
import static com.example.cbr.R.string.health_question_record_fail;
import static com.example.cbr.R.string.health_question_record_successful;
import static com.example.cbr.R.string.social_question_record_fail;
import static com.example.cbr.R.string.social_question_record_successful;

public class NewVisitPresenter implements NewVisitContract.Presenter {

    private final NewVisitContract.View view;

    private final JsonPlaceHolderApi jsonPlaceHolderApi;

    private final Context context;

    public NewVisitPresenter(NewVisitContract.View view, Context context) {
        this.view = view;
        this.context = context;
        // Init Retrofit & NodeJs stuff
        // Init API
        Retrofit retrofit = RetrofitInit.getInstance();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
    }

    public void createVisitGeneralQuestionSetData(@NonNull
            VisitGeneralQuestionSetData visitGeneralQuestionSetData) {

        Call<VisitGeneralQuestionSetData> call = jsonPlaceHolderApi
                .createVisitGeneralQuestionSetData(visitGeneralQuestionSetData);

        call.enqueue(new Callback<VisitGeneralQuestionSetData>() {
            @Override
            public void onResponse(@NonNull Call<VisitGeneralQuestionSetData> call,
                                   @NonNull Response<VisitGeneralQuestionSetData> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(context,
                            general_question_record_fail, Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(context,
                        general_question_record_successful, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NonNull Call<VisitGeneralQuestionSetData> call,
                                  @NonNull Throwable t) {

            }
        });
    }


    public void createVisitHealthQuestionSetData(@NonNull
            VisitHealthQuestionSetData visitHealthQuestionSetData) {

        Call<VisitHealthQuestionSetData> call = jsonPlaceHolderApi
                .createVisitHealthQuestionSetData(visitHealthQuestionSetData);

        call.enqueue(new Callback<VisitHealthQuestionSetData>() {
            @Override
            public void onResponse(@NonNull Call<VisitHealthQuestionSetData> call,
                                   @NonNull Response<VisitHealthQuestionSetData> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(context,
                             health_question_record_fail, Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(context,
                        health_question_record_successful, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NonNull Call<VisitHealthQuestionSetData> call,
                                  @NonNull Throwable t) {

            }
        });
    }

    public void createVisitEducationQuestionSetData(@NonNull
            VisitEducationQuestionSetData visitEducationQuestionSetData) {

        Call<VisitEducationQuestionSetData> call = jsonPlaceHolderApi
                .createVisitEducationQuestionSetData(visitEducationQuestionSetData);

        call.enqueue(new Callback<VisitEducationQuestionSetData>() {
            @Override
            public void onResponse(@NonNull Call<VisitEducationQuestionSetData> call,
                                   @NonNull Response<VisitEducationQuestionSetData> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(context,
                            education_question_record_fail, Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(context,
                        education_question_record_successful, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NonNull Call<VisitEducationQuestionSetData> call,
                                  @NonNull Throwable t) {

            }
        });
    }

    public void createVisitSocialQuestionSetData(@NonNull
            VisitSocialQuestionSetData visitSocialQuestionSetData) {
        Call<VisitSocialQuestionSetData> call = jsonPlaceHolderApi
                .createVisitSocialQuestionSetData(visitSocialQuestionSetData);

        call.enqueue(new Callback<VisitSocialQuestionSetData>() {
            @Override
            public void onResponse(@NonNull Call<VisitSocialQuestionSetData> call,
                                   @NonNull Response<VisitSocialQuestionSetData> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(context,
                            social_question_record_fail, Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(context,
                        social_question_record_successful, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NonNull Call<VisitSocialQuestionSetData> call,
                                  @NonNull Throwable t) {

            }
        });
    }
}
