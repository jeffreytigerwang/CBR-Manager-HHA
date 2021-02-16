package com.example.cbr.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
//import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitInit {
    private static Retrofit instance;

    public static Retrofit getInstance() {
        if (instance == null) {
//            instance = new Retrofit.Builder()
//                    .baseUrl("http://142.58.21.129/")
//                    .addConverterFactory(ScalarsConverterFactory.create())
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                    .build();

            instance = new Retrofit.Builder()
                    .baseUrl("http://142.58.21.129/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return instance;
    }
}


// 142.58.21.129/api/test_data/