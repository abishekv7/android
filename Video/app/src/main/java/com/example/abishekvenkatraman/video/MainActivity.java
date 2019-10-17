package com.example.abishekvenkatraman.video;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private File root;
    private ArrayList<File> fileList = new ArrayList<File>();
    ArrayAdapter<File> aa;
    private ListView ls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ls=(ListView)findViewById(R.id.view);
        //getting SDcard root path
        root = new File(Environment.getExternalStorageDirectory().getAbsolutePath());
        getfile(root);
        ArrayAdapter<File> aa=new ArrayAdapter<File>(this,android.R.layout.simple_list_item_1,fileList);
        ls.setAdapter(aa);

       ls.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
            {
               Intent i=new Intent(MainActivity.this,videoview.class);
                String a1=ls.getAdapter().getItem(position).toString();
                i.putExtra("path",a1);
                startActivity(i);
            }
        });

    }

    public ArrayList<File> getfile(File dir) {
        File listFile[] = dir.listFiles();
        if (listFile != null && listFile.length > 0) {
            for (int i = 0; i < listFile.length; i++) {

                if (listFile[i].isDirectory()) {
                    getfile(listFile[i]);

                } else {
                    if (listFile[i].getName().endsWith(".mp4") || listFile[i].getName().endsWith(".3gp"))
                    {
                        fileList.add(listFile[i]);
                       }
                }

            }
        }

        return fileList;
    }

}

