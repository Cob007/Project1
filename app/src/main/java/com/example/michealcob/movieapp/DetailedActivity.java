package com.example.michealcob.movieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.michealcob.movieapp.data.movie;
import com.squareup.picasso.Picasso;

public class DetailedActivity extends AppCompatActivity {

    movie movieClass;

    ImageView imageView;
    TextView title, overview, releaseDate, vote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        imageView = (ImageView) findViewById(R.id.detailed_image_view);
        title = (TextView) findViewById(R.id.title);
        overview = (TextView) findViewById(R.id.overview);
        releaseDate = (TextView) findViewById(R.id.release_date);
        vote = (TextView) findViewById(R.id.vote);

        Intent intent = getIntent();
        movieClass = intent.getParcelableExtra("movies");

        /*Bundle intent = getIntent().getExtras();
        movieClass = intent.getParcelable("movies");*/

        Picasso.with(this)
                .load("http://image.tmdb.org/t/p/w342//"+movieClass.getImageurl())
                .into(imageView);

        title.setText(movieClass.getTitle());
        overview.setText(movieClass.getOverview());
        releaseDate.setText(movieClass.getReleaseDate());
        vote.setText(movieClass.getVotaAverage());
    }
}
