package com.example.user.projectandro;

public class vicinfo {
    String fname, lname,femail,fphn,type;

    public vicinfo(String fname, String lname, String femail, String fphn, String type) {
        this.fname = fname;
        this.lname = lname;
        this.femail = femail;
        this.fphn = fphn;
        this.type = type;
    }

    public vicinfo() {
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getFemail() {
        return femail;
    }

    public void setFemail(String femail) {
        this.femail = femail;
    }

    public String getFphn() {
        return fphn;
    }

    public void setFphn(String fphn) {
        this.fphn = fphn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
