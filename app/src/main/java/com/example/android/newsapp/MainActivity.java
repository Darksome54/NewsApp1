package com.example.android.newsapp;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<TechNews>> {
    private TechNewsAdapter mAdapter;
    private TextView mEmptyTextView;

    private static final String TECH_REQUEST_URL = "https://content.guardianapis.com/technology?api-key=6a3a10ba-6bf4-4058-a294-59ec80750e38";
    private static final int TECH_NEWS_LOADER_ID = 1;

    /** TextView that is displayed when the list is empty */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_items);
        ListView techNewsListView = findViewById(R.id.list);

        mEmptyTextView = findViewById(R.id.empty_view);
        techNewsListView.setEmptyView(mEmptyTextView);
        mAdapter = new TechNewsAdapter(this, new ArrayList<TechNews>());

        techNewsListView.setAdapter(mAdapter);
        techNewsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                TechNews techNews = mAdapter.getItem(position);
                Uri techNewsUri = Uri.parse(techNews.getUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, techNewsUri);
                startActivity(websiteIntent);
            }
        });

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(TECH_NEWS_LOADER_ID, null, this);
        } else {
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyTextView.setText("No Internet Connection");
        }
    }


    @Override
    public Loader<List<TechNews>> onCreateLoader(int i, Bundle bundle) {
        return new TechNewsLoader(this, TECH_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<TechNews>> loader, List<TechNews> technews) {
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);
        mEmptyTextView.setText("No news found");
        mAdapter.clear();

        if (technews != null && !technews.isEmpty()) {
            mAdapter.addAll();
        }
    }

    @Override
    public void onLoaderReset(Loader<List<TechNews>> loader) {
        mAdapter.clear();
    }
}
