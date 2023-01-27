package com.example.worldcup.provider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worldcup.R;

import java.util.List;

public class ResultAdapter extends  RecyclerView.Adapter<ResultAdapter.ViewHolder> {
    List<Result> data ;

    public void setResult(List<Result> newData) {
        data = newData;
    }

    @NonNull
    @Override
    public ResultAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false); //CardView inflated as RecyclerView list item
        ResultAdapter.ViewHolder viewHolder = new ResultAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.team1.setText(data.get(position).getTeam1());
        holder.team2.setText(data.get(position).getTeam2());
        holder.score1.setText(data.get(position).getScore1());
        holder.score2.setText(data.get(position).getScore2());
        holder.match.setText(data.get(position).getDate());
        holder.venue.setText(data.get(position).getVenue());
        holder.fans.setText(data.get(position).getFans());
    }



    @Override
    public int getItemCount() {
        if (data == null)
            return 0;
        else
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
