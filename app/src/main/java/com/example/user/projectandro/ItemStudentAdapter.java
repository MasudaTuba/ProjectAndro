package com.example.user.projectandro;



import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class ItemStudentAdapter extends BaseAdapter{

    private Context activity;
    private ArrayList<StudentInfo> lawyer = new ArrayList<>();
    private LayoutInflater layoutInflater = null;

    public ItemStudentAdapter(Context activity, ArrayList<StudentInfo> lawyer) {
        this.activity = activity;
        this.lawyer = lawyer;
        this.layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private static class ViewHolder{
        private TextView fname,lname; }
    private ViewHolder viewHolder = null;

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
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
            view = layoutInflater.inflate(R.layout.list_layout,null);
            viewHolder.fname = view.findViewById(R.id.fname);
            viewHolder.lname = view.findViewById(R.id.lname);
            view.setTag(viewHolder);         }

            else {
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.fname.setText(lawyer.get(pos).getFname());
        viewHolder.lname.setText(lawyer.get(pos).getLname());

        return view;
    }
}
