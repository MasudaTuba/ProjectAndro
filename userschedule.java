package com.example.user.projectandro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class userschedule extends AppCompatActivity {

    String lawmail,mail,pic,umail,key,time,place,date;
    private ListView vicsche;
    TimeScheAdapter timeadapter;
    private ArrayList<vitimTimeSche> allvic;
    private DatabaseReference databaseReference,dref2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lawyerschedule);

        Intent intent = getIntent();
        umail = intent.getExtras().getString("umail");
        vicsche = findViewById(R.id.listsche);
        allvic = new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference("SCHEDULE");
        dref2= FirebaseDatabase.getInstance().getReference("APP");
        getAlldataFromDB();

    }

    private void getAlldataFromDB() {
        //  Toast.makeText(victimreq.this,"get all data",Toast.LENGTH_LONG).show();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data: dataSnapshot.getChildren()){
                    timeSche value = data.getValue(timeSche.class);
                    // String type="USER";
                    // Toast.makeText(victimreq.this,"on data change",Toast.LENGTH_LONG).show();
                    if(value.umail.matches(umail))
                    {
                        // Toast.makeText(victimreq.this,"selecting users",Toast.LENGTH_LONG).show();
                        key=value.helpermail;
                        time=value.time;
                        place=value.place;
                        date=value.date;
                        getUser(key,date,time,place);
                    }}
            }  @Override public void onCancelled(DatabaseError error) {
                //Log.w(TAG, "Failed to read value.", error.toException());
            }    }); }

    private  void getUser(final String usermail, final String date, final String time, final String place){
        dref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data: dataSnapshot.getChildren()){
                    vicinfo value = data.getValue(vicinfo.class);
                    if(value.femail.matches(usermail)) {
                        vitimTimeSche temp = new vitimTimeSche(value.getFname(), value.getLname(), date,time,place);
                        allvic.add(temp);
                        timeadapter = new TimeScheAdapter(userschedule.this, allvic);
                        //Toast.makeText(userschedule.this,"found", Toast.LENGTH_LONG).show();
                        vicsche.setAdapter(timeadapter);
                    }

                }
            }  @Override public void onCancelled(DatabaseError error) {
                //Log.w(TAG, "Failed to read value.", error.toException());
            }    });

    }
}
