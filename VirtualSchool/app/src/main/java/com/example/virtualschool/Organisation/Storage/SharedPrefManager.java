package com.example.virtualschool.Organisation.Storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.virtualschool.Organisation.Model.Org;
import com.example.virtualschool.TeacherControl.Model.Faculty;

public class SharedPrefManager {
    private static SharedPrefManager mInstance;
    private Context mCtx;
    private static final String SHARED_PREF_NAME = "My_Shared_pref";
    private static final String SHARED_PREF_NAME2 = "My_Shared_pref2";

    private SharedPrefManager(Context mCtx) {
        this.mCtx = mCtx;
    }

    public static synchronized SharedPrefManager getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(mCtx);
        }
        return mInstance;
    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("orgCode", -1) != -1;
    }

    public void clear(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public void saveOrg(Org org) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("orgCode", org.getOrgCode());
        editor.putString("orgName", org.getOrgName());
        editor.putString("estDate", org.getEstDate());
        editor.putString("address", org.getAddress());
        editor.putString("contact", org.getContact());
        editor.putString("email", org.getEmail());
        editor.putString("orgHead", org.getOrgHead());
        editor.putString("status", org.getStatus());
        editor.putString("live", org.getLive());
        editor.putString("run", org.getRun());
        editor.apply();
    }

    public Org getOrg() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        Org org = new Org(
                sharedPreferences.getInt("orgCode", -1),
                sharedPreferences.getString("orgName", null),
                sharedPreferences.getString("estDate", null),
                sharedPreferences.getString("address", null),
                sharedPreferences.getString("contact", null),
                sharedPreferences.getString("email", null),
                sharedPreferences.getString("orgHead", null),
                sharedPreferences.getString("status", null),
                sharedPreferences.getString("live", null),
                sharedPreferences.getString("run", null)
        );
        return org;
    }

    public void saveTeacher(Faculty fac) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME2, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

       editor.putInt("teacherId", fac.getTeacherId());
       editor.putInt("orgCode", fac.getOrgCode());
        editor.putString("name", fac.getName());
        editor.putString("email", fac.getEmail());
        editor.putString("contact", fac.getContact());
        editor.putString("qualification", fac.getQualification());
        editor.putString("address", fac.getAddress());
        editor.putString("status", fac.getStatus());
        editor.putString("live", fac.getLive());
        editor.apply();
    }

    public Faculty getFac() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME2, Context.MODE_PRIVATE);
        Faculty fac = new Faculty(
                sharedPreferences.getInt("teacherId", -1),
                sharedPreferences.getInt("orgCode", -1),
                sharedPreferences.getString("name", null),
                sharedPreferences.getString("email", null),
                sharedPreferences.getString("contact", null),
                sharedPreferences.getString("qualification", null),
                sharedPreferences.getString("address", null),
                sharedPreferences.getString("status", null),
                sharedPreferences.getString("live", null)
        );
        return fac;
    }
}
