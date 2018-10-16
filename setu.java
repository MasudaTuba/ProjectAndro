package com.example.user.projectandro;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class setu extends AppCompatActivity {

    private Button log_out,reset;
    private FirebaseAuth mauth;
    private EditText cha;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setu);

        log_out=(Button)findViewById(R.id.idlog);
        reset=(Button)findViewById(R.id.idreset);
        FirebaseAuth mauth=FirebaseAuth.getInstance();
        FirebaseUser user=mauth.getCurrentUser();
        dialog=new ProgressDialog(this);

        cha=(EditText)findViewById(R.id.idchpass);

        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent tosecond=new Intent(setu.this,second.class);
                startActivity(tosecond);
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
                                Toast.makeText(setu.this,"Your password is changed.log in again!",Toast
                                        .LENGTH_SHORT).show();
                                finish();
                                FirebaseAuth.getInstance().signOut();
                                Intent tosecondagain=new Intent(setu.this,second.class);
                            }
                            else{
                                dialog.dismiss();
                                Toast.makeText(setu.this,"Password can't be changed!",Toast.LENGTH_SHORT).show();
                            }
                        }

                    });
                }
                else{
                    dialog.dismiss();
                    Toast.makeText(setu.this,"Password can't be changed!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    void change(){

        //FirebaseUser user=mauth.getCurrentUser();

    }
}
