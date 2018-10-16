package com.example.user.projectandro;


public class StudentIn {

    String fname,fphn,femail,type;


    public  StudentIn()
    {

    }
    public StudentIn(String fname, String fphn, String femail, String type) {
        this.fname = fname;
        this.fphn = fphn;
        this.femail = femail;
        this.type = type;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFphn() {
        return fphn;
    }

    public void setFphn(String fphn) {
        this.fphn = fphn;
    }

    public String getFemail() {
        return femail;
    }

    public void setFemail(String femail) {
        this.femail = femail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
