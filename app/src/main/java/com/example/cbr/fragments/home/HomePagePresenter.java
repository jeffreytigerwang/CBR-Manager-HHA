package com.example.cbr.fragments.home;


import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.cbr.R;
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
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.example.cbr.R.string.education_question_record_cancelled;
import static com.example.cbr.R.string.education_question_record_fail;
import static com.example.cbr.R.string.education_question_record_successful;
import static com.example.cbr.R.string.general_question_record_cancelled;
import static com.example.cbr.R.string.general_question_record_fail;
import static com.example.cbr.R.string.general_question_record_successful;
import static com.example.cbr.R.string.health_question_record_cancelled;
import static com.example.cbr.R.string.health_question_record_fail;
import static com.example.cbr.R.string.health_question_record_successful;
import static com.example.cbr.R.string.retrofit_call_fail;
import static com.example.cbr.R.string.social_question_record_cancelled;
import static com.example.cbr.R.string.social_question_record_fail;
import static com.example.cbr.R.string.social_question_record_successful;

public class HomePagePresenter implements HomePageContract.Presenter {
    private HomePageContract.View homePageView;
    private Retrofit retrofit;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private Context context;

    public HomePagePresenter(HomePageContract.View homePageView, Context context) {
        retrofit = RetrofitInit.getInstance();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        this.homePageView = homePageView;
        this.context = context;
    }

    @Override
    public List<ClientInfo> getAllClients() throws IOException {
        Call<List<ClientInfo>> call = jsonPlaceHolderApi.getClientsInfo();
        Response<List<ClientInfo>> response = call.execute();

        if(!response.isSuccessful()) {
            Toast.makeText(context, R.string.unable_to_retrieve_client_info, Toast.LENGTH_SHORT).show();
        }

        return response.body();
    }

    @Override
    public List<ClientDisability> getAllClientDisabilities() throws IOException {
        Call<List<ClientDisability>> call = jsonPlaceHolderApi.getClientDisability();
        Response<List<ClientDisability>> response = call.execute();
        if(!response.isSuccessful()) {
            Toast.makeText(context, R.string.unable_to_retrieve_disabilities, Toast.LENGTH_SHORT).show();
        }
        return response.body();
    }

    @Override
    public List<ClientEducationAspect> getAllClientEducationAspect() throws IOException {
        Call<List<ClientEducationAspect>> call = jsonPlaceHolderApi.getClientEducationAspect();
        Response<List<ClientEducationAspect>> response = call.execute();
        if(!response.isSuccessful()) {
            Toast.makeText(context, R.string.unable_to_retrieve_education_aspect, Toast.LENGTH_SHORT).show();
        }
        return response.body();
    }

    @Override
    public List<ClientHealthAspect> getAllClientHealthAspect() throws IOException {
        Call<List<ClientHealthAspect>> call = jsonPlaceHolderApi.getClientHealthAspect();
        Response<List<ClientHealthAspect>> response = call.execute();
        if(!response.isSuccessful()) {
            Toast.makeText(context, R.string.unable_to_retrieve_health_aspect, Toast.LENGTH_SHORT).show();
        }

        return response.body();
    }

    @Override
    public List<ClientSocialAspect> getAllClientSocialAspect() throws IOException {
        Call<List<ClientSocialAspect>> call = jsonPlaceHolderApi.getClientSocialAspect();
        Response<List<ClientSocialAspect>> response = call.execute();
        if(!response.isSuccessful()) {
            Toast.makeText(context, R.string.unable_to_retrieve_social_aspect, Toast.LENGTH_SHORT).show();
        }
        return response.body();
    }

    @Override
    public List<ReferralInfo> getAllReferrals() throws IOException {
        Call<List<ReferralInfo>> call = jsonPlaceHolderApi.getReferralInfo();
        Response<List<ReferralInfo>> response = call.execute();
        if(!response.isSuccessful()) {
            Toast.makeText(context, R.string.unable_to_retrieve_referrals, Toast.LENGTH_SHORT).show();
        }
        return response.body();
    }

