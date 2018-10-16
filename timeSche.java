package com.example.user.projectandro;

public class timeSche {
    String umail,helpermail,date,time,place;

    public timeSche(String umail, String helpermail, String date, String time, String place) {
        this.umail = umail;
        this.helpermail = helpermail;
        this.date = date;
        this.time = time;
        this.place = place;
    }

    public timeSche() {
    }

    public String getUmail() {
        return umail;
    }

    public void setUmail(String umail) {
        this.umail = umail;
    }

    public String getHelpermail() {
        return helpermail;
    }

    public void setHelpermail(String helpermail) {
        this.helpermail = helpermail;
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
