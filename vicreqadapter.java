package com.example.user.projectandro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class vicreqadapter extends BaseAdapter{

    private Context activity;
    private ArrayList<vicinfo> allvic = new ArrayList<>();
    private LayoutInflater layoutInflater = null;

    public vicreqadapter(Context activity, ArrayList<vicinfo> allvic) {
        this.activity = activity;
        this.allvic = allvic;
        this.layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private static class ViewHolder{
        private TextView name, email, phn, type; }
    private ViewHolder viewHolder = null;
    @Override
    public int getCount() {
        return allvic.size();
    }

    @Override
    public Object getItem(int position) {
        return allvic.get(position);
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
            view = layoutInflater.inflate(R.layout.vicreq,null);
            viewHolder.name = view.findViewById(R.id.name);
            viewHolder.email = view.findViewById(R.id.email);
            viewHolder.phn =  view.findViewById(R.id.phn);
            viewHolder.type =  view.findViewById(R.id.type);
            view.setTag(viewHolder);         }
            else {
            viewHolder= (ViewHolder) view.getTag();         }
        viewHolder.name.setText(allvic.get(pos).getFname());
        viewHolder.email.setText(allvic.get(pos).getFemail());
        viewHolder.phn.setText(allvic.get(pos).getFphn());
        viewHolder.type.setText(allvic.get(pos).getType());
        return view;
    }
}
