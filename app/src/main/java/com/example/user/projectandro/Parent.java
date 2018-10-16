package com.example.user.projectandro;

import java.util.ArrayList;
public class Parent {




    private String name;
    private ArrayList<Child> ChildList = new ArrayList<Child>();

    public Parent(String name, ArrayList<Child> ChildList) {
        super();
        this.name = name;
        this.ChildList = ChildList;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Child> getChildList() {
        return ChildList;
    }
    public void setChildList(ArrayList<Child> ChildList) {
        this.ChildList = ChildList;
    };


}

