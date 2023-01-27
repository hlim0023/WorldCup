package com.example.movielibrary;

public class Match {

    private String team1, team2, score1, score2, matchdate, venue, fans;

    public Match(String team1, String team2, String score1, String score2, String matchdate, String venue, String fans) {
        this.team1 = team1;
        this.team2 = team2;
        this.score1 = score1;
        this.score2 = score2;
        this.matchdate = matchdate;
        this.venue = venue;
        this.fans = fans;
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

    public String getMatchdate() {
        return matchdate;
    }

    public String getVenue() {
        return venue;
    }

    public String getFans() {
        return fans;
    }
}
