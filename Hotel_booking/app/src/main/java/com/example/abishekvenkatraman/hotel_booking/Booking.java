package com.example.abishekvenkatraman.hotel_booking;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.BatchUpdateException;

public class Booking extends AppCompatActivity {
    TextView welcome;
    Button show,book,cancel;
    String username;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        welcome=(TextView)findViewById(R.id.Welcome);
       show=(Button)findViewById(R.id.show_details);
        book=(Button)findViewById(R.id.button_book);
        cancel=(Button)findViewById(R.id.button2);
        username=getIntent().getExtras().getString("name");
        welcome.setText("WELCOME "+username);
        db=openOrCreateDatabase("hoteldb", Context.MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS room_details(Single_bed int,Double_bed int,Single_bed_AC int,Double_bed_AC int);");
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Cursor c=db.rawQuery("SELECT * FROM booked_room_details",null);
                StringBuffer buffer=new StringBuffer();
                while(c.moveToNext()){
                    buffer.append("SIGLE_BED: "+c.getString(0)+"\n");
                    buffer.append("DOUBLE_BED: "+c.getString(1)+"\n");
                    buffer.append("SINGLE_BED_AC: "+c.getString(2)+"\n");
                    buffer.append("DOUBLE_BED_AC: "+c.getString(3)+"\n");
                }

                showmessage("USER DETAILS",buffer.toString());

            }
        });
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent i= new Intent(getApplicationContext(),bookactivity.class);
                startActivity(i);

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.execSQL("DELETE FROM booked_room_details");
                Toast.makeText(getApplicationContext(),"Rooms cancelled",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showmessage(String s, String s1) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(s);
        builder.setMessage(s1);
        builder.show();
    }

}
