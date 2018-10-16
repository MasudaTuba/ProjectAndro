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

public class helperprofile extends AppCompatActivity {

    private ImageView propic;
    private Button request;
    private DatabaseReference refdata;
    private TextView name,mail,phn,address,qualification,active,experties;
    private DatabaseReference databaseReference,dref2;
    private FirebaseAuth mauth;
    String Smail,fname,lname,sphn,sadd,squa,sact,sper,umail;
    requestHelp rh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helperprofile);
        mauth = FirebaseAuth.getInstance();
        refdata = FirebaseDatabase.getInstance().getReference();

        Intent intent = getIntent();
        Smail = intent.getExtras().getString("email");
        umail=intent.getExtras().getString("umail");

        databaseReference= FirebaseDatabase.getInstance().getReference("APP");
        dref2= FirebaseDatabase.getInstance().getReference("LAWDET");

        propic=findViewById(R.id.propic);
        name=findViewById(R.id.name);
        mail=findViewById(R.id.mail);
        phn=findViewById(R.id.phn);
        address=findViewById(R.id.add);
        qualification=findViewById(R.id.qualification);
        active=findViewById(R.id.activesince);
        experties=findViewById(R.id.experties);
        request=findViewById(R.id.req);

        getAlldataFromDB();
        furtherdata();
        //Toast.makeText(helperprofile.this,fname+" "+lname+" "+sphn+" ",Toast.LENGTH_SHORT).show();
       // Toast.makeText(helperprofile.this,sadd+" "+squa+" "+sact+" "+sper,Toast.LENGTH_SHORT).show();

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rh=new requestHelp(Smail,umail,"2","0");
                sendReq();

            }
        });



    }

    private void getAlldataFromDB() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data: dataSnapshot.getChildren()){
                    vicinfo value = data.getValue(vicinfo.class);

                    if(value.femail.matches(Smail))
                    {
                        fname=value.fname;
                        lname=value.lname;
                        sphn=value.fphn;
                        name.setText(fname);
                        name.append(" ");
                        name.append(lname);
                        mail.setText(Smail);
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
                    Lawinfo value = data.getValue(Lawinfo.class);

                    if(value.mail.matches(Smail))
                    {
                        sadd=value.ofadd;
                        squa=value.qual;
                        sact=value.active;
                        sper=value.exp;
                        address.setText(sadd);
                        qualification.setText(squa);
                        active.setText(sact);
                        experties.setText(sper);
                        //Toast.makeText(helperprofile.this,sadd+" "+squa+" "+sact+" "+sper,Toast.LENGTH_SHORT).show();
                        break;
                    }}
            }  @Override public void onCancelled(DatabaseError error) {

            }    }); }

            void sendReq(){
                requestHelp rhelp = new requestHelp(Smail,umail,"2","0");
                DatabaseReference d1 = FirebaseDatabase.getInstance().getReference("HELPREQ");
                String s1 = encodeUserEmail(Smail);
                d1.child(s1).setValue(rhelp);
                ArrayList<ArrayList<String>> mainArrayList1 = new ArrayList<ArrayList<String>>();
                ArrayList<String> subArrayList1 = new ArrayList<String>();
                subArrayList1.add(Smail);
                subArrayList1.add(umail);
                subArrayList1.add("2");
                mainArrayList1.add(subArrayList1);
                mauth = FirebaseAuth.getInstance();
                //prog.setMessage("please wait");
                refdata = FirebaseDatabase.getInstance().getReference();
                Toast.makeText(helperprofile.this,"Request Sent",Toast.LENGTH_SHORT).show();


            }
    static String encodeUserEmail(String userEmail) {
        return userEmail.replace(".", ",");
    }
}
