package com.example.user.projectandro;

public class Userdef {
    String mail,add,dob;

    public Userdef() {
    }

    public Userdef(String mail, String add, String dob) {
        this.mail = mail;
        this.add = add;
        this.dob = dob;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
