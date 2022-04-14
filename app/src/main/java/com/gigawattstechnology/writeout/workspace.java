package com.gigawattstechnology.writeout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.EventListener;

public class workspace extends AppCompatActivity {
MediaPlayer player;
TabLayout tabLayout;
TabItem myarticle,othersarticle,myfavorite,myratings;
ViewPager viewPager;
TextView profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workspace);
        profile=findViewById(R.id.profile);
        tabLayout=findViewById(R.id.tablayout);
        myarticle=findViewById(R.id.myarticle);
        othersarticle=findViewById(R.id.othersarticle);
        myfavorite=findViewById(R.id.myfavorite);
       myratings=findViewById(R.id.myratings);
        viewPager=findViewById(R.id.vpager);
        tabLayout.setupWithViewPager(viewPager);
        //viewPager.setOffscreenPageLimit(4);
        PageAdapter pageAdapter=new PageAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT);
        pageAdapter.addFragment(new myarticletab(),"My Articles");
        pageAdapter.addFragment(new myfavoritetab(),"My Favorites");
        pageAdapter.addFragment(new othersarticletab(),"Other's Articles");
        pageAdapter.addFragment(new myratingstab(),"My Ratings");
        viewPager.setAdapter(pageAdapter);
        profile.setText(getIntent().getStringExtra("auth"));
        authtransfer.storename(profile.getText().toString());

    }
    public void writebutton(View view)
    {
        authtransfer.storename(profile.getText().toString());
        if(player==null){
            player=MediaPlayer.create(this,R.raw.writesound);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    stopPlayer();
                }
            });
        }
        player.start();
        Intent i=new Intent(this,writebutton.class);
        startActivity(i);
    }
    private void stopPlayer(){
        if(player!=null){
            player.release();
            player=null;
        }
    }
    @Override
    protected  void onStop(){
        super.onStop();
        stopPlayer();
    }
    /*public void pdfviewmyarticle(View view){
        String articletext=mytext.getText().toString().replace(" ","").replace("/","");
        authtransfer.storekey(articletext);
        Intent i=new Intent(workspace.this,pdfview.class);
        startActivity(i);
    }*/




    /*public void myarticle(View view)
    {
        if(player==null){
            player=MediaPlayer.create(this,R.raw.writesound);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    stopPlayer();
                }
            });
        }
        player.start();
        Intent i=new Intent(this,myarticle.class);
        startActivity(i);
    }
    public void othersarticle(View view)
    {
        if(player==null){
            player=MediaPlayer.create(this,R.raw.writesound);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    stopPlayer();
                }
            });
        }
        player.start();
        Intent i=new Intent(this,othersarticle.class);
        startActivity(i);
    }
    public void myfavorite(View view)
    {
        if(player==null){
            player=MediaPlayer.create(this,R.raw.writesound);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    stopPlayer();
                }
            });
        }
        player.start();
        Intent i=new Intent(this,myfavorite.class);
        startActivity(i);
    }
    public void myarticlerating(View view)
    {
        if(player==null){
            player=MediaPlayer.create(this,R.raw.writesound);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    stopPlayer();
                }
            });
        }
        player.start();
        Intent i=new Intent(this,myarticlerating.class);
        startActivity(i);
    }*/
}