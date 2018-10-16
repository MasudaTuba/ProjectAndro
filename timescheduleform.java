package com.example.user.projectandro;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class timescheduleform extends AppCompatActivity {

    private Button schedule;
    private EditText date,time,location;
    private Calendar mcal;
    private FirebaseAuth mauth;
    private DatabaseReference refdata;
    private ProgressDialog dialog;
    String lawmail,umail,sdate,stime,splace;
    int day,month,year,hour,min;
    private ArrayList<Lawinfo> lawyers;
    private DatabaseReference databaseReference;
    timeSche ts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timescheduleform);

        mauth = FirebaseAuth.getInstance();
        refdata = FirebaseDatabase.getInstance().getReference();

        Intent intent = getIntent();
        lawmail = intent.getExtras().getString("email");
        umail=intent.getExtras().getString("umail");

        schedule=findViewById(R.id.schedulebtn);
        date=findViewById(R.id.meetingdate);
        time=findViewById(R.id.meetingtime);
        location=findViewById(R.id.meetingplace);
        mcal=Calendar.getInstance();
        day=mcal.get(Calendar.DAY_OF_MONTH);
        month=mcal.get(Calendar.MONTH);
        year=mcal.get(Calendar.YEAR);
        hour=mcal.get(Calendar.HOUR_OF_DAY);
        min=mcal.get(Calendar.MINUTE);

        month=month+1;
        // date.setText(day+"/"+month+"/"+year);

        dialog=new ProgressDialog(this);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog dp= new DatePickerDialog(timescheduleform.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month=month+1;
                        date.setText(dayOfMonth+"/"+month+"/"+year);
                        sdate=date.getText().toString();

                    }
                },year,month,day);
                dp.show();
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(timescheduleform.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,int minute) {

                                hour = hourOfDay;
                                min = minute;

                                time.setText(hourOfDay + ":" + minute);
                                stime=time.getText().toString();
                            }
                        }, hour, min, true);
                timePickerDialog.show();
            }

            }
        );





        //Toast.makeText(timescheduleform.this,sdate+" "+stime+" "+splace,Toast.LENGTH_SHORT).show();

        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                splace=location.getText().toString();
                Toast.makeText(timescheduleform.this,sdate+" "+stime+" "+splace,Toast.LENGTH_SHORT).show();
                //ts=new timeSche(umail,lawmail,sdate,stime,splace);
                sendSche();

            }
        });

    }

    void sendSche(){
        ts=new timeSche(umail,lawmail,sdate,stime,splace);
        DatabaseReference d1 = FirebaseDatabase.getInstance().getReference("SCHEDULE");
        String s1 = d1.push().getKey();
        d1.child(s1).setValue(ts);
        ArrayList<ArrayList<String>> mainArrayList1 = new ArrayList<ArrayList<String>>();
        ArrayList<String> subArrayList1 = new ArrayList<String>();
        subArrayList1.add(umail);
        subArrayList1.add(lawmail);
        subArrayList1.add(sdate);
        subArrayList1.add(stime);
        subArrayList1.add(splace);
        mainArrayList1.add(subArrayList1);
        mauth = FirebaseAuth.getInstance();
        //prog.setMessage("please wait");
        refdata = FirebaseDatabase.getInstance().getReference();
        Toast.makeText(timescheduleform.this,"Schedule Sent",Toast.LENGTH_SHORT).show();

    }
    static String encodeUserEmail(String userEmail) {
        return userEmail.replace(".", ",");
    }
}
