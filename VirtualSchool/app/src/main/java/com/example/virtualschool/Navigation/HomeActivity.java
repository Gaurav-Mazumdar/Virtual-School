package com.example.virtualschool.Navigation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.virtualschool.Organisation.Activities.OrgActivity;
import com.example.virtualschool.R;
import com.example.virtualschool.TeacherControl.Activities.TeacherLogin;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViewById(R.id.btnOrg).setOnClickListener(this);
        findViewById(R.id.btnTeachers).setOnClickListener(this);
        findViewById(R.id.btnStudents).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnOrg:
                startActivity(new Intent(this, OrgActivity.class));
                break;

            case R.id.btnTeachers:
                startActivity(new Intent(this, TeacherLogin.class));
                break;

            case R.id.btnStudents:
                //startActivity(new Intent(this, StudentLogin.class));
                break;
        }
    }
}
