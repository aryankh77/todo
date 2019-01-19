package com.example.home.todo_ap;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class Home extends AppCompatActivity {
    FloatingActionButton fab;
    RecyclerView recyclerView;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = findViewById(R.id.recyclerViewTasks);
        fab = findViewById(R.id.fab);
        navigationView=findViewById(R.id.ngv);
        navigationView.setItemIconTintList(null);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(Home.this, Task.class);
                startActivity(homeIntent);
                finish();
            }
        });
    }
}