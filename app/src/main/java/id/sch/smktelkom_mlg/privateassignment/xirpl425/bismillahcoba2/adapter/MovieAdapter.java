package id.sch.smktelkom_mlg.privateassignment.xirpl425.bismillahcoba2.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl425.bismillahcoba2.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl425.bismillahcoba2.model.MovieModel;


/**
 * Created by User on 13/05/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private ArrayList<MovieModel> brandModelArrayList;

    public MovieAdapter(ArrayList<MovieModel> brandModelArrayList) {
        this.brandModelArrayList = brandModelArrayList;

    }

    @Override
    public int getItemCount() {
        return brandModelArrayList.size();
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
        holder.txtTitle.setText(brandModelArrayList.get(position).getTitle());
        holder.txtOverview.setText(brandModelArrayList.get(position).getOverview());
        holder.movieModel = brandModelArrayList.get(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitle;
        public TextView txtOverview;
        public MovieModel movieModel;

        public ViewHolder(View v) {
            super(v);
            txtTitle = (TextView) v.findViewById(R.id.txtTitle);
            txtOverview = (TextView) v.findViewById(R.id.txtOverView);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), movieModel.getTitle(), Toast.LENGTH_LONG).show();
                }
            });
        }

    }
}