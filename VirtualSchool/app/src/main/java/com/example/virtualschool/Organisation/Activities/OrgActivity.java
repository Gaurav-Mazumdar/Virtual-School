package com.example.virtualschool.Organisation.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.virtualschool.Organisation.Api.RetrofitClient;
import com.example.virtualschool.Organisation.Model.DefaultResponse;
import com.example.virtualschool.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrgActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editOrgName, editOrgEstDate, editOrgAddress, editOrgContact, editOrgEmail, editOrgPass, editOrgHead, editOrgRun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_org);

        editOrgName = findViewById(R.id.editTextOrgName);
        editOrgEstDate = findViewById(R.id.editTextEstDate);
        editOrgAddress = findViewById(R.id.editTextAddress);
        editOrgContact = findViewById(R.id.editTextContact);
        editOrgEmail = findViewById(R.id.editTextEmail);
        editOrgPass = findViewById(R.id.editTextOrgPwd);
        editOrgHead = findViewById(R.id.editTextHead);
        editOrgRun = findViewById(R.id.editTextExecute);

        findViewById(R.id.buttonSignUp).setOnClickListener(this);
        findViewById(R.id.textViewLogin).setOnClickListener(this);
    }

    private void userSignUp() {
        String orgName = editOrgName.getText().toString().trim();
        String orgEstDate = editOrgEstDate.getText().toString().trim();
        String orgAdr = editOrgAddress.getText().toString().trim();
        String orgContact = editOrgContact.getText().toString().trim();
        String orgEmail = editOrgEmail.getText().toString().trim();
        String orgPass = editOrgPass.getText().toString().trim();
        String orgHead = editOrgHead.getText().toString().trim();
        String orgRun = editOrgRun.getText().toString().trim();

        if (orgName.isEmpty()) {
            editOrgName.setError("Organisation Name is required");
            editOrgName.requestFocus();
            return;
        }

        if (orgEstDate.isEmpty()) {
            editOrgEstDate.setError("Organisation estabilishment date required");
            editOrgEstDate.requestFocus();
            return;
        }

        if (orgAdr.isEmpty()) {
            editOrgAddress.setError("Address is required");
            editOrgAddress.requestFocus();
            return;
        }

        if (orgContact.isEmpty()) {
            editOrgContact.setError("Mobile number required");
            editOrgContact.requestFocus();
            return;
        }

        if (orgContact.length() > 10 || orgContact.length() < 10) {
            editOrgContact.setError("Mobile number cannot be more or less then 10 digit");
            editOrgContact.requestFocus();
            return;
        }

        if (orgEmail.isEmpty()) {
            editOrgEmail.setError("Email required");
            editOrgEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(orgEmail).matches()) {
            editOrgEmail.setError("Enter a valid Email Address");
            editOrgEmail.requestFocus();
            return;
        }

        if (orgPass.isEmpty()) {
            editOrgPass.setError("Password is required");
            editOrgPass.requestFocus();
            return;
        }

        if (orgHead.isEmpty()) {
            editOrgHead.setError("Organisation head is required");
            editOrgHead.requestFocus();
            return;
        }

        if (orgRun.isEmpty()) {
            editOrgRun.setError("Organisation Type is required");
            editOrgRun.requestFocus();
            return;
        }

        Call<DefaultResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .createOrganisation(orgName, orgEstDate, orgAdr, orgContact, orgEmail, orgPass, orgHead, orgRun);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.code() == 201) {
                    DefaultResponse dr = response.body();
                    Toast.makeText(OrgActivity.this, dr.getMsg(), Toast.LENGTH_LONG).show();

                } else if (response.code() == 422) {
                    Toast.makeText(OrgActivity.this, "Organisation already registered", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Toast.makeText(OrgActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSignUp:
                userSignUp();
                break;
            case R.id.textViewLogin:
                startActivity(new Intent(this, OrgLogActivity.class));
                break;
        }
    }
}
