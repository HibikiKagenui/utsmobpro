package com.muhammadnabillah1603631.utsmobpro.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.muhammadnabillah1603631.utsmobpro.Adapter.MovieCardViewAdapter;
import com.muhammadnabillah1603631.utsmobpro.Controller.MovieApi;
import com.muhammadnabillah1603631.utsmobpro.Model.Genre;
import com.muhammadnabillah1603631.utsmobpro.Model.GenreResponse;
import com.muhammadnabillah1603631.utsmobpro.Model.Movie;
import com.muhammadnabillah1603631.utsmobpro.Model.MovieResponse;
import com.muhammadnabillah1603631.utsmobpro.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieCardViewActivity extends AppCompatActivity {
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String BASE_URL_IMG = "https://image.tmdb.org/t/p/w500";
    public static final String API_KEY = "8602657acd0ef3edffc054f34404bfa9";

    RecyclerView recyclerViewMovies;
    MovieApi movieApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_card_view);

        recyclerViewMovies = findViewById(R.id.recycler_view_movies);

        startApi();
    }

    private void startApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory
                        .create(new GsonBuilder()
                                .setLenient()
                                .create()))
                .build();

        movieApi = retrofit.create(MovieApi.class);

        getGenres();
    }

    private void getGenres() {
        Call<GenreResponse> genreResponseCall = movieApi.getGenres(API_KEY);
        genreResponseCall.enqueue(new Callback<GenreResponse>() {
            @Override
            public void onResponse(Call<GenreResponse> call, Response<GenreResponse> response) {
                if (response.isSuccessful()) {
                    GenreResponse genreResponse = response.body();
                    getMovies(genreResponse);
                } else {
                    Toast.makeText(MovieCardViewActivity.this, response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<GenreResponse> call, Throwable t) {
                Toast.makeText(MovieCardViewActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private void getMovies(final GenreResponse genreResponse) {
        Call<MovieResponse> movieResponseCall = movieApi.getNowPlayingMovies(API_KEY);
        movieResponseCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    // ! start adapter here
                    showCardView(response.body().getResults(), genreResponse.getGenres());
                } else {
                    Toast.makeText(MovieCardViewActivity.this, response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(MovieCardViewActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showCardView(List<Movie> movieList, List<Genre> genreList) {
        recyclerViewMovies.setLayoutManager(new LinearLayoutManager(this));
        MovieCardViewAdapter adapter = new MovieCardViewAdapter(this);
        adapter.setListMovie(movieList);
        adapter.setListGenre(genreList);
        recyclerViewMovies.setAdapter(adapter);
    }
}
