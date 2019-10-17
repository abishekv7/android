package com.example.abishekvenkatraman.hotel_booking;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    EditText username,password;
    Button login,signup;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=(EditText)findViewById(R.id.editText_login_user);
        password=(EditText)findViewById(R.id.editText_login_pass);
        login=(Button)findViewById(R.id.button_login_user);
        signup=(Button)findViewById(R.id.button_login_signup);
        db=openOrCreateDatabase("hoteldb", Context.MODE_PRIVATE,null);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Cursor c= db.rawQuery("SELECT * FROM user",null);
                String a=username.getText().toString();
                String b=password.getText().toString();
                while (c.moveToNext()){
                    if(c.getString(0).equals(a) && c.getString(1).equals(b)){
                       Intent i=new Intent(login.this,Booking.class);
                       i.putExtra("name",a);
                        startActivity(i);
                        Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(getApplicationContext(),"Please check ur username and password",Toast.LENGTH_SHORT).show();
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(login.this,MainActivity.class);
                startActivity(i);
            }
        });

    }
}
