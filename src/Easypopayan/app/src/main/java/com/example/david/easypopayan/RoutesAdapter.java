package com.example.david.easypopayan;

/**
 * Created by david on 12/03/18.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RoutesAdapter extends RecyclerView.Adapter<RoutesAdapter.MyViewHolder> {

    private List<Route> routeList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, timeEstimated, genre;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.name);
            genre = (TextView) view.findViewById(R.id.details);
            timeEstimated = (TextView) view.findViewById(R.id.timeEstimated);
        }
    }


    public RoutesAdapter(List<Route> routesList) {
        this.routeList = routesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Route movie = routeList.get(position);
        holder.title.setText(movie.getTitle());
        holder.genre.setText(movie.getGenre());
        holder.timeEstimated.setText(movie.getYear());
    }

    @Override
    public int getItemCount() {
        return routeList.size();
    }
}
