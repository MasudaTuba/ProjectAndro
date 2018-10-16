package com.example.user.projectandro;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;



public class MyListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<Parent> ParentList;
    private ArrayList<Parent> originalList;

    public MyListAdapter(Context context, ArrayList<Parent> ParentList) {
        this.context = context;
        this.ParentList = new ArrayList<Parent>();
        this.ParentList.addAll(ParentList);
        this.originalList = new ArrayList<Parent>();
        this.originalList.addAll(ParentList);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<Child> ChildList = ParentList.get(groupPosition).getChildList();
        return ChildList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View view, ViewGroup parent) {

        Child Child = (Child) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.child_row, null);
        }

        TextView code = (TextView) view.findViewById(R.id.code);
        TextView name = (TextView) view.findViewById(R.id.name);
        //TextView population = (TextView) view.findViewById(R.id.population);
        code.setText(Child.getCode().trim());
        name.setText(Child.getName().trim());

        return view;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        ArrayList<Child> ChildList = ParentList.get(groupPosition).getChildList();
        return ChildList.size();

    }

    @Override
    public Object getGroup(int groupPosition) {
        return ParentList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return ParentList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isLastChild, View view,
                             ViewGroup parent) {

        Parent Parent = (Parent) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.group_row, null);
        }

        TextView heading = (TextView) view.findViewById(R.id.heading);
        heading.setText(Parent.getName().trim());

        return view;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void filterData(String query){

        query = query.toLowerCase();
        Log.v("MyListAdapter", String.valueOf(ParentList.size()));
        ParentList.clear();

        if(query.isEmpty()){
            ParentList.addAll(originalList);
        }
        else {

            for(Parent Parent: originalList){

                ArrayList<Child> ChildList = Parent.getChildList();
                ArrayList<Child> newList = new ArrayList<Child>();
                for(Child Child: ChildList){
                    if(Child.getCode().toLowerCase().contains(query) ||
                            Child.getName().toLowerCase().contains(query)){
                        newList.add(Child);
                    }
                }
                if(newList.size() > 0){
                    Parent nParent = new Parent(Parent.getName(),newList);
                    ParentList.add(nParent);
                }
            }
        }

        Log.v("MyListAdapter", String.valueOf(ParentList.size()));
        notifyDataSetChanged();

    }

}

