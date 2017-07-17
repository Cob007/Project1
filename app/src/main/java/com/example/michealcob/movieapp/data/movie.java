package com.example.michealcob.movieapp.data;

/**
 * Created by michealcob on 7/17/17.
 */

public class movie {
    String imageurl;
    String title;
    String overview;

    public String getTitle() {
        return title;
    }

    public String getImageurl() {
        return imageurl;
    }

    public String getOverview() {
        return overview;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}

