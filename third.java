package com.example.user.projectandro;

import android.app.ProgressDialog;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class third extends AppCompatActivity  {
    private EditText mailadd,pas,fn,ln,phn;
    private RadioGroup rad;
    private RadioButton typebtn;
    private Button sub;
    private int flag=1;

    private ProgressDialog prog;
    private DatabaseReference refdata;
    private FirebaseAuth mauth;
    private String fname,lname,femail,fpass,fphn,type;
    private StudentInfo student;
    private int selectid,idsel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);


        mailadd = (EditText) findViewById(R.id.address);
        pas = (EditText) findViewById(R.id.past);
        fn = (EditText) findViewById(R.id.n1);
        ln = (EditText) findViewById(R.id.n2);
        phn = (EditText) findViewById(R.id.num);
        rad=(RadioGroup) findViewById(R.id.rad);
        sub=(Button) findViewById(R.id.sub);


        mauth = FirebaseAuth.getInstance();
        refdata = FirebaseDatabase.getInstance().getReference();
        prog = new ProgressDialog(this);
            sub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (mailadd.getText().toString().matches("") || pas.getText().toString().matches("") ||
                            fn.getText().toString().matches("") || ln.getText().toString().matches("")) {

                        Toast.makeText(third.this, "Please provide all the informations",
                                Toast.LENGTH_SHORT).show();

                    }else {
                        getAllInputData();
                        createStudent();
                        createAccount();
                        save();

                    }
                }
            });
    }

    void getAllInputData(){
        fname = fn.getText().toString();
        lname = ln.getText().toString();
        femail=mailadd.getText().toString();
        fpass=pas.getText().toString();
        fphn=phn.getText().toString();
        selectid=rad.getCheckedRadioButtonId();
        typebtn=(RadioButton)findViewById(selectid);
        type=typebtn.getText().toString();

    }
    void createStudent(){
        student = new StudentInfo(fname,lname,fphn,type,femail);
    }
   void createAccount() {
       if (!femail.isEmpty() && !fpass.isEmpty()) {
           prog.setMessage("Please wait!!");
           prog.show();
           mauth.createUserWithEmailAndPassword(femail, fpass)
                   .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful()) {
                               Log.d("third", "createUserWithEmail:success");
                               FirebaseUser user = mauth.getCurrentUser();
                               Toast.makeText(third.this, "Successful",
                                       Toast.LENGTH_SHORT).show();
                               finish();

                           } else {

                               if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                   Toast.makeText(getApplicationContext(), "You are already registered", Toast
                                           .LENGTH_SHORT).show();
                               }
                               else {
                                   Toast.makeText(third.this, "UnSuccessful! E-mail and Password feild can't be empty!",
                                           Toast.LENGTH_SHORT).show();
                               }
                           }
                            prog.dismiss();
                       }
                   });
       }
   }
