package com.example.home.todo_ap;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;

public class Task extends AppCompatActivity {
    TextView textViewc;//time
    TextView textViewd;//date
    EditText ettaskname;//task name
    EditText etc;//task comment
    Button buttonc;//btn set time
    Button buttond;//btn set date
    Context cContext=this;
    Context dContext=this;
    int day,month,year,minute,hour;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    FloatingActionButton send;
    String select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        textViewc=(TextView)findViewById(R.id.tvc);
        textViewd=(TextView)findViewById(R.id.tvd2);
        ettaskname=findViewById(R.id.ettaskname);
        etc=findViewById(R.id.etc);
        buttonc=(Button)findViewById(R.id.btnc);
        buttond=(Button)findViewById(R.id.btnd);
        radioGroup = findViewById(R.id.rgp);
        send = findViewById(R.id.fabsend);
        buttonc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                hour=c.get(Calendar.HOUR_OF_DAY);
                minute=c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog=new TimePickerDialog(cContext, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        textViewc.setText(hourOfDay +":"+ minute);
                    }

                },hour,minute,android.text.format.DateFormat.is24HourFormat(cContext));
                timePickerDialog.show();
            }
        });
        buttond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                year=c.get(Calendar.YEAR);
                month=c.get(Calendar.MONTH);
                day=c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog =new DatePickerDialog(dContext, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        textViewd.setText(year +"/"+month+"/"+dayOfMonth);
                    }
                },year,month,day);

                datePickerDialog.show();
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkbutton(radioButton);
                Priority priority=Priority.low;
                select = radioButton.getText().toString();
                switch (select){
                    case "High":
                        priority=Priority.high;
                        break;
                    case "Medium":
                        priority=Priority.medium;
                        break;
                    case "Low":
                         priority=Priority.low;
                         break;
                }
                TaskInfo taskInfo=new TaskInfo(ettaskname.getText().toString(),priority);
                taskInfo.setComment(etc.getText().toString());
                taskInfo.setTime(textViewc.getText().toString());
                taskInfo.setDate(textViewd.getText().toString());
                Serializable[] objects={taskInfo,MainActivity.user};
                Request request=new Request("","add task",objects);
                MyAsyncTask myAsyncTask = new MyAsyncTask();
                myAsyncTask.setL(new MyAsyncTask.Listener() {
                    @Override
                    public void onDataReceive(Object o) {
                        if(((Request)o).getType().equals("success")){
                        Intent homeIntent = new Intent(Task.this, Home.class);
                        startActivity(homeIntent);
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

}
