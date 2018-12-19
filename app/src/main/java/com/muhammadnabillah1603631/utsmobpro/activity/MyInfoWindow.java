package com.muhammadnabillah1603631.utsmobpro.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.muhammadnabillah1603631.utsmobpro.adapter.MyGlide.GlideApp;
import com.muhammadnabillah1603631.utsmobpro.global.GlobalVariables;
import com.muhammadnabillah1603631.utsmobpro.model.Movie;
import com.muhammadnabillah1603631.utsmobpro.R;

public class MyInfoWindow implements GoogleMap.InfoWindowAdapter {
    Context context;
    LayoutInflater layoutInflater;
    Movie m;

    public MyInfoWindow(Context context, Movie m) {
        this.context = context;
        this.m = m;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.my_info_window, null);

        ImageView imageViewMoviePoster = v.findViewById(R.id.info_window_movie_poster);
        GlideApp.with(context)
                .load(GlobalVariables.BASE_URL_IMG + m.getPosterPath())
                .fitCenter()
                .override(160, 100)
                .transition(new DrawableTransitionOptions().crossFade())
                .into(imageViewMoviePoster);

        TextView textViewMovieTitle = v.findViewById(R.id.info_window_movie_title);
        textViewMovieTitle.setText(m.getTitle());

        return v;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}
