package id.sch.smktelkom_mlg.privateassignment.xirpl425.bismillahcoba2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl425.bismillahcoba2.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl425.bismillahcoba2.model.MovieModel;


/**
 * Created by User on 13/05/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    public static final String IMAGE_URL_BASE_PATH = "http://image.tmdb.org/t/p/w500";
    Context context;
    private ArrayList<MovieModel> movieModelArrayList;

    public MovieAdapter(Context context) {
        this.context = context;
    }

    public MovieAdapter(ArrayList<MovieModel> movieModelArrayList, Context context) {
        this.movieModelArrayList = movieModelArrayList;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return movieModelArrayList.size();
    }

    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_movie, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.txtTitle.setText(movieModelArrayList.get(position).getTitle());
        holder.txtOverview.setText(movieModelArrayList.get(position).getOverview());
        Glide.with(context)
                .load(IMAGE_URL_BASE_PATH + movieModelArrayList.get(position).getPoster_path())
                .into(holder.ivPoster);
        holder.movieModel = movieModelArrayList.get(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitle;
        public TextView txtOverview;
        public MovieModel movieModel;
        public ImageView ivPoster;

        public ViewHolder(View v) {
            super(v);
            txtTitle = (TextView) v.findViewById(R.id.txtTitle);
            txtOverview = (TextView) v.findViewById(R.id.txtOverView);
            ivPoster = (ImageView) v.findViewById(R.id.imageViewPoster);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), movieModel.getTitle(), Toast.LENGTH_LONG).show();
                }
            });
        }

    }
}