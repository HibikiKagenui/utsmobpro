package com.muhammadnabillah1603631.utsmobpro.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.muhammadnabillah1603631.utsmobpro.Adapter.MyGlide.GlideApp;
import com.muhammadnabillah1603631.utsmobpro.Model.Movie;
import com.muhammadnabillah1603631.utsmobpro.R;

public class MovieDetailActivity extends AppCompatActivity {
    public static String MOVIE_DATA = "movie_data";

    ImageView imageViewBackdrop, imageViewPoster;
    TextView textViewTitleYear, textViewOverview;
    Button buttonViewTheater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        imageViewBackdrop = findViewById(R.id.image_view_backdrop);
        imageViewPoster = findViewById(R.id.image_view_poster);
        textViewTitleYear = findViewById(R.id.text_view_title);
        textViewOverview = findViewById(R.id.text_view_overview);
        buttonViewTheater = findViewById(R.id.button_view_theater);

        final Movie m = getIntent().getParcelableExtra(MOVIE_DATA);

        getSupportActionBar().setTitle("MyMovie - " + m.getTitle());

        GlideApp.with(this)
                .load(MovieCardViewActivity.BASE_URL_IMG + m.getPosterPath())
                .centerCrop()
                .override(125, 200)
                .transition(new DrawableTransitionOptions().crossFade())
                .into(imageViewPoster);

        GlideApp.with(this)
                .load(MovieCardViewActivity.BASE_URL_IMG + m.getBackdropPath())
                .centerCrop()
                .transition(new DrawableTransitionOptions().crossFade())
                .into(imageViewBackdrop);

        textViewTitleYear.setText(m.getTitle());
        textViewOverview.setText(m.getOverview());

        buttonViewTheater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToTheaterSelection = new Intent(MovieDetailActivity.this, TheaterSelectionActivity.class);
                moveToTheaterSelection.putExtra(TheaterSelectionActivity.MOVIE_DATA, m);
                startActivity(moveToTheaterSelection);
            }
        });
    }
}
