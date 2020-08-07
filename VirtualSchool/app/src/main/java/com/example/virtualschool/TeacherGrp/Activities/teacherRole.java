package com.example.virtualschool.TeacherGrp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.virtualschool.Organisation.Activities.OrgActivity;
import com.example.virtualschool.Organisation.Api.RetrofitClient;
import com.example.virtualschool.Organisation.Model.DefaultResponse;
import com.example.virtualschool.R;
import com.example.virtualschool.TeacherControl.Activities.viewRole;
import com.example.virtualschool.TeacherGrp.Model.TeacherResponse;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class teacherRole extends AppCompatActivity implements View.OnClickListener {

    TextView Tname;
    TeacherResponse teacherResponse;
    Spinner classSpinner, subSpinner, periodSpinner, daySpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_role);

        Tname = findViewById(R.id.Tname);
        classSpinner = findViewById(R.id.spinnerClass);
        subSpinner = findViewById(R.id.spinnerSub);
        periodSpinner = findViewById(R.id.spinnerPeriod);
        daySpinner = findViewById(R.id.spinnerDay);

        findViewById(R.id.buttonAssign).setOnClickListener(this);
        findViewById(R.id.viewRoles).setOnClickListener(this);

        Intent intent = getIntent();
        if(intent.getExtras() != null){
            teacherResponse = (TeacherResponse) intent.getSerializableExtra("data");
            String teacherName = teacherResponse.getName();
            String teacherId = Integer.valueOf(teacherResponse.getTeacherId()).toString();

            Tname.setText("Teacher Id: " + teacherId +" "+ "Teacher Name: " + teacherName);
            //addListenerOnSpinnerSelection();
        }
    }

    public void assignRole(){
        String orgCode = Integer.valueOf(teacherResponse.getOrgCode()).toString();
        String teacherId = Integer.valueOf(teacherResponse.getTeacherId()).toString();
        String teacherName = teacherResponse.getName();
        String assgnedClass = String.valueOf(classSpinner.getSelectedItem());
        String assgnedSub = String.valueOf(subSpinner.getSelectedItem());
        String periodNum = String.valueOf(periodSpinner.getSelectedItem());
        String dayVal = String.valueOf(daySpinner.getSelectedItem());

        retrofit2.Call<DefaultResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .createRole(orgCode, teacherId, teacherName, assgnedClass, assgnedSub, periodNum, dayVal);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(retrofit2.Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.code() == 201) {
                    DefaultResponse dr = response.body();
                    Toast.makeText(teacherRole.this, dr.getMsg(), Toast.LENGTH_LONG).show();

                } else if (response.code() == 422) {
                    Toast.makeText(teacherRole.this, "Some Error Occured", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Toast.makeText(teacherRole.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    /*public void addListenerOnSpinnerSelection(){
        classSpinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }*/

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonAssign:
                assignRole();
                break;
            case R.id.viewRoles:
                startActivity(new Intent(this, viewRole.class));
        }
       // Toast.makeText(teacherRole.this, "Clicked Value : " + classSpinner.getSelectedItem() + " " + subSpinner.getSelectedItem(), Toast.LENGTH_SHORT).show();
    }
}
