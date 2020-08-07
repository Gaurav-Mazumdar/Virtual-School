package com.example.virtualschool.Organisation.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.virtualschool.Organisation.Api.RetrofitClient;
import com.example.virtualschool.Organisation.Model.LoginResponse;
import com.example.virtualschool.Organisation.Storage.SharedPrefManager;
import com.example.virtualschool.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrgLogActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextOrgEmail, editTextOrgPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org_log);

        editTextOrgEmail = findViewById(R.id.orgEmail);
        editTextOrgPwd = findViewById(R.id.editTextPass);

        findViewById(R.id.buttonLogin).setOnClickListener(this);
        findViewById(R.id.textViewRegister).setOnClickListener(this);
    }

    public void userLogin(){
        String email = editTextOrgEmail.getText().toString().trim();
        String pass = editTextOrgPwd.getText().toString().trim();

        if (email.isEmpty()) {
            editTextOrgEmail.setError("Email required");
            editTextOrgEmail.requestFocus();
            return;
        }
        if (pass.isEmpty()) {
            editTextOrgPwd.setError("Password field is blank");
            editTextOrgPwd.requestFocus();
            return;
        }

        Call<LoginResponse> call = RetrofitClient.getInstance().getApi().orgLogin(email, pass);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                if(!loginResponse.isError()){
                    SharedPrefManager.getInstance(OrgLogActivity.this)
                            .saveOrg(loginResponse.getOrg());

                    Toast.makeText(OrgLogActivity.this, loginResponse.getMessage(), Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(OrgLogActivity.this, MainDashboard.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(OrgLogActivity.this, loginResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(OrgLogActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonLogin:
                userLogin();
                break;
            case R.id.textViewRegister:
                startActivity(new Intent(this, OrgActivity.class));
                break;
        }
    }
}
