package com.sahajarora.socialnetwork;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DocumentProfileActivity extends AppCompatActivity {
    private TextView tvDocumentTag, tvDocumentProducer, tvLikers;
    private ListView listLikers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_profile);
        Document doc = DocumentClickListener.doc;
        tvDocumentTag = (TextView) findViewById(R.id.tvDocumentTag);
        tvDocumentProducer = (TextView) findViewById(R.id.tvDocumentProducer);
        tvLikers = (TextView) findViewById(R.id.tvLikers);

        tvDocumentTag.setText("Tag: " + doc.getTag());
        tvDocumentProducer.setText("Producer: " + doc.getProducer().toString());
        tvLikers.setText("Likers (" + doc.getLikingUsers().size() + ")");
        listLikers = (ListView) findViewById(R.id.listLikers);

        UserItemAdapter adapter = new UserItemAdapter(this, doc.getLikingUsers());

        listLikers.setAdapter(adapter);

        listLikers.setOnItemClickListener(new UserItemClickListener());


    }
}
