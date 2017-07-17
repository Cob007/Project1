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
    TextView title, overview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        imageView = (ImageView) findViewById(R.id.image_view);
        title = (TextView) findViewById(R.id.title);
        overview = (TextView) findViewById(R.id.overview);

        //Intent intent = getIntent();
        //movieClass = intent.getParcelableExtra("movies");

        Bundle intent = getIntent().getExtras();
        movieClass = intent.getParcelable("movies");

        Picasso.with(this)
                .load("http://image.tmdb.org/t/p/342//"+movieClass.getImageurl())
                .placeholder(R.color.colorPrimary)
                .into(imageView);

        title.setText(movieClass.getTitle());
        overview.setText(movieClass.getOverview());
    }
}
