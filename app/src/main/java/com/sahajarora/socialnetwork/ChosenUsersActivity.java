package com.sahajarora.socialnetwork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChosenUsersActivity extends AppCompatActivity  {
    private ListView listChosenUsers;

    private static final String USER_NAME = "Username";
    private static final String USER = "User";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen_users);

        listChosenUsers = (ListView) findViewById(R.id.listUsersChosen);

        UserItemAdapter adapter = new UserItemAdapter(getApplicationContext(), MainActivity.sn.getChosenUsers());

        listChosenUsers.setAdapter(adapter);

        listChosenUsers.setOnItemClickListener(new UserItemClickListener());

    }

    public void displayAllUsers(View view){
        startActivity(new Intent(this, AllUsersActivity.class));
    }
}
