package com.example.movielibrary;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    ArrayList<Match> data = new ArrayList<Match>();

    public void setData(ArrayList<Match> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false); //CardView inflated as RecyclerView list item
        ViewHolder viewHolder = new ViewHolder(v);
        Log.d("week6App","onCreateViewHolder");
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.team1.setText(data.get(position).getTeam1());
        holder.team2.setText(data.get(position).getTeam2());
        holder.score1.setText(data.get(position).getScore1());
        holder.score2.setText(data.get(position).getScore2());
        holder.match.setText(data.get(position).getMatchdate());
        holder.venue.setText(data.get(position).getVenue());
        holder.fans.setText(data.get(position).getFans());
        Log.d("week6App","onBindViewHolder");

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView team1, team2, score1, score2, match, venue, fans ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            team1 = itemView.findViewById(R.id.team1);
            team2 = itemView.findViewById(R.id.team2);
            score1 = itemView.findViewById(R.id.score1);
            score2 = itemView.findViewById(R.id.score2);
            match = itemView.findViewById(R.id.match);
            venue = itemView.findViewById(R.id.venue);
            fans = itemView.findViewById(R.id.fans);
        }
    }
}
