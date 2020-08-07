package com.example.virtualschool.Organisation.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static final String BASE_URL = "http://10.0.2.2/virtualSchool/";
    //public static final String BASE_URL = "https://schoolappdemo.000webhostapp.com/virtualSchool/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    public RetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public api getApi(){
        return retrofit.create(api.class);
    }
}
