package com.example.home.todo_ap;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Login extends AppCompatActivity {
    EditText username;
    EditText password;
    TextView textViewEror;
    TextView textViewEror2;
    String us;
    Button log;
    String pa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        textViewEror = findViewById(R.id.tve);
        textViewEror2 = findViewById(R.id.tve2);
        log = findViewById(R.id.btnlog);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                us = username.getText().toString();
                pa = password.getText().toString();
                Request request = new Request(us + " " + pa, "sign in", null);
                MyAsyncTask myAsyncTask = new MyAsyncTask();
                myAsyncTask.setL(new MyAsyncTask.Listener() {
                    @Override
                    public void onDataReceive(Object o) {
                        System.out.println();
                        if (((Request)o).getType().equals("not exist")) {
                            String message = ((Request)o).getMessage();
                            message = message.split("\\s")[0];
                            if (message.equals("email")) {
                                username.setError("username or email is not exist");
                                textViewEror.setText("username or email is not exist");
                            }
                            if (message.equals("password")) {
                                password.setError("password is wrong");
                                textViewEror2.setText("password is not exist");
                            }
                        } else {
                    User user = (User) ((Request)o).getSerializable();
                    MainActivity.user = user;
                            Intent intent = new Intent(Login.this, Home.class);
                            startActivity(intent);
                            finish();
                        }

                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
                myAsyncTask.execute(request);
            }
        });
    }
}
