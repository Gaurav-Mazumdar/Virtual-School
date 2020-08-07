package com.example.virtualschool.Organisation.Model;

public class Org {
    private int orgCode;
    private String orgName, estDate, address, contact, email, orgHead, status, live, run;

    public Org(int orgCode, String orgName, String estDate, String address, String contact, String email, String orgHead, String status, String live, String run) {
        this.orgCode = orgCode;
        this.orgName = orgName;
        this.estDate = estDate;
        this.address = address;
        this.contact = contact;
        this.email = email;
        this.orgHead = orgHead;
        this.status = status;
        this.live = live;
        this.run = run;
    }

    public int getOrgCode() {
        return orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public String getEstDate() {
        return estDate;
    }

    public String getAddress() {
        return address;
    }

    public String getContact() {
        return contact;
    }

    public String getEmail() {
        return email;
    }

    public String getOrgHead() {
        return orgHead;
    }

    public String getStatus() {
        return status;
    }

    public String getLive() {
        return live;
    }

    public String getRun() {
        return run;
    }
}
