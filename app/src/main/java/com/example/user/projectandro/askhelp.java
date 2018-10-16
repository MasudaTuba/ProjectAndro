package com.example.user.projectandro;

import android.annotation.SuppressLint;
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

public class askhelp extends AppCompatActivity {
    private ListView helper;
    private ArrayList<userhelpershow> allhelper;
    private DatabaseReference databaseReference;
    userhelperAdapter userhelper;
    private Bundle bundle;
    String key,mail,pic,umail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_askhelp);

        Intent intent = getIntent();
        key = intent.getExtras().getString("key");
        umail=intent.getExtras().getString("umail");

        helper = findViewById(R.id.helper);
        allhelper = new ArrayList<>();

        databaseReference= FirebaseDatabase.getInstance().getReference("APP");
        getAlldataFromDB();
        helper.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                userhelpershow temp = (userhelpershow) parent.getItemAtPosition(position);
                Intent lawyerpage = new Intent(askhelp.this,helperprofile.class);
                mail=temp.mail;
                lawyerpage.putExtra("email",mail);
                lawyerpage.putExtra("umail",umail);
                startActivity(lawyerpage);
                //Toast.makeText(askhelp.this,temp.getMail(),Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void getAlldataFromDB() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data: dataSnapshot.getChildren()){
                    vicinfo value = data.getValue(vicinfo.class);
                    String type=key;

                    if(value.type.matches(type))
                    {
                        userhelpershow temp=new userhelpershow("",value.getFname(),value.getLname(),value.getFemail());
                        allhelper.add(temp);
                        userhelper = new userhelperAdapter(askhelp.this,allhelper);
                        helper.setAdapter(userhelper);


                    }}
            }  @Override public void onCancelled(DatabaseError error) {

            }    }); }
}