    @Override
    public List<VisitEducationQuestionSetData> getAllVisitEducationQuestions() throws IOException {
        Call<List<VisitEducationQuestionSetData>> call = jsonPlaceHolderApi.getVisitEducationQuestionSetData();
        Response<List<VisitEducationQuestionSetData>> response = call.execute();
        if(!response.isSuccessful()) {
            Toast.makeText(context, R.string.unable_to_retrieve_education_rogress, Toast.LENGTH_SHORT).show();
        }
        return response.body();
    }

    @Override
    public List<VisitGeneralQuestionSetData> getAllVisitGeneralQuestions() throws IOException {
        Call<List<VisitGeneralQuestionSetData>> call = jsonPlaceHolderApi.getVisitGeneralQuestionSetData();
        Response<List<VisitGeneralQuestionSetData>> response = call.execute();
        if(!response.isSuccessful()) {
            Toast.makeText(context, R.string.unable_to_retrieve_visits, Toast.LENGTH_SHORT).show();
        }
        return response.body();
    }

    @Override
    public List<VisitHealthQuestionSetData> getAllVisitHealthQuestions() throws IOException {
        Call<List<VisitHealthQuestionSetData>> call = jsonPlaceHolderApi.getVisitHealthQuestionSetData();
        Response<List<VisitHealthQuestionSetData>> response = call.execute();
        if(!response.isSuccessful()) {
            Toast.makeText(context, R.string.unable_to_retrieve_health_progress, Toast.LENGTH_SHORT).show();
        }
        return response.body();
    }

    @Override
    public List<VisitSocialQuestionSetData> getAllVisitSocialQuestions() throws IOException {
        Call<List<VisitSocialQuestionSetData>> call = jsonPlaceHolderApi.getVisitSocialQuestionSetData();
        Response<List<VisitSocialQuestionSetData>> response = call.execute();
        if(!response.isSuccessful()) {
            Toast.makeText(context, R.string.unable_to_retrieve_social_progress, Toast.LENGTH_SHORT).show();
        }

        return response.body();
    }

    public void createClientInfo(@NonNull ClientInfo clientInfo) {
        Call<ClientInfo> call = jsonPlaceHolderApi.createClient(clientInfo);

        call.enqueue(new Callback<ClientInfo>() {
            @Override
            public void onResponse(Call<ClientInfo> call, Response<ClientInfo> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, R.string.client_record_fail, Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call<ClientInfo> call,
                                  @NonNull Throwable t) {
            }
        });

    }

