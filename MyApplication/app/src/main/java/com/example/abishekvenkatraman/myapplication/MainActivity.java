package com.example.abishekvenkatraman.myapplication;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import java.io.File;
import java.io.InterruptedIOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Runnable {
    Button b1,b2,b3;
    SeekBar s;
    MediaPlayer mp,mp1,mp2;
    Thread soundthread;
    int i=0;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        s = (SeekBar) findViewById(R.id.seekBar4);
        iv=(ImageView)findViewById(R.id.imageView);
        mp = MediaPlayer.create(MainActivity.this, R.raw.a);
        soundthread =new Thread(this);
        soundthread.start();
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if(i==0) {
                    mp.seekTo(0);
                    mp.stop();
                    b1.setText("||");
                    mp = MediaPlayer.create(MainActivity.this, R.raw.b);
                    mp.start();
                    iv.setImageResource(R.drawable.b);
                    s.setMax(mp.getDuration());
                }
                else{
                    b1.setText("Play");
                }
                i=1;
            }
        });
        s.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                if (b) {
                    mp.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch (id){
            case R.id.button:
                if (mp.isPlaying()) {
                    mp.pause();
                    b1.setText("Play");
                } else {
                    mp.start();
                    b1.setText("||");
                }
                break;
            case R.id.button2:
                mp.stop();
                mp.seekTo(0);
                b1.setText("||");
                mp = MediaPlayer.create(MainActivity.this, R.raw.b);
                 mp.start();
                iv.setImageResource(R.drawable.b);
                s.setMax(mp.getDuration());
                i=1;
                break;
            case R.id.button3:
                mp.stop();
                b1.setText("||");
                mp.seekTo(0);
                iv.setImageResource(R.drawable.a);
                mp = MediaPlayer.create(MainActivity.this, R.raw.a);
                mp.start();
                i=1;
                s.setMax(mp.getDuration());
                break;
        }
    }

    @Override
    public void run() {
        int currentposition = 0;
        int soundtotal = mp.getDuration();
        s.setMax(soundtotal);
        while (mp != null && currentposition < soundtotal) {
            try {
                Thread.sleep(100);
                currentposition = mp.getCurrentPosition();
            } catch (InterruptedException soundexception) {
                return;
            }
            s.setProgress(currentposition);
        }
    }
}



