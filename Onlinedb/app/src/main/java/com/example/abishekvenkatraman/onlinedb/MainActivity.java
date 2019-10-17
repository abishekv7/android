package com.example.abishekvenkatraman.onlinedb;

import android.support.v4.app.TaskStackBuilder;
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

public class MainActivity extends AppCompatActivity {
    EditText etname,etrollno;
    Button buttoninsert;
    String name,rollno;
    private final static String Reg_URl="http://abishekv178.000webhostapp.com/demo.php";
    private final static String Demo_name="Uname";
    private final static String Demo_rollno="Urollno";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttoninsert=(Button)findViewById(R.id.buttonInsert);
        etname=(EditText)findViewById(R.id.editTextname);
        etrollno=(EditText)findViewById(R.id.editTextrollno);

        buttoninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registeruser();
                //Toast.makeText(getApplicationContext(),"Added",Toast.LENGTH_SHORT).show();
                name=etname.getText().toString();
                rollno=etrollno.getText().toString();
                //etname.setText(" ");
                //etrollno.setText(" ");
            }


        });

    }

    private void registeruser() {
        StringRequest stringreq = new StringRequest(Request.Method.POST, Reg_URl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("successfull"))
                {
                    Toast.makeText(getApplicationContext(),"successfull",Toast.LENGTH_SHORT).show();
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
                params.put(Demo_name, name);
                params.put(Demo_rollno, rollno);
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringreq);
    }

}