package com.example.android.quakereport;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * Created by alanionita on 27/06/2018.
 */

public class Earthquake {
    private Double eMagnitude;
    private String eLocation;
    private long eTimeInMili;
    private String eUrl;

    Earthquake(Double magnitude, String location, long timeInMili, String url) {
        eMagnitude = magnitude;
        eLocation = location;
        eTimeInMili = timeInMili;
        eUrl = url;
    }

    public Double getMagnitude() {
        return eMagnitude;
    }

    public String getLocation() {
        return eLocation;
    }

    public long getTimeInMili() {
        return eTimeInMili;
    }

    public String getUrl() {
        return eUrl;
    }
}
