package com.example.user.projectandro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Viewall extends AppCompatActivity {

    private ListView studentListView;
    private ArrayList<StudentInfo> allStudent;
    private DatabaseReference databaseReference;
    private String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_viewall);

        Intent intent = getIntent();
        email = intent.getExtras().getString("email");

       //studentListView = findViewById(R.id.listAllStudent);
        allStudent = new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference("APP");
        getAlldataFromDB();
    }

    private void getAlldataFromDB() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data: dataSnapshot.getChildren()){
                    StudentInfo value = data.getValue(StudentInfo.class);
                    allStudent.add(value);
                    String mail=value.femail;

                    if(mail.equals(email)) {
                        String type = value.getType();
                        if(type.equals("LAWYER")){
                            String frstname=value.fname;
                            String lstname=value.lname;
                            Intent lawyerpage = new Intent(Viewall.this,lawyerhomepage.class);
                            lawyerpage.putExtra("fst", frstname);
                            lawyerpage.putExtra("lst", lstname);
                            lawyerpage.putExtra("email", email);
                            startActivity(lawyerpage);
                        }

                        else if(type.equals("USER")){
                            String frstname=value.fname;
                            String lstname=value.lname;
                            Intent lawyerpage = new Intent(Viewall.this,userprofile.class);
                            lawyerpage.putExtra("fst", frstname);
                            lawyerpage.putExtra("lst", lstname);
                            lawyerpage.putExtra("email", email);
                            startActivity(lawyerpage);
                        }

                        else if(type.equals("NGO")){
                            String frstname=value.fname;
                            String lstname=value.lname;
                            Intent lawyerpage = new Intent(Viewall.this,lawyerhomepage.class);
                            lawyerpage.putExtra("fst", frstname);
                            lawyerpage.putExtra("lst", lstname);
                            lawyerpage.putExtra("email", email);
                            startActivity(lawyerpage);
                        }
                        else if(type.equals("GOVT. ORG.")){
                            String frstname=value.fname;
                            String lstname=value.lname;
                            Intent lawyerpage = new Intent(Viewall.this,lawyerhomepage.class);
                            lawyerpage.putExtra("fst", frstname);
                            lawyerpage.putExtra("lst", lstname);
                            lawyerpage.putExtra("email", email);
                            startActivity(lawyerpage);
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
}

