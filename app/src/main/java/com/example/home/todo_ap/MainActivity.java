package com.example.home.todo_ap;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    private static int countToEnd=4000;
    AnimationDrawable animation;
    private static Socket socket;
    private static String ip="192.168.232.1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView loading =(ImageView)findViewById(R.id.imageView);
        animation=(AnimationDrawable)loading.getDrawable();
        animation.start();
        /*Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                     socket=new Socket(ip,4040);
                }catch (Exception o){
                }
            }
        });*/
        new Handler().postDelayed(new Runnable()
        {
            public void run(){
                Intent homeIntent =new Intent(MainActivity.this,registerAndlogin.class);
                startActivity(homeIntent);
                finish();
            }
        },countToEnd);

    }
    }

