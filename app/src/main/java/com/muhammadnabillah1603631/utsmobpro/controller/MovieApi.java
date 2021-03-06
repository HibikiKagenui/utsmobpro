package com.muhammadnabillah1603631.utsmobpro.controller;

import com.muhammadnabillah1603631.utsmobpro.model.CreditResponse;
import com.muhammadnabillah1603631.utsmobpro.model.GenreResponse;
import com.muhammadnabillah1603631.utsmobpro.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {
    @GET("movie/now_playing")
    Call<MovieResponse> getNowPlayingMovies(@Query("api_key") String apiKey);

    @GET("genre/movie/list")
    Call<GenreResponse> getGenres(@Query("api_key") String apiKey);

    @GET("movie/{movieId}/credits")
    Call<CreditResponse> getCredits(@Path("movieId") int movieId, @Query("api_key") String api_key);
}
