package com.example.worldcup.provider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worldcup.R;

import java.util.List;

public class MatchTimeAdapter extends  RecyclerView.Adapter<MatchTimeAdapter.ViewHolder> {
    List<Result> data ;

    public void setResult(List<Result> newData) {
        data = newData;
    }

    @NonNull
    @Override
    public MatchTimeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_matchtime, parent, false); //CardView inflated as RecyclerView list item
        MatchTimeAdapter.ViewHolder viewHolder = new MatchTimeAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.venue.setText(data.get(position).getVenue());

    }



    @Override
    public int getItemCount() {
        if (data == null)
            return 0;
        else
            return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView  venue ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            venue = itemView.findViewById(R.id.matchtime);
        }
    }
}
