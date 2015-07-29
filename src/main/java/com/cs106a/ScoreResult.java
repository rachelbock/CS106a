package com.cs106a;

/**
 *
 */
public class ScoreResult {

    //constructor

    public ScoreResult (int score, int player) {
        highestScore = score;
        playerHighScore = player;
    }

    //getters

    public int getHighestScore() {
        return highestScore;
    }

    public int getPlayerHighScore() {
        return playerHighScore;
    }

    private int highestScore = 0;
    private int playerHighScore = 0;

}
