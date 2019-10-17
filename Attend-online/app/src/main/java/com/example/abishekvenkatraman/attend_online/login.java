package com.example.abishekvenkatraman.attend_online;

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

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {
    EditText et_register_un,et_register_pass,et_ph_register,et_mail_register;
    Button bt_register_login;
    private final static String Reg_URl="http://abishekv178.000webhostapp.com/master_table.php";
    private final static String register_name="uname";
    private final static String register_pass="pass";
    private final static String register_ph="phoneno";
    private final static String register_mail="email";
    String uname,pass,phoneno,mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        et_register_pass=(EditText)findViewById(R.id.editTextPassword_register);
        et_register_un=(EditText)findViewById(R.id.editTextUsername_register);
        et_mail_register=(EditText)findViewById(R.id.et_email_register);
        et_ph_register=(EditText)findViewById(R.id.et_phoneno_register);
        bt_register_login=(Button)findViewById(R.id.button_register);
        bt_register_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registeruser();
                uname=et_register_un.getText().toString();
                pass=et_register_pass.getText().toString();
                phoneno=et_ph_register.getText().toString();
                mail=et_mail_register.getText().toString();
            }


        });
    }

    private void registeruser() {
        StringRequest stringreq = new StringRequest(Request.Method.POST, Reg_URl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("successfull"))
                {
                    Toast.makeText(getApplicationContext(),"successfully Registered",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(),"UnSuccessfull",Toast.LENGTH_SHORT).show();
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
                params.put(register_name, uname);
                params.put(register_pass, pass);
                params.put(register_ph,phoneno);
                params.put(register_mail,mail);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringreq);
    }

}
