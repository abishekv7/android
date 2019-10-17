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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText e1,e2,e3,e4;
    Button b1;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = (EditText) findViewById(R.id.editText_name);
        e2 = (EditText) findViewById(R.id.editText_pass);
        e3 = (EditText) findViewById(R.id.editText_mail);
        e4 = (EditText) findViewById(R.id.editText_ph);
        b1 = (Button) findViewById(R.id.button);
        db = openOrCreateDatabase("hoteldb", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS user(Name varchar,Password varchar,Email varchar,phoneno  varchar);");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.execSQL("INSERT INTO user VALUES('" + e1.getText() + "','" + e2.getText() + "','" + e3.getText() + "','" + e4.getText() + "');");
                Toast.makeText(getApplicationContext(), "added", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, login.class);
                startActivity(i);
            }
        });
    }
}
