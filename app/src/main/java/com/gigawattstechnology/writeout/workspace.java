package com.gigawattstechnology.writeout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class workspace extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workspace);
    }
    public void writebutton(View view)
    {
        Intent i=new Intent(this,writebutton.class);
        startActivity(i);
    }
    public void myarticle(View view)
    {
        Intent i=new Intent(this,myarticle.class);
        startActivity(i);
    }
    public void othersarticle(View view)
    {
        Intent i=new Intent(this,othersarticle.class);
        startActivity(i);
    }
    public void myfavorite(View view)
    {
        Intent i=new Intent(this,myfavorite.class);
        startActivity(i);
    }
    public void myarticlerating(View view)
    {
        Intent i=new Intent(this,myarticlerating.class);
        startActivity(i);
    }
}