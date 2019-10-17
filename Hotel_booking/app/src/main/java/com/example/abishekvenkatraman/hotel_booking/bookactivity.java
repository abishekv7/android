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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class bookactivity extends AppCompatActivity {
    EditText e1,e2,e3,e4;
    Button book;
    TextView t1,t2,t3,t4;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookactivity);
        e1=(EditText)findViewById(R.id.editText_single);
        e2=(EditText)findViewById(R.id.editText_single_ac);
        e3=(EditText)findViewById(R.id.editText_doub);
        e4=(EditText)findViewById(R.id.editText_doub_ac);
        book=(Button)findViewById(R.id.button3);
       db=openOrCreateDatabase("hoteldb", Context.MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS booked_room_details(Single_bed int,Double_bed int,Single_bed_AC int,Double_bed_AC int);");
          book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.execSQL("DELETE FROM booked_room_details");
              db.execSQL("INSERT INTO booked_room_details VALUES('"+e1.getText()+"','"+e3.getText()+"','"+e2.getText()+"','"+e4.getText()+"');");
                Toast.makeText(getApplicationContext(),"rooms booked",Toast.LENGTH_SHORT).show();
                Cursor c=db.rawQuery("SELECT * FROM booked_room_details",null);
                StringBuffer buffer=new StringBuffer();
                while(c.moveToNext()){
                    buffer.append("SINGLE_BED: "+c.getString(0)+"\n");
                    buffer.append("DOUBLE_BED: "+c.getString(1)+"\n");
                    buffer.append("SINGLE_BED_AC: "+c.getString(2)+"\n");
                    buffer.append("DOUBLE_BED_AC: "+c.getString(3)+"\n");
                }
                showmessage("BOOKED DETAILS", buffer.toString());

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
