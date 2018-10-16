package com.example.user.projectandro;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class lawyerhomepage extends AppCompatActivity {
    private CardView req,app,con,set,logout;
    private ImageView propic,imreq,imapp,imcon,imset,imlogout;
    private TextView name,mail;
    String lawmail,frstname,lstname;
    Boolean isImageFitToScreen;
    private String fullScreenInd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lawyerhomepage);

        Intent intent = getIntent();
        lawmail = intent.getExtras().getString("email");
        frstname= intent.getExtras().getString("fst");
        lstname=intent.getExtras().getString("lst");

        name=(TextView)findViewById(R.id.lname);
        mail=(TextView) findViewById(R.id.lmail);
        con=findViewById(R.id.con);
        set=findViewById(R.id.set);
        propic=(ImageView) findViewById(R.id.propic);
        propic.setImageResource(R.mipmap.lawyericon);
        imapp=(ImageView) findViewById(R.id.imapp) ;
        imapp.setImageResource(R.drawable.approval);
        imcon=(ImageView) findViewById(R.id.imcon) ;
        imcon.setImageResource(R.drawable.consult);
        imreq=(ImageView) findViewById(R.id.imreq) ;
        imreq.setImageResource(R.drawable.victimrequest);
        imset=(ImageView) findViewById(R.id.imset) ;
        imset.setImageResource(R.drawable.setting);
        imlogout=(ImageView) findViewById(R.id.imlogout) ;
        imlogout.setImageResource(R.mipmap.logout2);
        req= findViewById(R.id.req);
        logout= findViewById(R.id.logout);
        app=findViewById(R.id.app);


        mail.setText(lawmail);
        name.setText(frstname);
        name.append(" ");
        name.append(lstname);

        propic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lawyerpage = new Intent(lawyerhomepage.this,changepropic.class);
                lawyerpage.putExtra("resId",R.mipmap.lawyericon);
                startActivity(lawyerpage);
            }
        });

        req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lawyerpage = new Intent(lawyerhomepage.this,victimreq.class);
                lawyerpage.putExtra("email", lawmail);
                startActivity(lawyerpage);
            }
        });

        app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lawyerpage = new Intent(lawyerhomepage.this,lawyerapproval.class);
                lawyerpage.putExtra("email", lawmail);
                startActivity(lawyerpage);

            }
        });

        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lawyerpage = new Intent(lawyerhomepage.this,lawyerschedule.class);
                lawyerpage.putExtra("email", lawmail);
                startActivity(lawyerpage);

            }
        });


        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lawyerpage = new Intent(lawyerhomepage.this,setlaw.class);
                lawyerpage.putExtra("email", lawmail);
                startActivity(lawyerpage);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent tosecond=new Intent(lawyerhomepage.this,second.class);
                startActivity(tosecond);
            }
        });

    }


}
