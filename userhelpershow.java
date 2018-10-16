package com.example.user.projectandro;

public class userhelpershow {
    String pic,fname,lname,mail;

    public userhelpershow(String pic, String fname, String lname, String mail) {
        this.pic = pic;
        this.fname = fname;
        this.lname = lname;
        this.mail = mail;
    }

    public userhelpershow() {
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
