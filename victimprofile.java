package com.example.user.projectandro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class victimprofile extends AppCompatActivity {

    private ImageView propic;
    private Button accept,decline;
    private DatabaseReference refdata;
    private TextView name,mail,phn,address,dob;
    private DatabaseReference databaseReference,dref2,dref3;
    private FirebaseAuth mauth;
    String Smail,fname,lname,sphn,sadd,sdob,umail,datakey;
    requestHelp rh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_victimprofile);
        mauth = FirebaseAuth.getInstance();
        refdata = FirebaseDatabase.getInstance().getReference();

        Intent intent = getIntent();
        Smail = intent.getExtras().getString("email");
        umail=intent.getExtras().getString("umail");

        databaseReference= FirebaseDatabase.getInstance().getReference("APP");
        dref2= FirebaseDatabase.getInstance().getReference("USERDET");
        dref3=FirebaseDatabase.getInstance().getReference("HELPREQ");

        accept=findViewById(R.id.accept);
        decline=findViewById(R.id.decline);
        propic=findViewById(R.id.propic);
        name=findViewById(R.id.name);
        mail=findViewById(R.id.mail);
        phn=findViewById(R.id.phn);
        address=findViewById(R.id.add);
        dob=findViewById(R.id.dob);


        getAlldataFromDB();
        furtherdata();
        //Toast.makeText(helperprofile.this,fname+" "+lname+" "+sphn+" ",Toast.LENGTH_SHORT).show();
        // Toast.makeText(helperprofile.this,sadd+" "+squa+" "+sact+" "+sper,Toast.LENGTH_SHORT).show();

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datakey=decodeUserEmail(Smail);
                try {
                    dref3.child(datakey).child("caseopen").setValue("1");
                    Toast.makeText(victimprofile.this,"Accepted request",Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });

        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datakey=decodeUserEmail(Smail);
                try {
                    dref3.child(datakey).child("caseopen").setValue("-1");
                    Toast.makeText(victimprofile.this,"Declined request",Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });



    }

    private void getAlldataFromDB() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data: dataSnapshot.getChildren()){
                    vicinfo value = data.getValue(vicinfo.class);

                    if(value.femail.matches(umail))
                    {
                        fname=value.fname;
                        lname=value.lname;
                        sphn=value.fphn;

                        name.setText(fname);
                        name.append(" ");
                        name.append(lname);
                        mail.setText(umail);
                        phn.setText(sphn);

                        break;
                    }}
            }  @Override public void onCancelled(DatabaseError error) {

            }    }); }

    private void furtherdata() {
        dref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data: dataSnapshot.getChildren()){
                    Userdef value = data.getValue(Userdef.class);

                    if(value.mail.matches(umail))
                    {
                        sadd=value.add;
                        sdob=value.dob;
                        dob.setText(sdob);
                        address.setText(sadd);
                        //Toast.makeText(helperprofile.this,sadd+" "+squa+" "+sact+" "+sper,Toast.LENGTH_SHORT).show();
                        break;
                    }}
            }  @Override public void onCancelled(DatabaseError error) {

            }    }); }


    static String encodeUserEmail(String userEmail) {
        return userEmail.replace(".", ",");
    }
    static String decodeUserEmail(String userEmail) {
        return userEmail.replace(".", ",");
    }
}
