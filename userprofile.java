package com.example.user.projectandro;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.*;

import com.google.firebase.auth.FirebaseAuth;

public class userprofile extends AppCompatActivity  {

    private static final int REQUEST_CALL = 1;
    TextView nameuser, emailuser;
    CardView law, help, near, settings,logout;
    String p,lawmail,frstname,lstname;
    FloatingActionButton call;
    ImageView propic,imlaw,imhelp,imnear,imset,imlogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

        Intent intent = getIntent();
        lawmail = intent.getExtras().getString("email");
        frstname= intent.getExtras().getString("fst");
        lstname=intent.getExtras().getString("lst");

        nameuser=(TextView) findViewById(R.id.uname) ;
        emailuser=(TextView) findViewById(R.id.umail);

        emailuser.setText(lawmail);
        nameuser.setText(frstname);
        nameuser.append(" ");
        nameuser.append(lstname);

        law = findViewById(R.id.law);
        imlaw=findViewById(R.id.imlaw);
        imlaw.setImageResource(R.drawable.law);
        help = findViewById(R.id.help);
        imhelp=findViewById(R.id.imhelp);
        imhelp.setImageResource(R.drawable.help);
        near= findViewById(R.id.near);
        imnear = findViewById(R.id.imnear);
        imnear.setImageResource(R.drawable.near);
        settings = findViewById(R.id.set);
        imset=findViewById(R.id.imset);
        imset.setImageResource(R.drawable.setting);
        logout=findViewById(R.id.logout);
        imlogout=findViewById(R.id.imlogout);
        imlogout.setImageResource(R.drawable.logout);
        call=findViewById(R.id.call);
        //call.setImageResource(R.drawable.callbtn);
        propic=findViewById(R.id.propic);
        propic.setImageResource(R.drawable.user);


        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });
        law.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchList.class);
                startActivity(intent);


            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), findhelp.class);
                intent.putExtra("email", lawmail);
                startActivity(intent);

            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lawyerpage = new Intent(userprofile.this,setuser.class);
                lawyerpage.putExtra("email", lawmail);
                startActivity(lawyerpage);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent tosecond=new Intent(userprofile.this,second.class);
                startActivity(tosecond);
            }
        });

        near.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void makePhoneCall () {

        String number = "123";
        if (ContextCompat.checkSelfPermission(userprofile.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(userprofile.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        } else {
            String dial = "tel:" + number;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }

    }
}
