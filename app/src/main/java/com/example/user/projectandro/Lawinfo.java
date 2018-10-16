package com.example.user.projectandro;

public class Lawinfo {
    String ofadd, exp,qual,active,mail;

    public Lawinfo(String ofadd, String exp, String qual, String active, String mail) {
        this.ofadd = ofadd;
        this.exp = exp;
        this.qual = qual;
        this.active = active;
        this.mail=mail;
    }

    public Lawinfo() {

    }

    public Lawinfo(String mail) {
        this.mail = mail;
    }

    public String getOfadd() {
        return ofadd;
    }

    public void setOfadd(String ofadd) {
        this.ofadd = ofadd;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getQual() {
        return qual;
    }

    public void setQual(String qual) {
        this.qual = qual;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
