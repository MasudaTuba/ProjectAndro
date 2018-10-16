package com.example.user.projectandro;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

public class findhelp extends AppCompatActivity {
    CardView lawyer,ngo,govt,con;
    ImageView imlawyer,imngo,imgovt,imcon;
    String usermail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findhelp);

        Intent intent = getIntent();
        usermail = intent.getExtras().getString("email");

        lawyer =findViewById(R.id.lawyer);
        imlawyer=findViewById(R.id.imlawyer);
        imlawyer.setImageResource(R.mipmap.lawyericon);

        ngo=findViewById(R.id.ngo);
        imngo=findViewById(R.id.imngo);
        imngo.setImageResource(R.drawable.ngo);

        govt=findViewById(R.id.govt);
        imgovt=findViewById(R.id.imgovt);
        imgovt.setImageResource(R.mipmap.gov);

        con=findViewById(R.id.consult);
        imcon=findViewById(R.id.imcon);
        imcon.setImageResource(R.drawable.consult);

        lawyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lawyerpage = new Intent(findhelp.this,askhelp.class);
                lawyerpage.putExtra("key", "LAWYER");
                lawyerpage.putExtra("umail", usermail);
                startActivity(lawyerpage);

            }
        });
        ngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lawyerpage = new Intent(findhelp.this,askhelp.class);
                lawyerpage.putExtra("key", "NGO");
                lawyerpage.putExtra("umail", usermail);
                startActivity(lawyerpage);

            }
        });

        govt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lawyerpage = new Intent(findhelp.this,askhelp.class);
                lawyerpage.putExtra("key", "GOVT. ORG.");
                lawyerpage.putExtra("umail", usermail);
                startActivity(lawyerpage);

            }
        });
        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lawyerpage = new Intent(findhelp.this,userschedule.class);
                //lawyerpage.putExtra("key", "GOVT. ORG.");
                lawyerpage.putExtra("umail", usermail);
                startActivity(lawyerpage);

            }
        });
    }
}
