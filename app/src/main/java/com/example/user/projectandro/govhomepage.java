package com.example.user.projectandro;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class govhomepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout govdraw;
    private ActionBarDrawerToggle govabt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_govhomepage);
        govdraw=(DrawerLayout)findViewById(R.id.govdraw);
        govabt=new ActionBarDrawerToggle(this,govdraw,R.string.open,R.string.close);
        govdraw.addDrawerListener(govabt);
        govabt.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navview=(NavigationView)findViewById(R.id.navView2);
        navview.setNavigationItemSelectedListener(this);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        if(govabt.onOptionsItemSelected(item)){
            //int id=item.getItemId();
            //if(id==R.id.appreq){
            // Intent myintent= new Intent(lawyerhomepage.this,lappreq.class);
            // startActivity(myintent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.appreq){
            Intent myintent= new Intent(this,lappreq.class);
            startActivity(myintent);
        }
        else if(id==R.id.approval){
            Intent myintent= new Intent(this,lapproval.class);
            startActivity(myintent);
        }
        else if(id==R.id.consult){
            Intent myintent= new Intent(this,lconsult.class);
            startActivity(myintent);
        }
        else if(id==R.id.set){
            Intent myintent= new Intent(this,lsettings.class);
            startActivity(myintent);
        }
        else if(id==R.id.logo){
            Intent myintent= new Intent(this,llogout.class);
            startActivity(myintent);
        }
        return false;
    }
}
