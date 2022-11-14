package com.example.soyouthinkyoucantap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private ImageButton onePlayerButton;
    private ImageButton twoPlayerButton;
    private ImageButton threePlayerButton;
    private ImageButton fourPlayerButton;
    private TextView highscore;
    private TextView highscorer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        onePlayerButton = (ImageButton) findViewById(R.id.onePersonButton);
        onePlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOnePlayer();
            }
        });
        twoPlayerButton = (ImageButton) findViewById(R.id.twoPersonButton);
        twoPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTwoPlayer();
            }
        });
        threePlayerButton = (ImageButton) findViewById(R.id.threePersonButton);
        threePlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openThreePlayer();
            }
        });
        fourPlayerButton = (ImageButton) findViewById(R.id.fourPersonButton);
        fourPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFourPlayer();
            }
        });

        highscore = (TextView) findViewById(R.id.highscore);
        highscorer = (TextView) findViewById(R.id.highscorer);

        FileInputStream fis = null;
        try {
            fis = openFileInput("highscorer.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            highscorer.setText(br.readLine());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to load highscorer");
        }
        try {
            fis = openFileInput("highscore.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            highscore.setText(br.readLine());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to load highscore");
        }
    }

    public void openOnePlayer() {
        Intent intent = new Intent(this, OnePlayerActivity.class);
        startActivity(intent);
    }

    public void openTwoPlayer() {
        Intent intent = new Intent(this, TwoPlayerActivity.class);
        startActivity(intent);
    }
    public void openThreePlayer() {
        Intent intent = new Intent(this, ThreePlayerActivity.class);
        startActivity(intent);
    }

    public void openFourPlayer() {
        Intent intent = new Intent(this, FourPlayerActivity.class);
        startActivity(intent);
    }
}