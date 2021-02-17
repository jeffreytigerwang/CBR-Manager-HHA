package com.example.cbr.retrofit;

import com.example.cbr.models.ClientInfo;
import com.example.cbr.models.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

// 142.58.21.129/api/test_data/

public interface JsonPlaceHolderApi {

    @GET("api/clients")
    Call<List<ClientInfo>> getClientsInfo();

//    @POST("api/clients")
//    Call<ClientInfo> createClient(@Body ClientInfo clientInfo);


    @FormUrlEncoded
    @POST("api/clients")
    Call<ClientInfo> createClient(
            @Field("first_name") String first_name,
            @Field("last_name") String last_name,
            @Field("age") int age
    );

    @POST("api/users")
    Call<Users> createUser(@Body Users users);



}
