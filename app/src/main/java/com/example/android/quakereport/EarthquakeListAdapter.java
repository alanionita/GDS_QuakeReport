package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by alanionita on 28/06/2018.
 */

public class EarthquakeListAdapter extends ArrayAdapter<Earthquake> {
    private static final String LOCATION_SEPARATOR = " of ";

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
        TextView locationOffset = (TextView) convertView.findViewById(R.id.location_offset);
        TextView date = (TextView) convertView.findViewById(R.id.date);
        TextView time = (TextView) convertView.findViewById(R.id.time);

        if (currentEarthquake != null) {
            DecimalFormat formatter = new DecimalFormat("0.00");
            String magnitudeHolder = formatter.format(currentEarthquake.getMagnitude());
            magnitude.setText(magnitudeHolder);

            // Parse the location and pass it to the different views
            String rawLocation = currentEarthquake.getLocation();
            if (rawLocation.contains(",")) {

                String[] separateLocationStrings = rawLocation.split(LOCATION_SEPARATOR);
                locationOffset.setText(separateLocationStrings[0]);
                location.setText(separateLocationStrings[1].trim());

            } else {
                locationOffset.setText(getContext().getString(R.string.near_the));
                location.setText(rawLocation);
            }


            // Parse the date and time and pass them to relevant views
            Date dateObject = new Date(currentEarthquake.getTimeInMili());

            String formatedDate = formatDate(dateObject);
            String formatedTime = formatTime(dateObject);

            date.setText(formatedDate);
            time.setText(formatedTime);
        }

        return convertView;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd LLL, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm a");
        return timeFormat.format(dateObject);
    }
}
