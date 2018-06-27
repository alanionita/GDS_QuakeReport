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
    private String eDate;

    Earthquake(Double magnitude, String location, String date) {
        eMagnitude = magnitude;
        eLocation = location;
        eDate = date;
    }

    public Double getMagnitude () {
        return eMagnitude;
    }

    public String getLocation () {
        return eLocation;
    }

    public String getDate () {
        return eDate;
    }
}
