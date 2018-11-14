package com.muhammadnabillah1603631.utsmobpro.Controller;

import com.muhammadnabillah1603631.utsmobpro.Model.GenreResponse;
import com.muhammadnabillah1603631.utsmobpro.Model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {
    @GET("movie/now_playing")
    Call<MovieResponse> getNowPlayingMovies(@Query("api_key") String apiKey);

    @GET("genre/movie/list")
    Call<GenreResponse> getGenres(@Query("api_key") String apiKey);
}
