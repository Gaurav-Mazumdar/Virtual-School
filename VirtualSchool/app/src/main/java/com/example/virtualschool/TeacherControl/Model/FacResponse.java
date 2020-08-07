package com.example.virtualschool.TeacherControl.Model;

public class FacResponse {
    private boolean error;
    private String message;
    private Faculty fac;

    public FacResponse(boolean error, String message, Faculty fac) {
        this.error = error;
        this.message = message;
        this.fac = fac;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public Faculty getFac() {
        return fac;
    }
}
