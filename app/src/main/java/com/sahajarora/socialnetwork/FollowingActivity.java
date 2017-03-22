package com.sahajarora.socialnetwork;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class FollowingActivity extends Activity {
    private ListView listFollowing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listFollowing = (ListView) findViewById(R.id.listView);

        UserItemAdapter adapter = new UserItemAdapter(this, UserItemClickListener.user.getFollowing());

        listFollowing.setAdapter(adapter);

        listFollowing.setOnItemClickListener(new UserItemClickListener());
    }
}
