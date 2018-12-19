package com.muhammadnabillah1603631.utsmobpro.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.muhammadnabillah1603631.utsmobpro.adapter.TheaterSelectionAdapter;
import com.muhammadnabillah1603631.utsmobpro.model.Movie;
import com.muhammadnabillah1603631.utsmobpro.model.TheaterData;
import com.muhammadnabillah1603631.utsmobpro.R;

public class TheaterSelectionActivity extends AppCompatActivity {
    public static String MOVIE_DATA = "movie_data";

    RecyclerView recyclerViewTheaters;

    Movie m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theater_selection);

        m = getIntent().getParcelableExtra(MOVIE_DATA);
        getSupportActionBar().setTitle("View Theater " + m.getTitle());

        recyclerViewTheaters = findViewById(R.id.recycler_view_theaters);

        showListView();
    }

    private void showListView() {
        recyclerViewTheaters.setLayoutManager(new LinearLayoutManager(this));
        TheaterSelectionAdapter theaterSelectionAdapter = new TheaterSelectionAdapter(this);
        theaterSelectionAdapter.setTheaterList(TheaterData.theaterList);
        theaterSelectionAdapter.setMovie(m);
        recyclerViewTheaters.setAdapter(theaterSelectionAdapter);
    }

}
