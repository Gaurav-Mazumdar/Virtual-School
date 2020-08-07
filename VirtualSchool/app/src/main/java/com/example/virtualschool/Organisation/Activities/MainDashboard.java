package com.example.virtualschool.Organisation.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.virtualschool.Organisation.Api.RetrofitClient;
import com.example.virtualschool.Organisation.Model.LoginResponse;
import com.example.virtualschool.Organisation.Model.Org;
import com.example.virtualschool.Organisation.Storage.SharedPrefManager;
import com.example.virtualschool.R;
import com.example.virtualschool.TeacherGrp.Activities.AddNewTeacher;
import com.example.virtualschool.TeacherGrp.Activities.teacherList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainDashboard extends AppCompatActivity implements View.OnClickListener {

    TextView welcomeText, viewCode, viewEmail, normal;
    String code;
    Org org = SharedPrefManager.getInstance(this).getOrg();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);

        welcomeText = findViewById(R.id.welcome);
        viewCode = findViewById(R.id.dashboard);
        viewEmail = findViewById(R.id.viewEmail);

        //normal = findViewById(R.id.normals);

        findViewById(R.id.viewTeacher).setOnClickListener(this);
        findViewById(R.id.Addnew).setOnClickListener(this);
        findViewById(R.id.logout).setOnClickListener(this);
        //findViewById(R.id.normals).setOnClickListener(this);

        welcomeText.setText("Welcome: "+org.getOrgName());
        viewEmail.setText(org.getEmail());

        code = Integer.valueOf(org.getOrgCode()).toString();
        viewCode.setText(code);

        onlineState();
    }

    public void onlineState(){
        String orgCode = viewCode.getText().toString().trim();
        String email = viewEmail.getText().toString().trim();

        if(SharedPrefManager.getInstance(this).isLoggedIn()) {
            String live = "1";
            Call<LoginResponse> call = RetrofitClient.getInstance().getApi().orgLiveState(org.getOrgCode(), live, email);

            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (!response.body().isError()) {
                        Toast.makeText(MainDashboard.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                    } else if (response.body().isError()) {
                        Toast.makeText(MainDashboard.this, "Error may be in Api Link", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(MainDashboard.this, t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public void logout(){
        String live = "0";
        String email = viewEmail.getText().toString().trim();
        Call<LoginResponse> call = RetrofitClient.getInstance().getApi().orgLiveState(org.getOrgCode(), live, email);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (!response.body().isError()) {
                    Toast.makeText(MainDashboard.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                } else if (response.body().isError()) {
                    Toast.makeText(MainDashboard.this, "Error may be in Api Link", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(MainDashboard.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        SharedPrefManager.getInstance(this).clear();
        Intent intent = new Intent(this, OrgLogActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.viewTeacher:
                startActivity(new Intent(this, teacherList.class));
                break;
            case R.id.Addnew:
                startActivity(new Intent(this, AddNewTeacher.class));
                break;
            case R.id.logout:
                logout();
                break;
            /*case R.id.normals:
                onlineState();
                break;*/
        }
    }
}
