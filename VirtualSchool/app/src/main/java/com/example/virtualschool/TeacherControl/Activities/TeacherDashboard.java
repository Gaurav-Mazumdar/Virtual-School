package com.example.virtualschool.TeacherControl.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.virtualschool.Organisation.Storage.SharedPrefManager;
import com.example.virtualschool.R;
import com.example.virtualschool.TeacherControl.Model.Faculty;

public class TeacherDashboard extends AppCompatActivity implements View.OnClickListener {

    TextView welcomeTextT, viewTCode, viewTEmail;
    String teacherId, code ;
    Faculty fac = SharedPrefManager.getInstance(this).getFac();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);

        welcomeTextT = findViewById(R.id.welcomeT);
        viewTCode = findViewById(R.id.dashboardT);
        viewTEmail = findViewById(R.id.viewTEmail);

        findViewById(R.id.viewStud).setOnClickListener(this);
        findViewById(R.id.AddnewStud).setOnClickListener(this);
        findViewById(R.id.logoutT).setOnClickListener(this);
        findViewById(R.id.addAssignment).setOnClickListener(this);

        welcomeTextT.setText("Welcome: "+fac.getName());
        viewTEmail.setText(fac.getEmail());

        teacherId = Integer.valueOf(fac.getTeacherId()).toString();
        code = Integer.valueOf(fac.getOrgCode()).toString();
        viewTCode.setText(teacherId);
    }

    public void logout(){
        SharedPrefManager.getInstance(this).clear();
        Intent intent = new Intent(this, TeacherLogin.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addAssignment:
                startActivity(new Intent(this, viewRole.class));
                break;

            case R.id.logoutT:
                logout();
                break;
        }
    }
}
