package com.example.user.projectandro;

public class vitimTimeSche {
    String fname,lname,date,time,place;

    public vitimTimeSche(String fname, String lname, String date, String time, String place) {
        this.fname = fname;
        this.lname = lname;
        this.date = date;
        this.time = time;
        this.place = place;
    }

    public vitimTimeSche() {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
