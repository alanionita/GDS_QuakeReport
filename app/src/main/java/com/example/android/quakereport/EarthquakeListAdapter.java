package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by alanionita on 28/06/2018.
 */

public class EarthquakeListAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeListAdapter(@NonNull Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Get item from list
        final Earthquake currentEarthquake = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent,
                    false);
        }

        // Find view from list_item layout
        TextView magnitude = (TextView) convertView.findViewById(R.id.magnitude);
        TextView location = (TextView) convertView.findViewById(R.id.location);
        TextView date = (TextView) convertView.findViewById(R.id.date);

        if (currentEarthquake != null) {
            magnitude.setText(currentEarthquake.getMagnitude().toString());
            location.setText(currentEarthquake.getLocation());
            date.setText(currentEarthquake.getDate().toString());
        }

        return convertView;
    }
}
