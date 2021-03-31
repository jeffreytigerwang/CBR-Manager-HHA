package com.example.cbr.fragments.newclient;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.cbr.models.ClientDisability;
import com.example.cbr.models.ClientEducationAspect;
import com.example.cbr.models.ClientHealthAspect;
import com.example.cbr.models.ClientInfo;
import com.example.cbr.models.ClientSocialAspect;
import com.example.cbr.retrofit.JsonPlaceHolderApi;
import com.example.cbr.retrofit.RetrofitInit;
import com.example.cbr.util.Constants;

import java.util.ArrayList;

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

    public void createClientInfo(@NonNull ClientInfo clientInfoData) {
    }

    public void createClientDisability(@NonNull ClientDisability clientDisability) {
        Call<ClientDisability> call = jsonPlaceHolderApi.createClientDisability(clientDisability);

        call.enqueue(new Callback<ClientDisability>() {
            @Override
            public void onResponse(@NonNull Call<ClientDisability> call,
                                   @NonNull Response<ClientDisability> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(context, "Disability enqueue fail text", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(context, "Disability enqueue success text", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NonNull Call<ClientDisability> call,
                                  @NonNull Throwable t) {

            }
        });
    }

    public void createClientHealthAspect(@NonNull ClientHealthAspect clientHealthAspect) {

    }

    public void createClientEducationAspect(@NonNull ClientEducationAspect clientEducationAspect) {

    }

    public void createClientSocialAspect(@NonNull ClientSocialAspect clientSocialAspect) {

    }
}
