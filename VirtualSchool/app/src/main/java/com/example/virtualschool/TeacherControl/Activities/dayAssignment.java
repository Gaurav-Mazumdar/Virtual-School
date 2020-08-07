package com.example.virtualschool.TeacherControl.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.virtualschool.Organisation.Api.RetrofitClient;
import com.example.virtualschool.Organisation.Model.DefaultResponse;
import com.example.virtualschool.R;
import com.example.virtualschool.TeacherGrp.Model.RoleResponse;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class dayAssignment extends AppCompatActivity implements View.OnClickListener {

    TextView teacherId, teacherClass, teacherPeriod, teacherSub, curDate, periodCode;
    EditText topicName, urlLink;

    String teacherIdtxt, teacherClasstxt, teacherPeriodtxt, teacherSubtxt;

    RoleResponse roleResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_assignment);

        teacherId = findViewById(R.id.showName);
        teacherClass = findViewById(R.id.showClass);
        teacherPeriod = findViewById(R.id.showPeriod);
        teacherSub = findViewById(R.id.showSub);
        curDate = findViewById(R.id.showDate);
        periodCode = findViewById(R.id.genCode);
        topicName = findViewById(R.id.topicName);
        urlLink = findViewById(R.id.assignUrl);

        findViewById(R.id.buttonAssign).setOnClickListener(this);

        Intent intent = getIntent();
        if(intent.getExtras() != null){
            roleResponse = (RoleResponse) intent.getSerializableExtra("assignment");
            teacherIdtxt = Integer.valueOf(roleResponse.getTeacherId()).toString();
            teacherClasstxt = roleResponse.getAssignedClass();
            teacherPeriodtxt = roleResponse.getPeriodNum();
            teacherSubtxt = roleResponse.getAssignedSub();
            String curDatetxt = roleResponse.getDayVal();

            teacherId.setText(teacherIdtxt);
            teacherClass.setText(teacherClasstxt);
            teacherPeriod.setText(teacherPeriodtxt);
            teacherSub.setText(teacherSubtxt);
            curDate.setText(curDatetxt);

            int unqId = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
            String pCode = Integer.valueOf(unqId).toString();
            periodCode.setText(pCode);
        }
    }

    private void saveAssignment(){
        String tid = teacherId.getText().toString().trim();
        String std = teacherClass.getText().toString().trim();
        String period = teacherPeriod.getText().toString().trim();
        String subject = teacherSub.getText().toString().trim();
        String topic = topicName.getText().toString().trim();
        String link = urlLink.getText().toString().trim();
        String assignCode = periodCode.getText().toString().trim();

        if (topic.isEmpty()) {
            topicName.setError("Topic title is required");
            topicName.requestFocus();
            return;
        }
        if (link.isEmpty()) {
            urlLink.setError("url field cannot be blank blank");
            urlLink.requestFocus();
            return;
        }

        Call<DefaultResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .createAssignment(tid, std, period, subject, topic, link, assignCode);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.code() == 201) {
                    DefaultResponse dr = response.body();
                    Toast.makeText(dayAssignment.this, dr.getMsg(), Toast.LENGTH_LONG).show();
                }
                else if(response.code() == 422){
                    Toast.makeText(dayAssignment.this, "Something went wrong", Toast.LENGTH_LONG).show();
                }
                else if (response.code() == 500) {
                    Toast.makeText(dayAssignment.this, "Error in Code", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Toast.makeText(dayAssignment.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonAssign:
                saveAssignment();
                break;
        }
    }
}
