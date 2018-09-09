package com.example.android.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TechNewsAdapter extends ArrayAdapter<TechNews> {


    public TechNewsAdapter(Context context, ArrayList<TechNews> techNewsList) {
        super(context, 0, techNewsList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_main, parent, false);
        }
        TechNews techNews = getItem(position);

        TextView titleTextView = (TextView) listItemView.findViewById(R.id.title);
        String title = techNews.getTitle();
        titleTextView.setText(title);

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        String date = techNews.getDate();
        dateTextView.setText(date);


        return listItemView;
    }
}
