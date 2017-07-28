package com.example.michealcob.movieapp;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.michealcob.movieapp.adapter.MovieAdapter;
import com.example.michealcob.movieapp.data.movie;
import com.example.michealcob.movieapp.listerner.RecyclerViewOnClickListener;
import com.example.michealcob.movieapp.util.JsonParser;
import com.example.michealcob.movieapp.util.Url;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements RecyclerViewOnClickListener.OnItemClickListener{

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
        recyclerView.addOnItemTouchListener(new RecyclerViewOnClickListener(this, this));
        searchPosition(0);
        sendRequest();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.sort_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.top_rated:
                searchPosition(1);
                return true;
            case R.id.most_popular:
                searchPosition(2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, DetailedActivity.class);
        intent.putExtra("movies", MovieList.get(position));
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }
}