void save(){
        if(type.equals("USER")){
            //Toast.makeText(third.this,"Type is "+type,Toast.LENGTH_SHORT).show();
            StudentInfo s = new StudentInfo(fname, lname, fphn, type,femail);
            Userdef def=new Userdef(femail,"","");
            DatabaseReference d1 = FirebaseDatabase.getInstance().getReference("APP");
            DatabaseReference d2 = FirebaseDatabase.getInstance().getReference("USERDET");
            String s1 = encodeUserEmail(femail);
            String s2= encodeUserEmail(femail);
            d1.child(s1).setValue(s);
            d2.child(s2).setValue(def);
            ArrayList<ArrayList<String>> mainArrayList1 = new ArrayList<ArrayList<String>>();
            ArrayList<String> subArrayList1 = new ArrayList<String>();
            ArrayList<ArrayList<String>> mainArrayList2 = new ArrayList<ArrayList<String>>();
            ArrayList<String> subArrayList2 = new ArrayList<String>();
            subArrayList1.add(fname);
            subArrayList1.add(lname);
            subArrayList1.add(fphn);
            subArrayList1.add(type);
            subArrayList1.add(femail);
            mainArrayList1.add(subArrayList1);
            subArrayList2.add(femail);
            subArrayList2.add("");
            subArrayList2.add("");
            mainArrayList2.add(subArrayList2);
            mauth = FirebaseAuth.getInstance();
            //prog.setMessage("please wait");
            refdata = FirebaseDatabase.getInstance().getReference();
            useropen();
        }
        else  if(type.equals("LAWYER"))

    {
        //Toast.makeText(third.this,"Type is "+type,Toast.LENGTH_SHORT).show();
        StudentInfo s2 = new StudentInfo(fname, lname, fphn, type,femail);
        Lawinfo def=new Lawinfo("","","","",femail);
        DatabaseReference d1 = FirebaseDatabase.getInstance().getReference("APP");
        DatabaseReference l = FirebaseDatabase.getInstance().getReference("LAWDET");
        String s3 =encodeUserEmail(femail);
        String st2=encodeUserEmail(femail);
        d1.child(s3).setValue(s2);
        l.child(st2).setValue(def);
        ArrayList<ArrayList<String>> lawArrayList = new ArrayList<ArrayList<String>>();
        ArrayList<String> lawArrayList1 = new ArrayList<String>();
        ArrayList<ArrayList<String>> mainArrayList2 = new ArrayList<ArrayList<String>>();
        ArrayList<String> subArrayList2 = new ArrayList<String>();
        lawArrayList1.add(fname);
        lawArrayList1.add(lname);
        lawArrayList1.add(fphn);
        lawArrayList1.add(type);
        lawArrayList1.add(femail);
        subArrayList2.add(femail);
        lawArrayList.add(lawArrayList1);
        subArrayList2.add(femail);
        subArrayList2.add("");
        subArrayList2.add("");
        subArrayList2.add("");
        subArrayList2.add("");
        mauth = FirebaseAuth.getInstance();
        //prog.setMessage("please wait");
        refdata = FirebaseDatabase.getInstance().getReference();
        lawyeropen();

    }

    else if(type.equals("NGO")){
        Toast.makeText(third.this,"Type is "+type,Toast.LENGTH_SHORT).show();
        StudentInfo ngo = new StudentInfo(fname, lname, fphn, type,femail);
        DatabaseReference dngo = FirebaseDatabase.getInstance().getReference("APP");
        String sngo = dngo.push().getKey();
        dngo.child(sngo).setValue(ngo);
        ArrayList<ArrayList<String>> ngoArrayList = new ArrayList<ArrayList<String>>();
        ArrayList<String> ngoArrayList1 = new ArrayList<String>();
        ngoArrayList1.add(fname);
        ngoArrayList1.add(lname);
        ngoArrayList1.add(fphn);
        ngoArrayList1.add(type);
        ngoArrayList1.add(femail);
        ngoArrayList.add(ngoArrayList1);
        mauth = FirebaseAuth.getInstance();
        //prog.setMessage("please wait");
        refdata = FirebaseDatabase.getInstance().getReference();
       // Intent ngopage = new Intent(third.this,NGO.class);
        ngoopen();


    }
    else if(type.equals("GOVT. ORG.")){
        Toast.makeText(third.this,"Type is "+type,Toast.LENGTH_SHORT).show();
        StudentInfo gov = new StudentInfo(fname, lname, fphn, type,femail);
        DatabaseReference dgov = FirebaseDatabase.getInstance().getReference("APP");
        String sgov = dgov.push().getKey();
        dgov.child(sgov).setValue(gov);
        ArrayList<ArrayList<String>> govArrayList = new ArrayList<ArrayList<String>>();
        ArrayList<String> govArrayList1 = new ArrayList<String>();
        govArrayList1.add(fname);
        govArrayList1.add(lname);
        govArrayList1.add(fphn);
        govArrayList1.add(type);
        govArrayList1.add(femail);
        govArrayList.add(govArrayList1);
        mauth = FirebaseAuth.getInstance();
        refdata = FirebaseDatabase.getInstance().getReference();
        govtopen();

    }
    else if(type.equals(""))
    {
        Toast.makeText(third.this,"Type is not checked.Please check the type"+type,Toast.LENGTH_SHORT).show();
    }

}

    static String encodeUserEmail(String userEmail) {
        return userEmail.replace(".", ",");
    }




   void lawyeropen(){
    Intent lawyerpage = new Intent(third.this,lawyerhomepage.class);
    lawyerpage.putExtra("fst", fname);
    lawyerpage.putExtra("lst", lname);
    lawyerpage.putExtra("email", femail);
    startActivity(lawyerpage);
     }

    void useropen(){
        Intent lawyerpage = new Intent(third.this,userprofile.class);
        lawyerpage.putExtra("fst", fname);
        lawyerpage.putExtra("lst", lname);
        lawyerpage.putExtra("email", femail);
        startActivity(lawyerpage);
    }

    void ngoopen(){
        Intent lawyerpage = new Intent(third.this,MainActivity.class);
        lawyerpage.putExtra("fst", fname);
        lawyerpage.putExtra("lst", lname);
        lawyerpage.putExtra("email", femail);
        startActivity(lawyerpage);
    }

    void govtopen(){
        Intent lawyerpage = new Intent(third.this,lawyerhomepage.class);
        lawyerpage.putExtra("fst", fname);
        lawyerpage.putExtra("lst", lname);
        lawyerpage.putExtra("email", femail);
        startActivity(lawyerpage);
    }
    }

