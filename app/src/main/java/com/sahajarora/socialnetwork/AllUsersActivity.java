package com.sahajarora.socialnetwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class AllUsersActivity extends AppCompatActivity {
    private ListView listProducers, listConsumers;
    private ArrayList<User> producersList;
    private ArrayList<User> consumersList;

    private static final String PRODUCER = "Producer";
    private static final String CONSUMER = "Consumer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);

        listProducers = (ListView) findViewById(R.id.listProducers);
        listConsumers = (ListView) findViewById(R.id.listConsumers);
        consumersList = new ArrayList<>();
        producersList = new ArrayList<>();

        for (User user: MainActivity.sn.getUsers()){

            if (user.getType().equals(PRODUCER)){
                producersList.add(user);
            } else {
                consumersList.add(user);
            }
        }

        UserItemAdapter prodAdapter = new UserItemAdapter(this, producersList);
        UserItemAdapter consAdapter = new UserItemAdapter(this, consumersList);

        listProducers.setAdapter(prodAdapter);
        listConsumers.setAdapter(consAdapter);

        listProducers.setOnItemClickListener(new UserItemClickListener());
        listConsumers.setOnItemClickListener(new UserItemClickListener());
    }
}
