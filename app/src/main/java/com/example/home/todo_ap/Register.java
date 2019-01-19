package com.example.home.todo_ap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Register extends AppCompatActivity {
    public EditText userName, email, password, name, familyName;
    private Button confirm;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    boolean u=false;
    boolean n=false;
    boolean e=false;
    boolean p=false;
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
                if(s.length()==0||chekint(s)) {
                    userName.setError("you must fill your username or you start with number");
                    u=false;
                    invisibel(confirm);
                }else {
                    //visibel(confirm);
                    u=true;
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
                    p=false;
                }else {
                    p=true;
                }



            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==0){
                    password.setError("you must fill your password");
                    invisibel(confirm);
                    p=false;
                }else {
                    //visibel(confirm);
                    p=true;
                }
                if(u==true&&p==true&&n==true&&e==true){
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
                boolean is=false;
                for (int i = 0; i <c.length() ; i++) {
                    if (c.toString().charAt(i)== '@') {
                        if(c.toString().endsWith(".com"))
                        {
                            is = true;
                            e=true;
                            break;
                        }
                    }
                }
                if(is==false){
                    email.setError("email notvalid it must have @ and .com");
                    invisibel(confirm);
                    e=false;
                }



            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==0||checkword(s)) {
                    email.setError("you must fill your email or you put notvalid word ");
                    invisibel(confirm);
                    e=false;
                }
                else {
                    //visibel(confirm);
                    e=true;
                }
                /*if(checkword(s)){
                    email.setError("email start with nonvalid word");
                    invisibel(confirm);
                }*/

                if(u==true&&p==true&&n==true&&e==true){
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
                if(s.length()==0) {
                    name.setError("you must fill your name");
                    invisibel(confirm);
                    n=false;
                }else {
                    //visibel(confirm);
                    n=true;
                }
                if(u==true&&p==true&&n==true&&e==true){
                    visibel(confirm);
                }

            }

        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbutton(radioButton);
                select=radioButton.getText().toString();
                String un=userName.getText().toString();
                String pa=password.getText().toString();
                String em=email.getText().toString();
                String na=name.getText().toString();
                String fn=familyName.getText().toString();
                /*try {
                    objectOutputStream.writeUTF("register");
                    objectOutputStream.writeUTF(un);
                    objectOutputStream.writeUTF(pa);
                    objectOutputStream.writeUTF(na);
                    objectOutputStream.writeUTF(fn);
                    objectOutputStream.writeUTF(em);
                    objectOutputStream.writeUTF(select);
                }catch (Exception io){
                }*/

                Intent intent =new Intent(Register.this,Home.class);
                startActivity(intent);
                finish();

            }
        });


    }






    public void checkbutton(View v) {
        int radioid = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioid);
    }

    public void visibel(View v) {

        confirm.setVisibility(View.VISIBLE);
    }
    public void invisibel(View v){
        confirm.setVisibility(View.INVISIBLE);
    }
    public boolean chekint(Editable c){
        if(c.charAt(0)=='0'||c.charAt(0)=='1'||c.charAt(0)=='2'||c.charAt(0)=='3'||c.charAt(0)=='4'||c.charAt(0)=='5'||c.charAt(0)=='6'||c.charAt(0)=='7'||c.charAt(0)=='8'||c.charAt(0)=='9'){
            return true;
        }else {
            return false;
        }

    }
    public boolean checkword(Editable c){
        if(c.charAt(0)=='/'||c.charAt(0)=='?'||c.charAt(0)=='.'||c.charAt(0)=='>'||c.charAt(0)==','||c.charAt(0)=='<'||c.charAt(0)=='`'||c.charAt(0)=='~'||c.charAt(0)=='!'||c.charAt(0)=='@'
                ||c.charAt(0)=='#'||c.charAt(0)=='$'||c.charAt(0)=='%'||c.charAt(0)=='^'||c.charAt(0)=='&'||c.charAt(0)=='*'||c.charAt(0)=='('||c.charAt(0)==')'||c.charAt(0)=='-'||c.charAt(0)=='_'||c.charAt(0)=='='||c.charAt(0)=='+'||c.charAt(0)=='|'||c.charAt(0)=='"'||c.charAt(0)==';'||c.charAt(0)==':'
                ||c.charAt(0)==']'||c.charAt(0)=='}'||c.charAt(0)=='['||c.charAt(0)=='{'){
            return true;
        }else {
            return false;
        }

    }
    }

