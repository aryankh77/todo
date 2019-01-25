package com.example.home.todo_ap;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static com.example.home.todo_ap.MainActivity.ip;

public class MainActivity extends AppCompatActivity {
    private static int countToEnd = 4000;
    public static User user = readFromFile();
    AnimationDrawable animation;
    public static String ip = "192.168.230.1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

        ImageView loading = (ImageView) findViewById(R.id.imageView);
        animation = (AnimationDrawable) loading.getDrawable();
        animation.start();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent nextIntent;
                if (user != null) {
                    nextIntent = new Intent(MainActivity.this, Home.class);
                } else {
                    nextIntent = new Intent(MainActivity.this, registerAndlogin.class);

                }

                startActivity(nextIntent);
                finish();
            }
        }, countToEnd);
    }



    private static User readFromFile() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("user.ser"));
            return (User) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

}

