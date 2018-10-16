package com.example.user.projectandro;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TimeScheAdapter extends BaseAdapter {
    private Context activity;
    RecyclerView helperprofile;
    private ArrayList<vitimTimeSche> allhelper = new ArrayList<>();
    private LayoutInflater layoutInflater = null;

    public TimeScheAdapter(Context activity, ArrayList<vitimTimeSche> allhelper) {
        this.activity = activity;
        this.allhelper = allhelper;
        this.layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private static class ViewHolder{
        private TextView name,date,time,place;}
    private TimeScheAdapter.ViewHolder viewHolder = null;
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
            viewHolder = new TimeScheAdapter.ViewHolder();
            view = layoutInflater.inflate(R.layout.timeschecard,null);
            viewHolder.name = view.findViewById(R.id.name);
            viewHolder.date=view.findViewById(R.id.date);
            viewHolder.time=view.findViewById(R.id.time);
            viewHolder.place=view.findViewById(R.id.place);
            view.setTag(viewHolder);         }
        else {
            viewHolder= (TimeScheAdapter.ViewHolder) view.getTag();         }
        viewHolder.name.setText(allhelper.get(pos).getFname());
        viewHolder.name.append(" ");
        viewHolder.name.append(allhelper.get(pos).getLname());

        viewHolder.date.setText(allhelper.get(pos).getDate());
        viewHolder.time.setText(allhelper.get(pos).getTime());
        viewHolder.place.setText(allhelper.get(pos).getPlace());


        return view;
    }
}
