package com.example.hp.mymovies;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CustomLayout extends RecyclerView.Adapter<CustomLayout.MyViewHolder> {
    Context myContext;
//    ArrayList<Integer> Images;
    List<String> Titles;
    List<String> mReleaseDate;
    List<String> mVoteCount;
    List<String> mOverview;
    List<String> mPosterPath;

    public CustomLayout(List<String> movieTitle, List<String> movieReleaseDate,
                        List<String> movieVoteCount, List<String> movieOverview, List<String> moviePosterPath){
//        this.myContext = myContext;
        this.Titles = movieTitle;
        mReleaseDate = movieReleaseDate;
        mVoteCount = movieVoteCount;
        mOverview = movieOverview;
        mPosterPath = moviePosterPath;
    }

    public CustomLayout(Context myContext){
        this.myContext = myContext;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.custom_view, parent, false);
        MyViewHolder MVH = new MyViewHolder(view);

        return MVH;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.viewHolderTitle.setText(MovieInfo.Movies.title.get(position));
//        holder.viewHolderImage.setImageResource(R.drawable.ic_launcher_foreground);
        Glide.with(myContext).load(MovieInfo.Movies.poster_path.get(position)).into(holder.viewHolderImage);
    }

    @Override
    public int getItemCount() {
        return 10;
    }



    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView viewHolderTitle;
        ImageView viewHolderImage;
        RecyclerView recyclerView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            viewHolderTitle = itemView.findViewById(R.id.movie_title);
            viewHolderTitle = itemView.findViewById(R.id.movie_image);
            recyclerView = itemView.findViewById(R.id.recycler_view);

        }
    }




}
