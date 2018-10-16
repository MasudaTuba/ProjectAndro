package com.example.user.projectandro;

public class Collectlawyer {
    String fname,lname;

    public Collectlawyer(String fname, String lname) {
        this.fname = fname;
        this.lname = lname;
    }

    public Collectlawyer() {
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
}
