package com.example.virtualschool.TeacherGrp.Model;

import java.io.Serializable;

public class TeacherResponse implements Serializable {
    private int teacherId, orgCode;
    private String name, live, status;

    public TeacherResponse(int teacherId, int orgCode, String name, String live, String status) {
        this.teacherId = teacherId;
        this.orgCode = orgCode;
        this.name = name;
        this.live = live;
        this.status = status;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(int orgCode) {
        this.orgCode = orgCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLive() {
        return live;
    }

    public void setLive(String live) {
        this.live = live;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TeacherResponse{" +
                "teacherId=" + teacherId +
                ", orgCode=" + orgCode +
                ", name='" + name + '\'' +
                ", live='" + live + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
