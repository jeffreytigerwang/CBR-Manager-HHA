package com.example.cbr.retrofit;

import com.example.cbr.models.ClientDisability;
import com.example.cbr.models.ClientEducationAspect;
import com.example.cbr.models.ClientHealthAspect;
import com.example.cbr.models.ClientInfo;
import com.example.cbr.models.ClientSocialAspect;
import com.example.cbr.models.Users;
import com.example.cbr.models.VisitEducationQuestionSetData;
import com.example.cbr.models.VisitGeneralQuestionSetData;
import com.example.cbr.models.VisitHealthQuestionSetData;
import com.example.cbr.models.VisitSocialQuestionSetData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

// 142.58.21.129/api/test_data/

public interface JsonPlaceHolderApi {

    @GET("api/clients")
    Call<List<ClientInfo>> getClientsInfo();

    @POST("api/disability")
    Call<ClientDisability> createClientDisability(@Body ClientDisability clientDisability);

    @POST("api/healthAspect")
    Call<ClientHealthAspect> createClientHealthAspect(@Body ClientHealthAspect clientHealthAspect);

    @POST("api/educationAspect")
    Call<ClientEducationAspect> createClientEducationAspect(@Body ClientEducationAspect clientEducationAspect);

    @POST("api/socialAspect")
    Call<ClientSocialAspect> createClientSocialAspect(@Body ClientSocialAspect clientSocialAspect);

    @FormUrlEncoded
    @POST("api/clients")
    Call<ClientInfo> createClient(
            @Field("clientId") Integer clientId,
            @Field("firstName") String firstName,
            @Field("lastName") String lastName,
            @Field("gpsLocation") String gpsLocation,
            @Field("zoneLocation") String zoneLocation,
            @Field("villageNumber") Integer villageNumber,
            @Field("age") Integer age,
            @Field("contactNumber") String contactNumber,
            @Field("caregiverPresentForInterview") boolean caregiverPresentForInterview,
            @Field("caregiverContactNumber") Integer caregiverContactNumber
    );


    @POST("api/visits")
    Call<VisitGeneralQuestionSetData> createVisitGeneralQuestionSetData(@Body VisitGeneralQuestionSetData visitGeneralQuestionSetData);

    @POST("api/healthProgress")
    Call<VisitHealthQuestionSetData> createVisitHealthQuestionSetData(@Body VisitHealthQuestionSetData visitHealthQuestionSetData);

    @POST("api/educationProgress")
    Call<VisitEducationQuestionSetData> createVisitEducationQuestionSetData(@Body VisitEducationQuestionSetData visitEducationQuestionSetData);

    @POST("api/socialProgress")
    Call<VisitSocialQuestionSetData> createVisitSocialQuestionSetData(@Body VisitSocialQuestionSetData visitSocialQuestionSetData);

    @GET("api/users")
    Call<List<Users>> getUserEmail(@Query("email") String email);

    @POST("api/users")
    Call<Users> createUser(@Body Users users);

}

























