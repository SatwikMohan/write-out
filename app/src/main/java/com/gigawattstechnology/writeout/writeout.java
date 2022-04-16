package com.gigawattstechnology.writeout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

public class writeout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writeout);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    public void signup(View view)
    {
        Intent i=new Intent(this,signup.class);
        startActivity(i);
    }
    public void login(View view)
    {
        Intent i=new Intent(this,login.class);
        startActivity(i);
    }
}