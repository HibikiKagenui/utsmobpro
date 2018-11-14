package com.muhammadnabillah1603631.utsmobpro.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.muhammadnabillah1603631.utsmobpro.Activity.MovieCardViewActivity;
import com.muhammadnabillah1603631.utsmobpro.Activity.MovieDetailActivity;
import com.muhammadnabillah1603631.utsmobpro.Adapter.MyGlide.GlideApp;
import com.muhammadnabillah1603631.utsmobpro.Model.Genre;
import com.muhammadnabillah1603631.utsmobpro.Model.Movie;
import com.muhammadnabillah1603631.utsmobpro.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MovieCardViewAdapter extends RecyclerView.Adapter<MovieCardViewAdapter.CardViewHolder> {
    private List<Movie> movieList;
    private List<Genre> genreList;
    private Context context;

    public MovieCardViewAdapter(Context context) {
        this.context = context;
    }

    public List<Movie> getListMovie() {
        return movieList;
    }

    public void setListMovie(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public List<Genre> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie_card_view, viewGroup, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder cardViewHolder, int i) {
        final Movie m = getListMovie().get(i);

        // set poster
        GlideApp.with(context)
                .load(MovieCardViewActivity.BASE_URL_IMG + m.getPosterPath())
                .centerCrop()
                .override(100, 160)
                .transition(new DrawableTransitionOptions().crossFade())
                .into(cardViewHolder.imageViewPoster);
        // set title
        cardViewHolder.textViewTitle.setText(m.getTitle());
        // set date
        String date = m.getReleaseDate();
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date newDate = simpleDateFormat.parse(date);
            date = DateFormat.getDateInstance(DateFormat.LONG).format(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cardViewHolder.textViewDate.setText(date);
        // set genre
        StringBuilder genres = new StringBuilder();
        for (int x : m.getGenreIds()) {
            for (Genre y : getGenreList()) {
                if (x == y.getId()) {
                    genres.append(y.getName());
                    genres.append(", ");
                }
            }
        }
        genres.delete(genres.length() - 2, genres.length());
        cardViewHolder.textViewGenre.setText(genres.toString());
        // set button
        cardViewHolder.buttonMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToDetail = new Intent(context, MovieDetailActivity.class);
                moveToDetail.putExtra(MovieDetailActivity.MOVIE_DATA, m);
                context.startActivity(moveToDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getListMovie().size();
    }

    public void setListGenre(List<Genre> genreList) {
        this.genreList = genreList;
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewPoster;
        TextView textViewTitle, textViewDate, textViewGenre;
        Button buttonMoreInfo;

        public CardViewHolder(View view) {
            super(view);
            this.imageViewPoster = view.findViewById(R.id.image_item_poster);
            this.textViewTitle = view.findViewById(R.id.textview_item_title);
            this.textViewDate = view.findViewById(R.id.textview_item_date);
            this.textViewGenre = view.findViewById(R.id.textview_item_genre);
            this.buttonMoreInfo = view.findViewById(R.id.button_more_info);
        }
    }
}
