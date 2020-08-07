package com.example.virtualschool.TeacherGrp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.virtualschool.Organisation.Api.RetrofitClient;
import com.example.virtualschool.Organisation.Model.DefaultResponse;
import com.example.virtualschool.Organisation.Model.Org;
import com.example.virtualschool.Organisation.Storage.SharedPrefManager;
import com.example.virtualschool.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewTeacher extends AppCompatActivity implements View.OnClickListener {

    private EditText editTName, editTEmail, editTPassword, editTContact, editTQualification, editTAddress;
    TextView tvOrgCode;
    String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_teacher);

        tvOrgCode = findViewById(R.id.orgId);
        editTName = findViewById(R.id.editTName);
        editTEmail = findViewById(R.id.editTEmail);
        editTPassword = findViewById(R.id.editTPassword);
        editTContact = findViewById(R.id.editTContact);
        editTQualification = findViewById(R.id.editTQualification);
        editTAddress = findViewById(R.id.editTAddress);

        Org org = SharedPrefManager.getInstance(this).getOrg();
        code = Integer.valueOf(org.getOrgCode()).toString();
        tvOrgCode.setText(code);

        findViewById(R.id.buttonAddTeacher).setOnClickListener(this);
    }

    private void AddTeacher() {
        String orgCode = tvOrgCode.getText().toString().trim();
        String name = editTName.getText().toString().trim();
        String email = editTEmail.getText().toString().trim();
        String password = editTPassword.getText().toString().trim();
        String contact = editTContact.getText().toString().trim();
        String qualification = editTQualification.getText().toString().trim();
        String address = editTAddress.getText().toString().trim();

        if (name.isEmpty()) {
            editTName.setError("Teacher Name is required");
            editTName.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTEmail.setError("Email is required");
            editTEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTEmail.setError("Enter a valid email");
            editTEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTPassword.setError("Password required");
            editTPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTPassword.setError("Password should be atleast 6 character long");
            editTPassword.requestFocus();
            return;
        }

        if (contact.isEmpty()) {
            editTContact.setError("Mobile number is required");
            editTContact.requestFocus();
            return;
        }

        if (contact.length() > 10 || contact.length() < 10) {
            editTContact.setError("Mobile number cannot be more or less then 10 digit");
            editTContact.requestFocus();
            return;
        }

        if (qualification.isEmpty()) {
            editTQualification.setError("Qualification is required");
            editTQualification.requestFocus();
            return;
        }

        if (address.isEmpty()) {
            editTAddress.setError("Address is required");
            editTAddress.requestFocus();
            return;
        }

        Call<DefaultResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .createNewTeacher(orgCode, name, email, password, contact, qualification, address);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.code() == 201) {
                    DefaultResponse dr = response.body();
                    Toast.makeText(AddNewTeacher.this, dr.getMsg(), Toast.LENGTH_LONG).show();

                } else if (response.code() == 422) {
                    Toast.makeText(AddNewTeacher.this, "Teacher already exists", Toast.LENGTH_LONG).show();
                }
                else if (response.code() == 500) {
                    Toast.makeText(AddNewTeacher.this, "Error in Code", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Toast.makeText(AddNewTeacher.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonAddTeacher:
                AddTeacher();
                break;
        }
    }
}
