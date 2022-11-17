package com.example.soyouthinkyoucantap;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game extends AppCompatActivity
{
    private static int highscore = 0;

    public static void setHighscore(int highscore) {
        Game.highscore = highscore;
    }

    public static void countClick(Player player, TextView score)
    {
        player.addPointToScore();
        score.setText("" + player.getScore());
    }

    public static String victorOrLoser(Player player, Player opponentOne)
    {
        String victor = "Victor!";
        String loser = "Loser!";
        String draw = "Draw!";
        if (player.getScore() < opponentOne.getScore())
            return loser;
        else if (player.getScore() == opponentOne.getScore())
            return draw;
        else
            return victor;
    }
    public static String victorOrLoser(Player player, Player opponentOne, Player opponentTwo)
    {
        String victor = "Victor!";
        String loser = "Loser!";
        String draw = "Draw!";
        if (player.getScore() < opponentOne.getScore() || player.getScore() < opponentTwo.getScore())
            return loser;
        else if (player.getScore() == opponentOne.getScore() || player.getScore() == opponentTwo.getScore())
            return draw;
        else
            return victor;
    }
    public static String victorOrLoser(Player player, Player opponentOne, Player opponentTwo, Player opponentThree)
    {
        String victor = "Victor!";
        String loser = "Loser!";
        String draw = "Draw!";
        if (player.getScore() < opponentOne.getScore() || player.getScore() < opponentTwo.getScore() || player.getScore() < opponentThree.getScore())
            return loser;
        else if (player.getScore() == opponentOne.getScore() || player.getScore() == opponentTwo.getScore() || player.getScore() == opponentThree.getScore())
            return draw;
        else
            return victor;
    }

    public static void resetScores(Player playerOne, TextView scoreOne)
    {
        playerOne.setScore(0);

        scoreOne.setText("" + playerOne.getScore());
    }

    public static void resetScores(Player playerOne, Player playerTwo, TextView scoreOne, TextView scoreTwo)
    {
        playerOne.setScore(0);
        playerTwo.setScore(0);

        scoreOne.setText("" + playerOne.getScore());
        scoreTwo.setText("" + playerTwo.getScore());
    }

    public static void resetScores(Player playerOne, Player playerTwo, Player playerThree, TextView scoreOne, TextView scoreTwo, TextView scoreThree)
    {
        playerOne.setScore(0);
        playerTwo.setScore(0);
        playerThree.setScore(0);

        scoreOne.setText("" + playerOne.getScore());
        scoreTwo.setText("" + playerTwo.getScore());
        scoreThree.setText("" + playerThree.getScore());
    }

    public static void resetScores(Player playerOne, Player playerTwo, Player playerThree, Player playerFour, TextView scoreOne, TextView scoreTwo, TextView scoreThree, TextView scoreFour)
    {
        playerOne.setScore(0);
        playerTwo.setScore(0);
        playerThree.setScore(0);
        playerFour.setScore(0);

        scoreOne.setText("" + playerOne.getScore());
        scoreTwo.setText("" + playerTwo.getScore());
        scoreThree.setText("" + playerThree.getScore());
        scoreFour.setText("" + playerFour.getScore());
    }

    public static boolean wasHighscoreAchieved(Player playerOne)
    {
        boolean highscoreAchieved = false;
        if(playerOne.getScore() > highscore) {
            highscoreAchieved = true;
        }
        else {
            highscoreAchieved = false;
        }
        return highscoreAchieved;
    }

    public static boolean wasHighscoreAchieved(Player playerOne, Player playerTwo)
    {
        boolean highscoreAchieved = false;
        if(playerOne.getScore() > highscore) {
            highscoreAchieved = true;
        }
        else {
            if(playerTwo.getScore() > highscore) {
                highscoreAchieved = true;
            }
            else {
                highscoreAchieved = false;
            }
        }
        return highscoreAchieved;
    }

    public static boolean wasHighscoreAchieved(Player playerOne, Player playerTwo, Player playerThree)
    {
        boolean highscoreAchieved = false;
        if(playerOne.getScore() > highscore) {
            highscoreAchieved = true;
        }
        else {
            if(playerTwo.getScore() > highscore) {
                highscoreAchieved = true;
            }
            else {
                if(playerThree.getScore() > highscore) {
                    highscoreAchieved = true;
                }
                else {
                    highscoreAchieved = false;
                }
            }
        }
        return highscoreAchieved;
    }

    public static boolean wasHighscoreAchieved(Player playerOne, Player playerTwo, Player playerThree, Player playerFour)
    {
        boolean highscoreAchieved = false;
        if(playerOne.getScore() > highscore) {
            highscoreAchieved = true;
        }
        else {
            if(playerTwo.getScore() > highscore) {
                highscoreAchieved = true;
            }
            else {
                if(playerThree.getScore() > highscore) {
                    highscoreAchieved = true;
                }
                else {
                    if(playerFour.getScore() > highscore) {
                        highscoreAchieved = true;
                    }
                    else {
                        highscoreAchieved = false;
                    }
                }
            }
        }
        return highscoreAchieved;
    }

    public static String returnNewHighscoreAsString(Player playerOne)
    {
        int one = playerOne.getScore();
        return String.valueOf(one);
    }

    public static String returnNewHighscoreAsString(Player playerOne, Player playerTwo)
    {
        int one = playerOne.getScore();
        int two = playerTwo.getScore();
        if(one >= two)
            return String.valueOf(one);
        else
            return String.valueOf(two);
    }

    public static String returnNewHighscoreAsString(Player playerOne, Player playerTwo, Player playerThree)
    {
        int one = playerOne.getScore();
        int two = playerTwo.getScore();
        int three = playerThree.getScore();
        if(one >= two && one >= three)
            return String.valueOf(one);
        else
        if(two >= one && two >= three)
            return String.valueOf(two);
        else
            return String.valueOf(three);
    }

    public static String returnNewHighscoreAsString(Player playerOne, Player playerTwo, Player playerThree, Player playerFour)
    {
        int one = playerOne.getScore();
        int two = playerTwo.getScore();
        int three = playerThree.getScore();
        int four = playerFour.getScore();
        if(one >= two && one >= three && one >= four)
            return String.valueOf(one);
        else
        if(two >= one && two >= three && two >= four)
            return String.valueOf(two);
        else
        if(three >= one && three >= two && three >= four)
            return String.valueOf(three);
        else
            return String.valueOf(four);
    }
}
