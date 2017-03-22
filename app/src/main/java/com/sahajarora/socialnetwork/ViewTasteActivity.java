package com.sahajarora.socialnetwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewTasteActivity extends AppCompatActivity {
    private ListView listTags;
    private String[] tags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_taste);

       listTags = (ListView) findViewById(R.id.listTags);

        TagItemAdapter adapter = new TagItemAdapter(this, UserItemClickListener.user.getTaste());

        listTags.setAdapter(adapter);

    }
}
