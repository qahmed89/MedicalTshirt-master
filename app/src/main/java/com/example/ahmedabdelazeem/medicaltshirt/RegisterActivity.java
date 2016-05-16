package com.example.ahmedabdelazeem.medicaltshirt;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Log.i("Ahmed", "onCreate: done");
        final EditText etName = (EditText) findViewById(R.id.etEmail);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final EditText etPassword =(EditText) findViewById(R.id.etPassword);
        final Button bRegister = (Button)findViewById(R.id.bRegister);
        final Spinner spDoctors = (Spinner) findViewById(R.id.spDoctors);
        Response.Listener<String> responseListner = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.i("Ahmed", "docotrslist: 1");
                    JSONObject jsonResponse = new JSONObject(response);
                    Log.i("Ahmed", "docotrslist: 2");
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        Log.i("Ahmed", "docotrslist: 3");
                        // get doctors list
                        int doctorsNum = jsonResponse.getInt("num");
                        ArrayList<String> doctorsList=new ArrayList<String>();
                        for ( int i = 0 ; i< doctorsNum ; i++ ){
                            JSONObject DoctorData = new JSONObject();
                            DoctorData = jsonResponse.getJSONObject(i+"");
                            String doctorName =DoctorData.getString("name");
                            doctorsList.add(doctorName);
                            Log.i("incomin","doctroName :"+doctorName);
                            //   spDoctors.List
                        }



                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,doctorsList);
                        spDoctors.setAdapter(adapter);
                     //   spDoctors.setSelection(1);

                    } else {
                        //no docttors
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        RequestDoctorList requestDoctorList = new RequestDoctorList(responseListner);
        RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);

        queue.add(requestDoctorList);

        Log.i("Ahmed", "onCreate: 2");
        if (bRegister != null) {
            bRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.i("Ahmed", "onCreate: 21");

                    final String name = etName.getText().toString();
                    final String email = etEmail.getText().toString();
                    final String password = etPassword.getText().toString();
                    Log.i("Ahmed", "onCreate: 22");
                    Response.Listener<String> responseListner = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                Log.i("Ahmed", "onCreate: 23");
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    RegisterActivity.this.startActivity(intent);
                                } else {
                                    Log.i("Ahmed", "onCreate: 24");
                                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                    builder.setMessage("Register Failed")
                                            .setNegativeButton("Retry", null)
                                            .create()
                                            .show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    Log.i("Ahmed", "onCreate: 3");
                    RegisterRequest registerRequest = new RegisterRequest(name, email, password, responseListner);
                    Log.i("Ahmed", "onCreate: 4");
                    RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);

                    queue.add(registerRequest);

                }
            });
        }}}