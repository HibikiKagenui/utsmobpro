package com.muhammadnabillah1603631.utsmobpro.Controller;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.muhammadnabillah1603631.utsmobpro.Model.MovieResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiController implements Callback<MovieResponse> {
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String API_KEY = "8602657acd0ef3edffc054f34404bfa9";
    private MovieResponse movieResponse;
    private Context context;

    public MovieResponse getMovieResponse() {
        return movieResponse;
    }

    public void start(Context context) {
        this.context = context;

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        MovieApi movieApi = retrofit.create(MovieApi.class);

        Call<MovieResponse> call = movieApi.getNowPlayingMovies(API_KEY);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
        if (response.isSuccessful()) {
            movieResponse = response.body();
        } else {
            Toast.makeText(context, response.message(), Toast.LENGTH_LONG).show();
        }
        Toast.makeText(context, response.message(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailure(Call<MovieResponse> call, Throwable t) {
        Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show();
    }
}
