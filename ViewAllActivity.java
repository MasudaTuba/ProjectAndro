package com.example.user.projectandro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewAllActivity extends AppCompatActivity {


    private ListView viclist;
    private ArrayList<vicinfo> allvic;
    private DatabaseReference databaseReference;
    private Bundle bundle;
    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victimreq);
        Intent intent = getIntent();
        key = intent.getExtras().getString("key");
        viclist = findViewById(R.id.listvic);
        allvic = new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference("APP");
        getAlldataFromDB();
    }

    private void getAlldataFromDB() {
        //  Toast.makeText(victimreq.this,"get all data",Toast.LENGTH_LONG).show();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data: dataSnapshot.getChildren()){
                    vicinfo value = data.getValue(vicinfo.class);
                    String lawyer="LAWYER";
                    String ngo="NGO";
                    String govt="GOVT. ORG.";
                    // Toast.makeText(victimreq.this,"on data change",Toast.LENGTH_LONG).show();
                    if(value.type.matches(lawyer))
                    {
                        // Toast.makeText(victimreq.this,"selecting users",Toast.LENGTH_LONG).show();
                        allvic.add(value);
                        vicreqadapter vicadapter = new vicreqadapter(ViewAllActivity.this, allvic);
                        viclist.setAdapter(vicadapter);
                    }
                    else if(value.type.matches(ngo))
                    {
                        // Toast.makeText(victimreq.this,"selecting users",Toast.LENGTH_LONG).show();
                        allvic.add(value);
                        vicreqadapter vicadapter = new vicreqadapter(ViewAllActivity.this, allvic);
                        viclist.setAdapter(vicadapter);
                    }
                    else if(value.type.matches(govt))
                    {
                        // Toast.makeText(victimreq.this,"selecting users",Toast.LENGTH_LONG).show();
                        allvic.add(value);
                        vicreqadapter vicadapter = new vicreqadapter(ViewAllActivity.this, allvic);
                        viclist.setAdapter(vicadapter);
                    }}
            }  @Override public void onCancelled(DatabaseError error) {
                //Log.w(TAG, "Failed to read value.", error.toException());
            }    }); }
}

