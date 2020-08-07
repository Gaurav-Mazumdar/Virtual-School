package com.example.virtualschool.TeacherGrp.Api;

import com.example.virtualschool.TeacherGrp.Model.RoleResponse;
import com.example.virtualschool.TeacherGrp.Model.TeacherResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TeacherService {
        @GET("teachers.php")
        Call<List<TeacherResponse>> getAllTeachers(@Query("orgCode") String orgCode);

        @GET("roles.php")
        Call<List<RoleResponse>> getRoles(@Query("teacherId") String teacherId);
}
