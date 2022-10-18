package com.example.brcandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class UserApplications extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_applications);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);


        RecyclerAdapter adapter =new RecyclerAdapter(this);

        recyclerView.setAdapter(adapter);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}