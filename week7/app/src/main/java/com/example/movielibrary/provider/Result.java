package com.example.movielibrary.provider;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "results")
public class Result {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "resultId")
    private int id;

    @ColumnInfo(name = "team1")
    private String team1;

    @ColumnInfo(name = "team2")
    private String team2;

    @ColumnInfo(name = "score1")
    private String score1;

    @ColumnInfo(name = "score2")
    private String score2;

    @ColumnInfo(name = "matchdate")
    private String date;

    @ColumnInfo(name = "venue")
    private String venue;

    @ColumnInfo(name = "fans")
    private String fans;

    public Result( String team1, String team2, String score1, String score2, String date, String venue, String fans) {
        this.team1 = team1;
        this.team2 = team2;
        this.score1 = score1;
        this.score2 = score2;
        this.date = date;
        this.venue = venue;
        this.fans = fans;
    }



    public int getId() {
        return id;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public String getScore1() {
        return score1;
    }

    public String getScore2() {
        return score2;
    }

    public String getDate() {
        return date;
    }

    public String getVenue() {
        return venue;
    }

    public String getFans() {
        return fans;
    }


    public void setId(@NonNull int id) {
        this.id = id;
    }


}