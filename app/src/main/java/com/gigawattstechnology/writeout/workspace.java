package com.gigawattstechnology.writeout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class workspace extends AppCompatActivity {
MediaPlayer player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workspace);
    }
    public void writebutton(View view)
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
    public void myarticle(View view)
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
    }
}