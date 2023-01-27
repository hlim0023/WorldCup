package com.example.worldcup.provider;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface ResultDao {

    // ALL result
    @Query("select * from results")
    LiveData<List<Result>> getAllResult();
    //Live Data make the result to be up to date

    @Insert
    void addResult(Result result);

    @Query("delete FROM results")
    void deleteAllResults();


//    //team1
//    @Query("select * from results where team1=:team1")
//    List<Result> getTeam1(String team1);
//
//    @Query("delete from results where team1= :team1")
//    void deleteTeam1(String team1);
//
//    //team2
//    @Query("select * from results where team2=:team2")
//    List<Result> getTeam2(String team2);
//
//    @Query("delete from results where team2= :team2")
//    void deleteTeam2(String team2);
//
//    //score 1
//    @Query("select * from results where score1=:score1")
//    List<Result> getScore1(String score1);
//
//    @Query("delete from results where score1= :score1")
//    void deleteScore1(String score1);
//
//    //score 2
//    @Query("select * from results where score2=:name")
//    List<Result> getScore2(String name);
//
//    @Query("delete from results where score2= :name")
//    void deleteScore2(String name);
//
//    // match
//    @Query("select * from results where Matchdate=:name")
//    List<Result> getMatch(String name);
//
//    @Query("delete from results where Matchdate= :name")
//    void deleteMatch(String name);
//
//    // venue
//    @Query("select * from results where venue=:name")
//    List<Result> getVenue(String name);
//
//    @Query("delete from results where venue= :name")
//    void deleteVenue(String name);
//
//    // fans
//    @Query("select * from results where fans=:name")
//    List<Result> getFans(String name);
//
//    @Query("delete from results where fans= :name")
//    void deleteFans(String name);



}



