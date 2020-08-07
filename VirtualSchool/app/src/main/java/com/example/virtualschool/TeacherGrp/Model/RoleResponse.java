package com.example.virtualschool.TeacherGrp.Model;

import java.io.Serializable;

public class RoleResponse implements Serializable {
    private int teacherId;
    private String assignedClass, assignedSub, periodNum, dayVal;

    public RoleResponse(int teacherId, String assignedClass, String assignedSub, String periodNum, String dayVal) {
        this.teacherId = teacherId;
        this.assignedClass = assignedClass;
        this.assignedSub = assignedSub;
        this.periodNum = periodNum;
        this.dayVal = dayVal;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getAssignedClass() {
        return assignedClass;
    }

    public void setAssignedClass(String assignedClass) {
        this.assignedClass = assignedClass;
    }

    public String getAssignedSub() {
        return assignedSub;
    }

    public void setAssignedSub(String assignedSub) {
        this.assignedSub = assignedSub;
    }

    public String getPeriodNum() {
        return periodNum;
    }

    public void setPeriodNum(String periodNum) {
        this.periodNum = periodNum;
    }

    public String getDayVal() {
        return dayVal;
    }

    public void setDayVal(String dayVal) {
        this.dayVal = dayVal;
    }

    @Override
    public String toString() {
        return "RoleResponse{" +
                "teacherId=" + teacherId +
                ", assignedClass='" + assignedClass + '\'' +
                ", assignedSub='" + assignedSub + '\'' +
                ", periodNum='" + periodNum + '\'' +
                ", dayVal='" + dayVal + '\'' +
                '}';
    }
}
