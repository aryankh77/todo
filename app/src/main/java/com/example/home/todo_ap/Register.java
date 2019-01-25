package com.example.home.todo_ap;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Register extends AppCompatActivity {
    public EditText userName, email, password, name, familyName;
    private Button confirm;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    boolean u = false;
    boolean n = false;
    boolean e = false;
    boolean p = false;
    boolean eisc = false;
    String select;
    static ObjectInputStream objectInputStream;
    static ObjectOutputStream objectOutputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userName = findViewById(R.id.etUserName);
        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPassword);
        name = findViewById(R.id.etName);
        familyName = findViewById(R.id.etFamilyName);
        confirm = findViewById(R.id.btnregister);
        radioGroup = findViewById(R.id.rg);
        /*normal = findViewById(R.id.rbNormal);
        silver = findViewById(R.id.rbSilver);
        gold = findViewById(R.id.rbGold);*/
        confirm = findViewById(R.id.btnConfirm);
        /*if (normal.isChecked()) {
            select = normal.getText().toString();
        } else if (silver.isChecked()) {
            select = silver.getText().toString();
        } else if (gold.isChecked()) {
            select = gold.getText().toString();
        }*/
        userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            // the user's changes are saved here
            public void onTextChanged(CharSequence c, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0 || chekint(s) || chekword(s)) {
                    userName.setError("you must fill your username or you start with number or put bad word ");
                    u = false;
                    invisibel(confirm);
                } else {
                    //visibel(confirm);
                    u = true;
                }
                if (u == true && p == true && n == true && e == true) {
                    visibel(confirm);
                }


            }

        });


        password.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            // the user's changes are saved here
            public void onTextChanged(CharSequence c, int start, int before, int count) {
                if (c.length() < 8) {
                    password.setError("password must be more than 8 chracter");
                    invisibel(confirm);
                    p = false;
                } else {
                    p = true;
                }


            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0||!checkuln(s)) {
                    password.setError("you must fill your password or you donot use upper and lower word or you do not use num");
                    invisibel(confirm);
                    p = false;
                } else {
                    //visibel(confirm);
                    p = true;
                }
                if (u == true && p == true && n == true && e == true) {
                    visibel(confirm);
                }
            }

        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            // the user's changes are saved here
            public void onTextChanged(CharSequence c, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                eisc=s.toString().matches("^([a-zA-Z0-9_\\-.]+)@([a-zA-Z0-9_\\-.]+)\\.([a-zA-Z]{2,5})$");
                if (!eisc) {
                    email.setError("you must fill your email or you put wrong email ");
                    invisibel(confirm);
                    e = false;
                } else {
                    e = true;
                }

                if (u == true && p == true && n == true && e == true) {
                    visibel(confirm);
                }


            }

        });
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            // the user's changes are saved here
            public void onTextChanged(CharSequence c, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    name.setError("you must fill your name");
                    invisibel(confirm);
                    n = false;
                } else {
                    //visibel(confirm);
                    n = true;
                }
                if (u == true && p == true && n == true && e == true) {
                    visibel(confirm);
                }

            }

        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbutton(radioButton);
                select = radioButton.getText().toString();
                String un = userName.getText().toString();
                String pa = password.getText().toString();
                String em = email.getText().toString();
                String na = name.getText().toString();
                String fn = familyName.getText().toString();
                    Request request=new Request(un+" "+pa+" "+em+" "+na+" "+fn+" "+select,"register",null);
                MyAsyncTask myAsyncTask = new MyAsyncTask();
                myAsyncTask.setL(new MyAsyncTask.Listener() {
                    @Override
                    public void onDataReceive(Object o) {
                        if(((Request)o).getType().equals("exist")){
                            String message=((Request)o).getMessage();
                            message=message.split("\\s")[0];
                            if (message.equals("username")){
                                userName.setError("username exist");
                            }
                            if (message.equals("email")){
                                email.setError("email exist");
                            }
                        }else{
                            MainActivity.user= (User) ((Request)o).getSerializable();
                            Intent intent = new Intent(Register.this, Home.class);
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


    public  void checkbutton(View v) {
        int radioid = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioid);
    }

    public void visibel(View v) {

        confirm.setVisibility(View.VISIBLE);
    }

    public void invisibel(View v) {
        confirm.setVisibility(View.INVISIBLE);
    }

    public boolean chekint(Editable c) {
        if (c.charAt(0) == '0' || c.charAt(0) == '1' || c.charAt(0) == '2' || c.charAt(0) == '3' || c.charAt(0) == '4' || c.charAt(0) == '5' || c.charAt(0) == '6' || c.charAt(0) == '7' || c.charAt(0) == '8' || c.charAt(0) == '9') {
            return true;
        } else {
            return false;
        }

    }

    public boolean checkword(Editable c) {
        boolean f=false;
        for (int i = 0; i <c.length() ; i++) {
            if (c.charAt(0) == '/' || c.charAt(0) == '?' || c.charAt(0) == '.' || c.charAt(0) == '>' || c.charAt(0) == ',' || c.charAt(0) == '<' || c.charAt(0) == '`' || c.charAt(0) == '~' || c.charAt(0) == '!' || c.charAt(0) == '@'
                    || c.charAt(0) == '#' || c.charAt(0) == '$' || c.charAt(0) == '%' || c.charAt(0) == '^' || c.charAt(0) == '&' || c.charAt(0) == '*' || c.charAt(0) == '(' || c.charAt(0) == ')' || c.charAt(0) == '-' || c.charAt(0) == '_' || c.charAt(0) == '=' || c.charAt(0) == '+' || c.charAt(0) == '|' || c.charAt(0) == '"' || c.charAt(0) == ';' || c.charAt(0) == ':'
                    || c.charAt(0) == ']' || c.charAt(0) == '}' || c.charAt(0) == '[' || c.charAt(0) == '{') {
                return f;
            } else {
                return f;
            }
        }
        return f;


    }

    public boolean chekword(Editable c) {
        boolean f=false;
        for (int i = 0; i < c.length(); i++) {
            if (c.charAt(i) == '/' || c.charAt(i) == '?' || c.charAt(i) == '.' || c.charAt(i) == '>' || c.charAt(i) == ',' || c.charAt(i) == '<' || c.charAt(i) == '`' || c.charAt(i) == '~' || c.charAt(i) == '!' || c.charAt(i) == '@'
                    || c.charAt(i) == '#' || c.charAt(i) == '$' || c.charAt(i) == '%' || c.charAt(i) == '^' || c.charAt(i) == '&' || c.charAt(i) == '*' || c.charAt(i) == '(' || c.charAt(i) == ')' || c.charAt(i) == '-' || c.charAt(i) == '_' || c.charAt(i) == '=' || c.charAt(i) == '+' || c.charAt(i) == '|' || c.charAt(i) == '"' || c.charAt(i) == ';' || c.charAt(i) == ':'
                    || c.charAt(i) == ']' || c.charAt(i) == '}' || c.charAt(i) == '[' || c.charAt(i) == '{') {
                f =true;
            } else {
                f= false;
            }

        }
        return f;
    }

    public static boolean checkuln(Editable c) {
        boolean up = false;
        boolean up1 = false;
        boolean up2 = false;
        boolean up3 = false;
        for (int i = 0; i < c.length(); i++) {
            if (c.charAt(i) == '0' || c.charAt(i) == '1' || c.charAt(i) == '2' || c.charAt(i) == '3' || c.charAt(i) == '4' || c.charAt(i) == '5' ||
                    c.charAt(i) == '6' || c.charAt(i) == '7' || c.charAt(i) == '8' || c.charAt(i) == '9') {
                up = true;

            }
            else if (Character.isUpperCase(c.charAt(i))) {
                up1 = true;

            } else if (Character.isLowerCase(c.charAt(i))) {
                up2 = true;

            } else {
                up = false;
                up1 = false;
                up2 = false;
            }

        }
        if (up && up1 && up2) {
            up3 = true;
        }

        return up3;
    }

    }




