package com.example.user.projectandro;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class lawyerapproval extends AppCompatActivity {
    private ListView viclist;
    private ArrayList<userhelpershow> allvic;
    private DatabaseReference databaseReference,dref2;
    userhelperAdapter userhelper;
    private Bundle bundle;
    String lawmail,mail,pic,umail,key;
    AlertDialog.Builder myAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victimreq);
        viclist=findViewById(R.id.helper);
        myAlertDialog = new AlertDialog.Builder(lawyerapproval.this);
        Intent intent = getIntent();
        lawmail = intent.getExtras().getString("email");
        viclist = findViewById(R.id.listvic);
        allvic = new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference("HELPREQ");
        dref2= FirebaseDatabase.getInstance().getReference("APP");
        getAlldataFromDB();

        viclist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final userhelpershow temp = (userhelpershow) parent.getItemAtPosition(position);

                myAlertDialog.setTitle("SCHEDULE TIME");
                myAlertDialog.setMessage("Do you want to schedule meeting time with this client?");
                myAlertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent lawyerpage = new Intent(lawyerapproval.this,timescheduleform.class);
                        mail=temp.mail;
                        lawyerpage.putExtra("email",lawmail);
                        lawyerpage.putExtra("umail",mail);
                        startActivity(lawyerpage);

                    }});
                myAlertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                    }});
                myAlertDialog.show();

                //Toast.makeText(askhelp.this,temp.getMail(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getAlldataFromDB() {
        //  Toast.makeText(victimreq.this,"get all data",Toast.LENGTH_LONG).show();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data: dataSnapshot.getChildren()){
                    requestHelp value = data.getValue(requestHelp.class);
                    // String type="USER";
                    // Toast.makeText(victimreq.this,"on data change",Toast.LENGTH_LONG).show();
                    if(value.helper.matches(lawmail)&& value.caseopen.matches("1"))
                    {
                        // Toast.makeText(victimreq.this,"selecting users",Toast.LENGTH_LONG).show();
                        key=value.user;
                        getUser(key);
                    }}
            }  @Override public void onCancelled(DatabaseError error) {
                //Log.w(TAG, "Failed to read value.", error.toException());
            }    }); }

    private  void getUser(final String usermail){
        dref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data: dataSnapshot.getChildren()){
                    vicinfo value = data.getValue(vicinfo.class);
                    if(value.femail.matches(usermail)) {
                        userhelpershow temp = new userhelpershow("", value.getFname(), value.getLname(), value.getFemail());
                        allvic.add(temp);
                        userhelper = new userhelperAdapter(lawyerapproval.this, allvic);
                        viclist.setAdapter(userhelper);
                    }

                }
            }  @Override public void onCancelled(DatabaseError error) {
                //Log.w(TAG, "Failed to read value.", error.toException());
            }    });

    }
}

