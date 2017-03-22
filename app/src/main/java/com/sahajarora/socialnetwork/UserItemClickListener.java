package com.sahajarora.socialnetwork;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.HashMap;

import static android.widget.Toast.makeText;

/**
 * Created by sahajarora1286 on 2015-12-07.
 */
public class UserItemClickListener implements android.widget.AdapterView.OnItemClickListener {

    public static User user;
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        user = (User) parent.getItemAtPosition(position);
        Intent i = new Intent(parent.getContext(), UserProfileActivity.class);
        parent.getContext().startActivity(i);

    }



}


