package com.example.virtualschool.Organisation.Api;

import com.example.virtualschool.Organisation.Model.DefaultResponse;
import com.example.virtualschool.Organisation.Model.LoginResponse;
import com.example.virtualschool.TeacherControl.Model.FacResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface api {
    @FormUrlEncoded
    @POST("public/createOrganisation")
    Call<DefaultResponse> createOrganisation(
            @Field("orgName") String orgName,
            @Field("estDate") String estDate,
            @Field("address") String address,
            @Field("contact") String contact,
            @Field("email") String email,
            @Field("password") String password,
            @Field("orgHead") String orgHead,
            @Field("run") String run
    );

    @FormUrlEncoded
    @POST("public/orgLogin")
    Call<LoginResponse> orgLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @PUT("public/orgLiveState/{orgCode}")
    Call<LoginResponse> orgLiveState(
            @Path("orgCode") int orgCode,
            @Field("live") String live,
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("public/createTeacher")
    Call<DefaultResponse> createNewTeacher(
            @Field("orgCode") String orgCode,
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("contact") String contact,
            @Field("qualification") String qualification,
            @Field("address") String address
    );

    @FormUrlEncoded
    @POST("public/teacherLogin")
    Call<FacResponse> TLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("public/createTeacherRole")
    Call<DefaultResponse> createRole(
            @Field("orgCode") String orgCode,
            @Field("teacherId") String teacherId,
            @Field("teacherName") String teacherName,
            @Field("assignedClass") String assignedClass,
            @Field("assignedSub") String assignedSub,
            @Field("periodNum") String periodNum,
            @Field("dayVal") String dayVal
    );

    @FormUrlEncoded
    @POST("public/createAssignment")
    Call<DefaultResponse> createAssignment(
            @Field("tId") String tId,
            @Field("std") String std,
            @Field("period") String period,
            @Field("subject") String subject,
            @Field("topic") String topic,
            @Field("link") String link,
            @Field("assignCode") String assignCode
    );
}
