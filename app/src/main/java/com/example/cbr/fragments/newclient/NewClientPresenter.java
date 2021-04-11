package com.example.cbr.fragments.newclient;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.cbr.R;
import com.example.cbr.models.ClientDisability;
import com.example.cbr.models.ClientEducationAspect;
import com.example.cbr.models.ClientHealthAspect;
import com.example.cbr.models.ClientInfo;
import com.example.cbr.models.ClientSocialAspect;
import com.example.cbr.retrofit.JsonPlaceHolderApi;
import com.example.cbr.retrofit.RetrofitInit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NewClientPresenter implements NewClientContract.Presenter {

    private NewClientContract.View newClientView;
    private final JsonPlaceHolderApi jsonPlaceHolderApi;
    private final Context context;

    public NewClientPresenter(NewClientContract.View newClientView, Context context) {
        this.newClientView = newClientView;
        this.context = context;
        Retrofit retrofit = RetrofitInit.getInstance();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
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
}
