package com.example.virtualschool.TeacherGrp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.virtualschool.Organisation.Model.Org;
import com.example.virtualschool.Organisation.Storage.SharedPrefManager;
import com.example.virtualschool.R;
import com.example.virtualschool.TeacherGrp.Api.ApiClient;
import com.example.virtualschool.TeacherGrp.Model.TeacherResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class teacherList extends AppCompatActivity implements teacherAdapter.ClickedItem {

    Toolbar toolbar;
    RecyclerView recyclerView;
    teacherAdapter teacherAdapter;
    private TextView textClass;
    String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_list);

        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerViewTeachers);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        Org org = SharedPrefManager.getInstance(this).getOrg();
        query = Integer.valueOf(org.getOrgCode()).toString();

        teacherAdapter = new teacherAdapter(this::ClickedUser);
        myStudents();
    }

    private void myStudents() {
        Call<List<TeacherResponse>> teacherList = ApiClient.getTeacherService().getAllTeachers(query);

        teacherList.enqueue(new Callback<List<TeacherResponse>>() {
            @Override
            public void onResponse(Call<List<TeacherResponse>> call, Response<List<TeacherResponse>> response) {
                if(response.isSuccessful()){
                    List<TeacherResponse> teacherResponses = response.body();
                    teacherAdapter.setData(teacherResponses);
                    recyclerView.setAdapter(teacherAdapter);
                    Log.e("success", response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<List<TeacherResponse>> call, Throwable t) {
                Log.e("Failure", t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void ClickedUser(TeacherResponse teacherResponse) {
        //Log.e("clicked item", teacherResponse.toString());

        startActivity(new Intent(this, teacherRole.class).putExtra("data", teacherResponse));
    }
}
