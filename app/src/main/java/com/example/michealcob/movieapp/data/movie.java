package com.example.michealcob.movieapp.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by michealcob on 7/17/17.
 */

public class movie implements Parcelable{
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

    public movie(String imageurl, String title, String overview){
        this.imageurl = imageurl;
        this.title = title;
        this.overview = overview;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public movie(Parcel in){
        String[] data = new String[3];

        in.readStringArray(data);
        this.imageurl = data[0];
        this.title = data[1];
        this.overview = data[2];
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{
                this.imageurl, this.title, this.overview
        });
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){

        @Override
        public movie createFromParcel(Parcel source) {
            return new movie(source);
        }

        @Override
        public movie[] newArray(int size) {
            return new movie[size];
        }
    };
}

