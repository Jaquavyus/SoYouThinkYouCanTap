package com.example.soyouthinkyoucantap;

public class Player
{
    private int score = 0;

    public void addPointToScore() {
        this.score++;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
