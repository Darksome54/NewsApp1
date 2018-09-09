package com.example.android.newsapp;



public class TechNews {
    private String mTitle;
    private String mDate;
    private String mUrl;

    public TechNews(String title, String date, String url) {
        mTitle = title;
        mDate = date;
        mUrl = url;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDate() {
        return mDate;
    }

    public String getUrl() {
        return mUrl;
    }
}
