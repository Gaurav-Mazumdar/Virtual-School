package com.example.virtualschool.Organisation.Model;

public class LoginResponse {
    private boolean error;
    private String message;
    private Org org;

    public LoginResponse(boolean error, String message, Org org) {
        this.error = error;
        this.message = message;
        this.org = org;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public Org getOrg() {
        return org;
    }
}
