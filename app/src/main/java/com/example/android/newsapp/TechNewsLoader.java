package com.example.android.newsapp;
import android.content.Context;
import android.content.AsyncTaskLoader;

import java.util.List;


public class TechNewsLoader extends AsyncTaskLoader<List<TechNews>> {
    private String mUrl;

    public TechNewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }
    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<TechNews> loadInBackground() {
        if(mUrl == null) return null;

        List<TechNews> techNewsList = QueryUtils.fetchTechNewsData(mUrl);
        return techNewsList;
    }



}

