package com.example.abishekvenkatraman.attend_online;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Staff_login extends AppCompatActivity {
    EditText et_staff_un,et_staff_pass;
    Button bt_staff_login;
    private final static String Reg_URl="http://abishekv178.000webhostapp.com/staff_login.php";
    private final static String staff_login_name="email";
    private final static String staff_login_pass="pass";
    String mail,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_login);
        bt_staff_login=(Button)findViewById(R.id.bt_staff_login);
        et_staff_un=(EditText)findViewById(R.id.et_staff_name);
        et_staff_pass=(EditText)findViewById(R.id.et_staff_pass);
        bt_staff_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registeruser();
                mail=et_staff_un.getText().toString();
                pass=et_staff_pass.getText().toString();
            }
        });
    }

    private void registeruser() {
        StringRequest stringreq = new StringRequest(Request.Method.POST, Reg_URl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("successfull"))
                {
                    Intent i=new Intent(Staff_login.this,completed.class);
                    i.putExtra("mail",mail);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(),"Login Successfull",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(),"Login UnSuccessfull",Toast.LENGTH_SHORT).show();
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
                params.put(staff_login_name, mail);
                params.put(staff_login_pass, pass);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringreq);
    }

}

