package com.example.michealcob.movieapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.michealcob.movieapp.R;
import com.example.michealcob.movieapp.data.movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by michealcob on 7/17/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;

    public List<movie> mDataset;

    public MovieAdapter(List<movie> myDataset){
        this.mDataset = myDataset;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image_view);
        }
    }


    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.style, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieAdapter.ViewHolder holder, int position) {
        String url = "http://image.tmdb.org/t/p/w342//" + mDataset.get(position).getImageurl();
        Picasso.with(context).load(url).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if(null == mDataset) return 0;
        return this.mDataset.size();
    }
}
