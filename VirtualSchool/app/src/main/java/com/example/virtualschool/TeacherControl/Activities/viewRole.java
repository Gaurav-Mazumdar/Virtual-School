package com.example.virtualschool.TeacherControl.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.virtualschool.Organisation.Storage.SharedPrefManager;
import com.example.virtualschool.R;
import com.example.virtualschool.TeacherControl.Model.Faculty;
import com.example.virtualschool.TeacherGrp.Activities.roleAdapter;
import com.example.virtualschool.TeacherGrp.Api.ApiClient;
import com.example.virtualschool.TeacherGrp.Model.RoleResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class viewRole extends AppCompatActivity implements roleAdapter.ClickedPeriodItem {

    String query, tname;
    TextView time;
    Toolbar toolbarRole;
    RecyclerView recyclerViewRole;
    roleAdapter roleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_role);

        toolbarRole = findViewById(R.id.toolbarRole);
        recyclerViewRole = findViewById(R.id.recyclerViewRole);
        time = findViewById(R.id.timeshow);

        Faculty fac = SharedPrefManager.getInstance(this).getFac();
        query = Integer.valueOf(fac.getTeacherId()).toString();
        tname = fac.getName();

        time.setText(tname +"\n" + getDate());

        recyclerViewRole.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewRole.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        roleAdapter = new roleAdapter(this::clickedPeriod);

        myRoles();
    }

    public String getDate(){
        SimpleDateFormat df = new SimpleDateFormat("EEEE");
        Date date = new Date();
        return df.format(date);
    }

    public void myRoles(){
        Call<List<RoleResponse>> roleList = ApiClient.getTeacherService().getRoles(query);
        roleList.enqueue(new Callback<List<RoleResponse>>() {
            @Override
            public void onResponse(Call<List<RoleResponse>> call, Response<List<RoleResponse>> response) {
                if(response.isSuccessful()){
                    //Log.e("success: ", response.body().toString());

                    List<RoleResponse> roleResponses = response.body();
                    roleAdapter.setRole(roleResponses);
                    recyclerViewRole.setAdapter(roleAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<RoleResponse>> call, Throwable t) {
                    Log.e("failure: ", t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void clickedPeriod(RoleResponse roleResponse) {
        //Log.e("Clicked Period: ", roleResponse.toString());

        startActivity(new Intent(this, dayAssignment.class).putExtra("assignment", roleResponse));
    }
}