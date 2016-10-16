package org.weitblicker.weitblickapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProjectAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Project> mDataSource;

    public ProjectAdapter(Context context, ArrayList<Project> items){
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position; // later return project id
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get view for row item
        View rowView = mInflater.inflate(R.layout.list_item_project, parent, false);

        // caption
        TextView captionTextView =
                (TextView) rowView.findViewById(org.weitblicker.weitblickapp.R.id.project_list_title); // plus possibly location -> 'Title, Location' as caption

        // abstract
        TextView abstractTextView =
                (TextView) rowView.findViewById(org.weitblicker.weitblickapp.R.id.project_list_abstract);

        // thumbnail
        ImageView thumbnailImageView =
                (ImageView) rowView.findViewById(org.weitblicker.weitblickapp.R.id.project_list_thumbnail);

        Project project = (Project) getItem(position);

        captionTextView.setText(project.getName());
        abstractTextView.setText(project.getAbstract());
        Picasso.with(mContext).load("https://weitblicker.org/sites/default/files/bundesverband.png").into(thumbnailImageView);
        //Picasso.with(mContext).load("C:/Users/Bene/Downloads/bundesverband.png").placeholder(R.mipmap.ic_launcher).into(thumbnailImageView);

        return rowView;
    }

}