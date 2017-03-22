package com.sahajarora.socialnetwork;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sahajarora1286 on 2015-12-07.
 */
public class TagItemAdapter extends ArrayAdapter<Tag> {
    public TagItemAdapter(Context context, List<Tag> items){
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Tag tag = (Tag) getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.user_item_list, parent, false);
        }

        TextView tvTag = (TextView) convertView.findViewById(R.id.tvUser);

        tvTag.setText(tag.toString());

        return convertView;
    }

}
