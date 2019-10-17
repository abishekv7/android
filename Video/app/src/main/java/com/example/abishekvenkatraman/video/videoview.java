package com.example.abishekvenkatraman.video;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class videoview extends AppCompatActivity {
    VideoView v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoview);
        v=(VideoView)findViewById(R.id.videoView);
        MediaController mediaController=new MediaController(this);
        mediaController.setAnchorView(v);
        v.setMediaController(mediaController);
        String path=getIntent().getExtras().getString("path");
        v.setVideoPath(path);
        v.start();

    }
}
