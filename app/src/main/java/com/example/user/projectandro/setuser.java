package com.example.user.projectandro;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class setuser extends AppCompatActivity {

    private Button reset,add;
    private EditText address,date;
    private Calendar mcal;
    private FirebaseAuth mauth;
    private EditText cha;
    private ProgressDialog dialog;
    String lawmail,addre,key;
    int day,month,year;
    private ArrayList<Userdef> users;
    private DatabaseReference databaseReference;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setuser);

        FirebaseAuth mauth=FirebaseAuth.getInstance();
        FirebaseUser user=mauth.getCurrentUser();
        users = new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference("USERDET");

        Intent intent = getIntent();
        lawmail = intent.getExtras().getString("email");
        addre = lawmail;
        key= decodeUserEmail(addre);
        date=findViewById(R.id.date);
        mcal=Calendar.getInstance();
        day=mcal.get(Calendar.DAY_OF_MONTH);
        month=mcal.get(Calendar.MONTH);
        year=mcal.get(Calendar.YEAR);
        reset=(Button)findViewById(R.id.idreset);
        add=findViewById(R.id.editadd);
        address=findViewById(R.id.add);
        cha=(EditText)findViewById(R.id.idchpass);
        month=month+1;
        // date.setText(day+"/"+month+"/"+year);

        dialog=new ProgressDialog(this);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog dp= new DatePickerDialog(setuser.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month=month+1;
                        date.setText(dayOfMonth+"/"+month+"/"+year);

                    }
                },year,month,day);
                dp.show();
            }
        });

        //  setAdd();


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!address.getText().toString().matches("")){
                    changeadd();
                }
                if(!date.getText().toString().matches("")){
                    changedate();
                }

            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth mauth=FirebaseAuth.getInstance();
                FirebaseUser user=mauth.getCurrentUser();
                if(user!=null){
                    dialog.setMessage("Please wait!Password is changing.......");
                    dialog.show();

                    user.updatePassword(cha.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                dialog.dismiss();
                                Toast.makeText(setuser.this,"Your password is changed.log in again!",Toast
                                        .LENGTH_SHORT).show();
                                finish();
                                FirebaseAuth.getInstance().signOut();
                                Intent tosecondagain=new Intent(setuser.this,second.class);
                                startActivity(tosecondagain);
                            }
                            else{
                                dialog.dismiss();
                                Toast.makeText(setuser.this,"Password can't be changed!",Toast.LENGTH_SHORT).show();
                            }
                        }

                    });
                }
                else{
                    dialog.dismiss();
                    Toast.makeText(setuser.this,"Password can't be changed!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    void change(){

        //FirebaseUser user=mauth.getCurrentUser();

    }

    private void changeadd() {

        String laddress=address.getText().toString();

        try {
            databaseReference.child(key).child("add").setValue(laddress);
            address.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    private void changedate() {

        String laddress=date.getText().toString();

        try {
            databaseReference.child(key).child("dob").setValue(laddress);
            date.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    void setAdd(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data: dataSnapshot.getChildren()){
                    Userdef value = data.getValue(Userdef.class);
                    users.add(value);
                    String mail=value.mail;

                    if(mail.equals(lawmail)) {
                        String addre = value.getAdd();
                        if (addre.matches("")) {

                        } else {
                            address.setText(addre);
                        }
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("iii", "Failed to read value.", error.toException());
            }
        });

    }

    static String decodeUserEmail(String userEmail) {
        return userEmail.replace(".", ",");
    }
}
