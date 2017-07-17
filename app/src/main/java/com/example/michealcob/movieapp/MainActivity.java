package com.example.michealcob.movieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.michealcob.movieapp.adapter.MovieAdapter;
import com.example.michealcob.movieapp.data.movie;
import com.example.michealcob.movieapp.util.JsonParser;
import com.example.michealcob.movieapp.util.Url;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static String TAG = MainActivity.class.getName();
    public String JSON_URL;
    public List<movie> MovieList;

    public Url url = new Url();


    public RecyclerView recyclerView;
    public RecyclerView.Adapter adapter;
    public RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        searchPosition(0);
        sendRequest();
    }

    public int searchPosition(int position){
        if (position == 1){
            JSON_URL = url.popular_url;
        } else if (position ==2 ){
            JSON_URL = url.top_rated_url;
        }else{
            JSON_URL = url.popular_url;
        }
        return position;
    }

    public void sendRequest(){
        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JsonParser jsonParser = new JsonParser(response);
                        jsonParser.parseJson();
                        MovieList = jsonParser.getMovies();
                        adapter = new MovieAdapter(MovieList);
                        recyclerView.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
