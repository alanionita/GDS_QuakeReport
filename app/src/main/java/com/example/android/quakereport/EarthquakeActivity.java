/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class EarthquakeActivity
        extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<ArrayList<Earthquake>> {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=5&limit=10";
    private EarthquakeListAdapter earthquakeListAdapter;
    private static final int EARTHQUAKE_LOADER_ID = 1;
    private TextView emptyTextView;
    private ProgressBar progressBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);
        assert earthquakeListView != null;

        // Set the empty TextView
        emptyTextView = (TextView) findViewById(R.id.empty_textView);
        earthquakeListView.setEmptyView(emptyTextView);

        // Find ProgressBar
        progressBar = (ProgressBar) findViewById(R.id.indeterminateBar);

        // Create a new {@link ArrayAdapter} of earthquakes
        earthquakeListAdapter = new EarthquakeListAdapter(this, new ArrayList<Earthquake>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(earthquakeListAdapter);

        // Start LoaderManager and initialise loader
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);
    }

    @Override
    public Loader<ArrayList<Earthquake>> onCreateLoader(int i, Bundle bundle) {
        return new EarthquakeLoader(this, USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Earthquake>> loader,
                               ArrayList<Earthquake> earthquakes) {
        emptyTextView.setText(R.string.no_earthquakes_found);
        progressBar.setVisibility(ProgressBar.GONE);

        earthquakeListAdapter.clear();

        if (earthquakes != null && !earthquakes.isEmpty()) {
            earthquakeListAdapter.addAll(earthquakes);
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<Earthquake>> loader) {
        earthquakeListAdapter.clear();
    }
}
