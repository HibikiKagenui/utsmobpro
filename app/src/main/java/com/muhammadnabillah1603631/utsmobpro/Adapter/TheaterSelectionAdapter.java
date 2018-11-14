package com.muhammadnabillah1603631.utsmobpro.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.muhammadnabillah1603631.utsmobpro.Activity.MapsActivity;
import com.muhammadnabillah1603631.utsmobpro.Adapter.MyGlide.GlideApp;
import com.muhammadnabillah1603631.utsmobpro.Model.Movie;
import com.muhammadnabillah1603631.utsmobpro.Model.Theater;
import com.muhammadnabillah1603631.utsmobpro.R;

import java.util.List;

public class TheaterSelectionAdapter extends RecyclerView.Adapter<TheaterSelectionAdapter.ListViewHolder> {
    private List<Theater> theaterList;
    private Movie movie;
    private Context context;

    public TheaterSelectionAdapter(Context context) {
        this.context = context;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public List<Theater> getTheaterList() {
        return theaterList;
    }

    public void setTheaterList(List<Theater> theaterList) {
        this.theaterList = theaterList;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_theater_selection, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder listViewHolder, int i) {
        final Theater t = getTheaterList().get(i);

        listViewHolder.theaterItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToMaps = new Intent(context, MapsActivity.class);
                moveToMaps.putExtra(MapsActivity.THEATER_DATA, t);
                moveToMaps.putExtra(MapsActivity.MOVIE_DATA, getMovie());
                context.startActivity(moveToMaps);
            }
        });

        GlideApp.with(context)
                .load(t.getLogoUrl())
                .fitCenter()
                .override(120, 60)
                .transition(new DrawableTransitionOptions().crossFade())
                .into(listViewHolder.imageViewTheaterLogo);

        listViewHolder.textViewTheaterName.setText(t.getName());
    }

    @Override
    public int getItemCount() {
        return getTheaterList().size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        LinearLayout theaterItem;
        ImageView imageViewTheaterLogo;
        TextView textViewTheaterName;

        public ListViewHolder(View view) {
            super(view);
            theaterItem = view.findViewById(R.id.theater_item);
            imageViewTheaterLogo = view.findViewById(R.id.image_view_theater_logo);
            textViewTheaterName = view.findViewById(R.id.text_view_theater_name);
        }
    }
}
