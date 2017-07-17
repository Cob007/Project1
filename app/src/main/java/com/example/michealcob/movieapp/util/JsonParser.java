package com.example.michealcob.movieapp.util;

import android.graphics.Movie;

import com.example.michealcob.movieapp.data.movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michealcob on 7/17/17.
 */

public class JsonParser {

    public static String[] imageUrl;
    public static String[] title;
    public static String[] overview;

    public JSONObject moviesObject;
    public JSONArray moviesList;

    public List<movie> Movies;

    private String Json;

    public JsonParser(String _json){
        this.Json = _json;
    }

    public void parseJson(){
        JSONObject jsonObject = null;

        try{
            moviesObject = new JSONObject(Json);

            moviesList = moviesObject.getJSONArray("results");

            imageUrl = new String[moviesList.length()];
            title = new String[moviesList.length()];
            overview = new String[moviesList.length()];

            Movies = new ArrayList<>();
            for(int i=0; i<moviesList.length(); i++){
                movie moviesObject = new movie(null, null, null);

                jsonObject = moviesList.getJSONObject(i);
                imageUrl[i] = jsonObject.getString("poster_path");
                title[i] = jsonObject.getString("title");
                overview[i] = jsonObject.getString("overview");

                moviesObject.setImageurl(imageUrl[i]);
                moviesObject.setTitle(title[i]);
                moviesObject.setOverview(overview[i]);

                Movies.add(moviesObject);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public List<movie> getMovies(){
        return Movies;
    }
}
