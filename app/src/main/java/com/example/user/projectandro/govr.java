package com.example.user.projectandro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class govr extends AppCompatActivity {
    private Button lawyers;
    private String femail,fname,fphn,type,sName,sLevel,sStdid,sCredit,sEmail,sPass;
    private DatabaseReference refDatabase;
    private FirebaseAuth mAuth;
    private StudentIn student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_govr);

        mAuth = FirebaseAuth.getInstance();
        refDatabase= FirebaseDatabase.getInstance().getReference();
        lawyers=(Button) findViewById(R.id.Govr);
        getAllInputData();
        createStudent();
        lawyers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toViewallActivity = new   Intent(govr.this, ViewAllActivity.class);
                toViewallActivity.putExtra("key","GOVT. ORG.");
                startActivity(toViewallActivity);
            }
        });
        //createAccountAndSaveInfo();
    }
    void getAllInputData(){
        fname = "aaaa";
        fphn = "2";
        femail = "aa2@gmail.com";
        type = "LAWYER" ;   }
    void createStudent(){

        student = new StudentIn(fname,fphn,femail,type);     }


    void createAccountAndSaveInfo()
    {

        StudentIn s2 = new StudentIn(fname, fphn, femail,type);
        DatabaseReference d1 = FirebaseDatabase.getInstance().getReference("APP");
        String s3 = d1.push().getKey();
        d1.child(s3).setValue(s2);
        ArrayList<ArrayList<String>> lawArrayList = new ArrayList<ArrayList<String>>();
        ArrayList<String> lawArrayList1 = new ArrayList<String>();
        lawArrayList1.add(fname);
        lawArrayList1.add(fphn);
        lawArrayList1.add(type);
        lawArrayList1.add(femail);
        lawArrayList.add(lawArrayList1);
        //  mauth = FirebaseAuth.getInstance();
        //prog.setMessage("please wait");
        // refdata = FirebaseDatabase.getInstance().getReference();
        //lawyeropen();

    }


}


