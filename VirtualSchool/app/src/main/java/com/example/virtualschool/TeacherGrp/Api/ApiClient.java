package com.example.virtualschool.TeacherGrp.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String MAIN_URL = "http://10.0.2.2/virtualSchool/schoolRecords/";
    //public static final String MAIN_URL = "https://schoolappdemo.000webhostapp.com/virtualSchool/schoolRecords/";
    private static Retrofit getRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MAIN_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static TeacherService getTeacherService(){
        TeacherService teacherService = getRetrofit().create(TeacherService.class);
        return teacherService;
    }
}
