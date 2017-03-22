package com.sahajarora.socialnetwork;

import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import org.w3c.dom.Text;

public class UserProfileActivity extends TabActivity {
    private TabHost tabHost;
    private TextView tvUserName, tvUserType, tvPayoff;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        tvUserName = (TextView) findViewById(R.id.tvUserName);
        tvUserType = (TextView) findViewById(R.id.tvUserType);
        tvPayoff = (TextView) findViewById(R.id.tvPayoff);

        tabHost = getTabHost();

        tvUserName.setText(UserItemClickListener.user.toString());
        tvUserType.setText(UserItemClickListener.user.getType());
        tvPayoff.setText(String.valueOf(UserItemClickListener.user.getCurrentPayoff()));

        tabHost.addTab(tabHost.newTabSpec("followers").setIndicator("Followers (" + UserItemClickListener.user.getFollowers().size() + ")").setContent(new Intent(this, FollowersActivity.class)));
        tabHost.addTab(tabHost.newTabSpec("following").setIndicator("Following (" + UserItemClickListener.user.getFollowing().size() + ")").setContent(new Intent(this, FollowingActivity.class)));
        tabHost.addTab(tabHost.newTabSpec("documentsLiked").setIndicator("Documents Liked (" + UserItemClickListener.user.getLikedDocuments().size() + ")").setContent(new Intent(this, DocumentsLikedActivity.class)));

                tabHost.setCurrentTab(0);
    }

    public void displayTaste(View view){
        startActivity(new Intent(this, ViewTasteActivity.class));
    }
}
