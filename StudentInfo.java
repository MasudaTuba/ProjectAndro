package com.example.user.projectandro;


public class StudentInfo {

    String fname, lname,femail,fphn,type;



    public StudentInfo() {

    }
    public StudentInfo(String fname, String lname, String fphn, String type, String femail) {
        this.fname = fname;
        this.lname = lname;
        this.fphn = fphn;
        this.femail = femail;
        this.type=type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFphn() {
        return fphn;
    }

    public void setFphn(String fphn) {
        this.fphn = fphn;
    }

    public String getFemail() { return femail; }

    public void setFemail(String femail) { this.femail = femail; }
}
