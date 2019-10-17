package com.example.abishekvenkatraman.attend_online;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextClock;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class completed extends AppCompatActivity {
    private final static String Reg_URl="http://abishekv178.000webhostapp.com/user_data.php";
    private final static String staff_login_time="time";
    private final static String staff_login_mail="email";
    String time,mail;
    TextClock tc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed);
        tc=(TextClock)findViewById(R.id.textClock);
        time= tc.getText().toString();
        mail=getIntent().getExtras().getString("mail");
        Toast.makeText(getApplicationContext(),time,Toast.LENGTH_SHORT).show();
        registeruser();
    }
    private void registeruser() {
        StringRequest stringreq = new StringRequest(Request.Method.POST, Reg_URl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               /* if(response.equals("successfull"))
                {

                    Toast.makeText(getApplicationContext(),"Login completed",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(),"Login UnSuccessfull",Toast.LENGTH_SHORT).show();*/
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(staff_login_mail, mail);
               // params.put(staff_login_time, time);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringreq);
    }

}
