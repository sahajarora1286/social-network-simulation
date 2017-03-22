package com.sahajarora.socialnetwork;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by sahajarora1286 on 2015-12-07.
 */
public class DocumentClickListener implements android.widget.AdapterView.OnItemClickListener {
    public static Document doc;
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        doc = (Document) parent.getItemAtPosition(position);
        Intent i = new Intent(parent.getContext(), DocumentProfileActivity.class);
        parent.getContext().startActivity(i);
    }
}
