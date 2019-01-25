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
import static com.example.home.todo_ap.MainActivity.objectOutputStream;

public class MainActivity extends AppCompatActivity {
    private static int countToEnd = 4000;
    public static User user = readFromFile();
    AnimationDrawable animation;
    public static ObjectOutputStream objectOutputStream;
    public static ObjectInputStream objectInputStream;
    public static Socket socket;
    //public static Serv serv=new Serv();
    public static String ip = "192.168.230.1";

    //"localhost";//"172.20.174.86";//"192.168.232.1"; //"192.168.230.1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //try {
            /*socket = new Socket(ip, 7878);
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());*/
            setContentView(R.layout.activity_main);
       // } catch (Exception o) {

        //}

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
class Serv extends AsyncTask<Request, Object, Object> {
@Override
protected Object doInBackground(Request... objects) {

    try {
        if (MainActivity.socket == null) {
            MainActivity.socket = new Socket(MainActivity.ip, 7878);
            MainActivity.objectInputStream = new ObjectInputStream(MainActivity.socket.getInputStream());
            MainActivity.objectOutputStream = new ObjectOutputStream(MainActivity.socket.getOutputStream());
        }
        send(objects[0]);
        System.err.println("*******");
        return receive();
    } catch (Exception o) {
        System.err.println("try again");
        o.printStackTrace();
        // TODO: 1/19/2019 safe try again ezafe shavad
        return o;
        //Intent intent=new Intent(MainActivity.this,MainActivity.class);
    }

}

    public void send(Request request) {
        try {
            MainActivity.objectOutputStream.writeObject(request);
            MainActivity.objectOutputStream.flush();
            System.err.println(request.getMessage() + " " + request.getType());
        } catch (IOException e) {
            // TODO: 1/19/2019 safe try again ezafe shavad
            send(request);
        }
    }

    public Request receive() {
        try {
            Request request = (Request) MainActivity.objectInputStream.readObject();
            System.err.println(request.getMessage() + " " + request.getType());
            return request;
        } catch (ClassNotFoundException | IOException e) {
            // TODO: 1/19/2019 safe try again ezafe shavad
            return null;
        }
    }
}