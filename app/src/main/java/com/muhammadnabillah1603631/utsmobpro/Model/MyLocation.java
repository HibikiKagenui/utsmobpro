package com.muhammadnabillah1603631.utsmobpro.Model;

import android.location.Location;

public class MyLocation extends Location {
    public MyLocation(double longitude, double latitude) {
        super("");
        setLongitude(longitude);
        setLatitude(latitude);
    }
}
