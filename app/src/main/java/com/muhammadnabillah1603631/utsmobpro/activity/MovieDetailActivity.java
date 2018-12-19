package com.muhammadnabillah1603631.utsmobpro.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.muhammadnabillah1603631.utsmobpro.adapter.MyGlide.GlideApp;
import com.muhammadnabillah1603631.utsmobpro.controller.MovieApi;
import com.muhammadnabillah1603631.utsmobpro.global.GlobalVariables;
import com.muhammadnabillah1603631.utsmobpro.model.CreditResponse;
import com.muhammadnabillah1603631.utsmobpro.model.Movie;
import com.muhammadnabillah1603631.utsmobpro.R;

public class MovieDetailActivity extends AppCompatActivity {
    public static String MOVIE_DATA = "movie_data";

    CreditResponse creditResponse;

    MovieApi movieApi;

    ImageView imageViewBackdrop, imageViewPoster;
    TextView textViewTitleYear, textViewOverview, textViewCrewJob, textViewCrewName;
    Button buttonViewTheater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        imageViewBackdrop = findViewById(R.id.image_view_backdrop);
        imageViewPoster = findViewById(R.id.image_view_poster);
        textViewTitleYear = findViewById(R.id.text_view_title);
        textViewOverview = findViewById(R.id.text_view_overview);
        textViewCrewJob = findViewById(R.id.crew_job);
        textViewCrewName = findViewById(R.id.crew_name);
        buttonViewTheater = findViewById(R.id.button_view_theater);

        final Movie m = getIntent().getParcelableExtra(MOVIE_DATA);

        getSupportActionBar().setTitle("MyMovie - " + m.getTitle());

        GlideApp.with(this)
                .load(GlobalVariables.BASE_URL_IMG + m.getPosterPath())
                .centerCrop()
                .override(125, 200)
                .transition(new DrawableTransitionOptions().crossFade())
                .into(imageViewPoster);

        GlideApp.with(this)
                .load(GlobalVariables.BASE_URL_IMG + m.getBackdropPath())
                .centerCrop()
                .transition(new DrawableTransitionOptions().crossFade())
                .into(imageViewBackdrop);

        textViewTitleYear.setText(m.getTitle());

//        getCredits(m);
//        textViewCrewJob.setText(creditResponse.getCrewList().get(0).getJob());
//        textViewCrewName.setText(creditResponse.getCrewList().get(0).getName());

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

//    private void getCredits(Movie m) {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(MovieCardViewActivity.BASE_URL)
//                .addConverterFactory(GsonConverterFactory
//                        .create(new GsonBuilder()
//                                .setLenient()
//                                .create()))
//                .build();
//
//        movieApi = retrofit.create(MovieApi.class);
//
//        Call<CreditResponse> creditResponseCall = movieApi.getCredits(m.getId(), MovieCardViewActivity.API_KEY);
//        creditResponseCall.enqueue(new Callback<CreditResponse>() {
//            @Override
//            public void onResponse(Call<CreditResponse> call, Response<CreditResponse> response) {
//                if (response.isSuccessful()) {
//                    creditResponse = response.body();
//                } else {
//                    Toast.makeText(MovieDetailActivity.this, response.message(), Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CreditResponse> call, Throwable t) {
//                Toast.makeText(MovieDetailActivity.this, t.toString(), Toast.LENGTH_LONG).show();
//            }
//        });
//    }
}
