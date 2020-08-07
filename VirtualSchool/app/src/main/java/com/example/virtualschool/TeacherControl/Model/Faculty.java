package com.example.virtualschool.TeacherControl.Model;

public class Faculty {
    int teacherId, orgCode;
    String name, email, contact, qualification, address, status, live;

    public Faculty(int teacherId, int orgCode, String name, String email, String contact, String qualification, String address, String status, String live) {
        this.teacherId = teacherId;
        this.orgCode = orgCode;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.qualification = qualification;
        this.address = address;
        this.status = status;
        this.live = live;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public int getOrgCode() {
        return orgCode;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getContact() {
        return contact;
    }

    public String getQualification() {
        return qualification;
    }

    public String getAddress() {
        return address;
    }

    public String getStatus() {
        return status;
    }

    public String getLive() {
        return live;
    }
}
