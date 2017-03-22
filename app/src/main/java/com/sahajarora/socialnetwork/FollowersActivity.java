package com.sahajarora.socialnetwork;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.io.Serializable;

public class FollowersActivity extends Activity {

    private ListView listFollowers;
    private static final long serialVersionUID = 6L;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        listFollowers = (ListView) findViewById(R.id.listView);

        UserItemAdapter adapter = new UserItemAdapter(this, UserItemClickListener.user.getFollowers());

        listFollowers.setAdapter(adapter);

        listFollowers.setOnItemClickListener(new UserItemClickListener());

    }
}
