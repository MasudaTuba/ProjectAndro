package com.example.user.projectandro;

public class requestHelp {
    String helper,user,caseopen,noti;

    public requestHelp(String helper, String user, String caseopen,String noti) {
        this.helper = helper;
        this.user = user;
        this.caseopen = caseopen;
        this.noti=noti;
    }

    public requestHelp() {
    }

    public String getHelper() {
        return helper;
    }

    public void setHelper(String helper) {
        this.helper = helper;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCaseopen() {
        return caseopen;
    }

    public void setCaseopen(String caseopen) {
        this.caseopen = caseopen;
    }

    public String getNoti() {
        return noti;
    }

    public void setNoti(String noti) {
        this.noti = noti;
    }
}