    public void createVisitGeneralQuestionSetData(@NonNull VisitGeneralQuestionSetData visitGeneralQuestionSetData) {

        Call<VisitGeneralQuestionSetData> call = jsonPlaceHolderApi
                .createVisitGeneralQuestionSetData(visitGeneralQuestionSetData);

        call.enqueue(new Callback<VisitGeneralQuestionSetData>() {
            @Override
            public void onResponse(@NonNull Call<VisitGeneralQuestionSetData> call,
                                   @NonNull Response<VisitGeneralQuestionSetData> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(context, general_question_record_fail, Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(@NonNull Call<VisitGeneralQuestionSetData> call,
                                  @NonNull Throwable t) {

            }
        });
    }

    public void createVisitHealthQuestionSetData(@NonNull VisitHealthQuestionSetData visitHealthQuestionSetData) {

        Call<VisitHealthQuestionSetData> call = jsonPlaceHolderApi
                .createVisitHealthQuestionSetData(visitHealthQuestionSetData);

        call.enqueue(new Callback<VisitHealthQuestionSetData>() {
            @Override
            public void onResponse(@NonNull Call<VisitHealthQuestionSetData> call,
                                   @NonNull Response<VisitHealthQuestionSetData> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(context, health_question_record_fail, Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(@NonNull Call<VisitHealthQuestionSetData> call,
                                  @NonNull Throwable t) {

            }
        });
    }

    public void createVisitEducationQuestionSetData(@NonNull VisitEducationQuestionSetData visitEducationQuestionSetData) {

        Call<VisitEducationQuestionSetData> call = jsonPlaceHolderApi
                .createVisitEducationQuestionSetData(visitEducationQuestionSetData);

        call.enqueue(new Callback<VisitEducationQuestionSetData>() {
            @Override
            public void onResponse(@NonNull Call<VisitEducationQuestionSetData> call,
                                   @NonNull Response<VisitEducationQuestionSetData> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(context, education_question_record_fail, Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(@NonNull Call<VisitEducationQuestionSetData> call,
                                  @NonNull Throwable t) {

            }
        });
    }

    public void createVisitSocialQuestionSetData(@NonNull VisitSocialQuestionSetData visitSocialQuestionSetData) {
        Call<VisitSocialQuestionSetData> call = jsonPlaceHolderApi
                .createVisitSocialQuestionSetData(visitSocialQuestionSetData);

        call.enqueue(new Callback<VisitSocialQuestionSetData>() {
            @Override
            public void onResponse(@NonNull Call<VisitSocialQuestionSetData> call,
                                   @NonNull Response<VisitSocialQuestionSetData> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(context, social_question_record_fail, Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(@NonNull Call<VisitSocialQuestionSetData> call,
                                  @NonNull Throwable t) {

            }
        });
    }

    public void createClientDisability(@NonNull ClientDisability clientDisability) {
        Call<ClientDisability> call = jsonPlaceHolderApi.createClientDisability(clientDisability);

        call.enqueue(new Callback<ClientDisability>() {
            @Override
            public void onResponse(@NonNull Call<ClientDisability> call,
                                   @NonNull Response<ClientDisability> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, R.string.disability_record_fail, Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(@NonNull Call<ClientDisability> call,
                                  @NonNull Throwable t) {
            }
        });
    }

    public void createClientHealthAspect(@NonNull ClientHealthAspect clientHealthAspect) {
        Call<ClientHealthAspect> call = jsonPlaceHolderApi.createClientHealthAspect(clientHealthAspect);

        call.enqueue(new Callback<ClientHealthAspect>() {
            @Override
            public void onResponse(@NonNull Call<ClientHealthAspect> call,
                                   @NonNull Response<ClientHealthAspect> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, R.string.health_aspect_record_fail, Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(@NonNull Call<ClientHealthAspect> call,
                                  @NonNull Throwable t) {
            }
        });
    }

    public void createClientEducationAspect(@NonNull ClientEducationAspect clientEducationAspect) {
        Call<ClientEducationAspect> call = jsonPlaceHolderApi.createClientEducationAspect(clientEducationAspect);

        call.enqueue(new Callback<ClientEducationAspect>() {
            @Override
            public void onResponse(@NonNull Call<ClientEducationAspect> call,
                                   @NonNull Response<ClientEducationAspect> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, R.string.education_aspect_record_fail, Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(@NonNull Call<ClientEducationAspect> call,
                                  @NonNull Throwable t) {
            }
        });
    }

    public void createClientSocialAspect(@NonNull ClientSocialAspect clientSocialAspect) {
        Call<ClientSocialAspect> call = jsonPlaceHolderApi.createClientSocialAspect(clientSocialAspect);

        call.enqueue(new Callback<ClientSocialAspect>() {
            @Override
            public void onResponse(@NonNull Call<ClientSocialAspect> call,
                                   @NonNull Response<ClientSocialAspect> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, R.string.social_aspect_record_fail, Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(@NonNull Call<ClientSocialAspect> call,
                                  @NonNull Throwable t) {
            }
        });
    }

    public void createReferralInfo(@NonNull ReferralInfo referralInfo) {
        Call<ReferralInfo> call = jsonPlaceHolderApi.createReferralInfo(referralInfo);

        call.enqueue(new Callback<ReferralInfo>() {
            @Override
            public void onResponse(Call<ReferralInfo> call, Response<ReferralInfo> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, R.string.referral_info_record_fail, Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call<ReferralInfo> call, Throwable t) {

            }
        });
    }
}
