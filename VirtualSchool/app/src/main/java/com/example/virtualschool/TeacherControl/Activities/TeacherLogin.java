package com.example.virtualschool.TeacherControl.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.virtualschool.Organisation.Api.RetrofitClient;
import com.example.virtualschool.Organisation.Storage.SharedPrefManager;
import com.example.virtualschool.R;
import com.example.virtualschool.TeacherControl.Model.FacResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherLogin extends AppCompatActivity implements View.OnClickListener {

    EditText editTextTEmail, editTextTPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);

        editTextTEmail = findViewById(R.id.editfacEmail);
        editTextTPwd = findViewById(R.id.editfacPass);

        findViewById(R.id.buttonLogin).setOnClickListener(this);
    }

    public void facLogin() {
        String email = editTextTEmail.getText().toString().trim();
        String pass = editTextTPwd.getText().toString().trim();

        if (email.isEmpty()) {
            editTextTEmail.setError("Email required");
            editTextTEmail.requestFocus();
            return;
        }
        if (pass.isEmpty()) {
            editTextTPwd.setError("Password field is blank");
            editTextTPwd.requestFocus();
            return;
        }

        Call<FacResponse> call = RetrofitClient.getInstance().getApi().TLogin(email, pass);
        call.enqueue(new Callback<FacResponse>() {
            @Override
            public void onResponse(Call<FacResponse> call, Response<FacResponse> response) {
                FacResponse facResponse = response.body();
                if(!facResponse.isError()){
                    SharedPrefManager.getInstance(TeacherLogin.this)
                            .saveTeacher(facResponse.getFac());

                    Toast.makeText(TeacherLogin.this, facResponse.getMessage(), Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(TeacherLogin.this, TeacherDashboard.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(TeacherLogin.this, facResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<FacResponse> call, Throwable t) {
                Toast.makeText(TeacherLogin.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonLogin:
                facLogin();
                break;
        }
    }
}
