package com.example.user.projectandro;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

class userhelperAdapter extends BaseAdapter{

    private Context activity;
    RecyclerView helperprofile;
    private ArrayList<userhelpershow> allhelper = new ArrayList<>();
    private LayoutInflater layoutInflater = null;

    public userhelperAdapter(Context activity, ArrayList<userhelpershow> allhelper) {
        this.activity = activity;
        this.allhelper = allhelper;
        this.layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    private static class ViewHolder{
        private TextView fname,lname,mail;
        private ImageView img;}
    private ViewHolder viewHolder = null;
    @Override
    public int getCount() {
        return allhelper.size();
    }

    @Override
    public Object getItem(int position) {
        return allhelper.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=convertView;
        final int pos = position;
        if(view == null){
            viewHolder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.helpercard,null);
            viewHolder.fname = view.findViewById(R.id.name);
            viewHolder.lname=view.findViewById(R.id.lname);
            viewHolder.mail=view.findViewById(R.id.mail);
            viewHolder.img=view.findViewById(R.id.propic);
            view.setTag(viewHolder);         }
        else {
            viewHolder= (ViewHolder) view.getTag();         }
        viewHolder.fname.setText(allhelper.get(pos).getFname());
        viewHolder.lname.setText(allhelper.get(pos).getLname());
        viewHolder.mail.setText(allhelper.get(pos).getMail());

        viewHolder.img.setImageResource(R.drawable.consult);

        return view;
    }
}


