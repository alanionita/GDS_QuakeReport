package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.text.TextUtils;

import java.util.ArrayList;

public class EarthquakeLoader extends AsyncTaskLoader<ArrayList<Earthquake>> {
    /** Tag for log messages */
    private static final String LOG_TAG = EarthquakeLoader.class.getName();

    private String urlHolder;

    public EarthquakeLoader(Context context, String url) {
        super(context);
        urlHolder = url;
    }

    @Override
    public ArrayList<Earthquake> loadInBackground() {
        if( urlHolder == null ) {
            return null;
        }
        ArrayList<Earthquake> result = QueryUtils.fetchEarthquakeData(urlHolder);
        return result;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
