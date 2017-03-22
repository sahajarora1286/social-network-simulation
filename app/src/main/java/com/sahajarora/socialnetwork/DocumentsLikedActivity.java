package com.sahajarora.socialnetwork;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class DocumentsLikedActivity extends Activity {

    private ListView listDocumentsLiked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listDocumentsLiked = (ListView) findViewById(R.id.listView);

        DocumentItemAdapter adapter = new DocumentItemAdapter(this, UserItemClickListener.user.getLikedDocuments());

        listDocumentsLiked.setAdapter(adapter);

        listDocumentsLiked.setOnItemClickListener(new DocumentClickListener());
    }
}
