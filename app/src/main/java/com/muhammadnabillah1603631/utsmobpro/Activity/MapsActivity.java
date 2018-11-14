package com.muhammadnabillah1603631.utsmobpro.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.muhammadnabillah1603631.utsmobpro.Model.Movie;
import com.muhammadnabillah1603631.utsmobpro.Model.Theater;
import com.muhammadnabillah1603631.utsmobpro.R;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    public static final String THEATER_DATA = "theater_data";
    public static final String MOVIE_DATA = "movie_data";
    Theater t;
    Movie m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        t = getIntent().getParcelableExtra(THEATER_DATA);
        m = getIntent().getParcelableExtra(MOVIE_DATA);

        getSupportActionBar().setTitle(m.getTitle() + " in " + t.getName());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker and move the camera
        LatLng theaterLocation = t.getLocation();
        googleMap.addMarker(new MarkerOptions().position(theaterLocation).title(t.getName()));
        googleMap.setMinZoomPreference(15);
        googleMap.setMaxZoomPreference(19);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(theaterLocation));
        googleMap.setInfoWindowAdapter(new MyInfoWindow(this, m));
    }
}
