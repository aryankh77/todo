package com.example.home.todo_ap;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;

public class Home extends AppCompatActivity {
    FloatingActionButton fab;
    ListView listView;
    NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listView = findViewById(R.id.lv);
        fab = findViewById(R.id.fab);
        drawerLayout = findViewById(R.id.dl);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        navigationView = findViewById(R.id.ngv);
        navigationView.setItemIconTintList(null);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(Home.this, Task.class);
                startActivity(homeIntent);
            }
        });
        Request request = new Request("", "get tasks", MainActivity.user);
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.setL(new MyAsyncTask.Listener() {
            @Override
            public void onDataReceive(Object o) {
                ArrayList<TaskInfo> tasks= (ArrayList<TaskInfo>) ((Request)o).getSerializable();
                MainActivity.user.setTasks(tasks);
                System.out.println(tasks);
                listView.setAdapter(new MyAdapter(Home.this,tasks));
            }
            @Override
            public void onError(Exception e) {
            }
        });
        myAsyncTask.execute(request);





    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

