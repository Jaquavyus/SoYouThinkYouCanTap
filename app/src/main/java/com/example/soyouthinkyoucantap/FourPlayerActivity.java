package com.example.soyouthinkyoucantap;

import static com.example.soyouthinkyoucantap.MainActivity.mplayer;
import static com.example.soyouthinkyoucantap.MainActivity.rand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class FourPlayerActivity extends AppCompatActivity {
    private ImageButton backButton;
    private ImageButton startButton;
    private ImageButton restartButton;
    private ImageButton playerOneButton;
    private ImageButton playerTwoButton;
    private ImageButton playerThreeButton;
    private ImageButton playerFourButton;
    private TextView playerOneTimer;
    private TextView playerTwoTimer;
    private TextView playerThreeTimer;
    private TextView playerFourTimer;
    private TextView playerOneScore;
    private TextView playerTwoScore;
    private TextView playerThreeScore;
    private TextView playerFourScore;
    Player playerOne = new Player();
    Player playerTwo = new Player();
    Player playerThree = new Player();
    Player playerFour = new Player();

    EditText highscorerName;
    private Button submitHighscorerButton;

    RelativeLayout highscoreInputBox;

    public int counterOne = 3;
    public int counterTwo = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_four_player);

        backButton = (ImageButton) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });

        startButton = (ImageButton) findViewById(R.id.startButton);
        restartButton = (ImageButton) findViewById(R.id.restartButton);
        playerOneButton = (ImageButton) findViewById(R.id.playerOneButton);
        playerTwoButton = (ImageButton) findViewById(R.id.playerTwoButton);
        playerThreeButton = (ImageButton) findViewById(R.id.playerThreeButton);
        playerFourButton = (ImageButton) findViewById(R.id.playerFourButton);
        playerOneTimer = (TextView) findViewById(R.id.playerOneTimer);
        playerTwoTimer = (TextView) findViewById(R.id.playerTwoTimer);
        playerThreeTimer = (TextView) findViewById(R.id.playerThreeTimer);
        playerFourTimer = (TextView) findViewById(R.id.playerFourTimer);
        playerOneScore = (TextView) findViewById(R.id.playerOneScore);
        playerTwoScore = (TextView) findViewById(R.id.playerTwoScore);
        playerThreeScore = (TextView) findViewById(R.id.playerThreeScore);
        playerFourScore = (TextView) findViewById(R.id.playerFourScore);
        highscoreInputBox = (RelativeLayout) findViewById(R.id.highscoreInputBox);

        disableClickers();

        highscorerName = findViewById(R.id.editTextHighscorer);
        submitHighscorerButton = (Button) findViewById(R.id.submitHighscorerButton);
        submitHighscorerButton.setClickable(false);

        // when we add text in the edit text
        // it will check for the pattern of text
        highscorerName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            // whenever text size changes it will check
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // if text written matches the pattern then
                // it will show a toast of pattern matches
                if (highscorerName.getText().toString().length() <= 30) {
                    submitHighscorerButton.setClickable(true);
                } else {
                    // otherwise show error of invalid name
                    submitHighscorerButton.setClickable(false);
                    highscorerName.setError("Too long");
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void startGame(View view) {
        mplayer.stop();
        mplayer = MediaPlayer.create(FourPlayerActivity.this, R.raw.countdown);
        mplayer.start();
        startButton.setVisibility(View.INVISIBLE);
        restartButton.setVisibility(View.INVISIBLE);
        playerOneTimer.setVisibility(View.VISIBLE);
        playerTwoTimer.setVisibility(View.VISIBLE);
        playerThreeTimer.setVisibility(View.VISIBLE);
        playerFourTimer.setVisibility(View.VISIBLE);


        new CountDownTimer(3000, 1000){
            public void onTick(long millisUntilFinished){
                playerOneTimer.setText(String.valueOf(counterOne));
                playerTwoTimer.setText(String.valueOf(counterOne));
                playerThreeTimer.setText(String.valueOf(counterOne));
                playerFourTimer.setText(String.valueOf(counterOne));
                counterOne--;
            }
            public  void onFinish(){
                playerOneTimer.setText("Go!");
                playerTwoTimer.setText("Go!");
                playerThreeTimer.setText("Go!");
                playerFourTimer.setText("Go!");
                enableClickers();
                new CountDownTimer(30000, 1000){
                    public void onTick(long millisUntilFinished){
                        if(counterTwo < 30) {
                            playerOneTimer.setText(String.valueOf(counterTwo));
                            playerTwoTimer.setText(String.valueOf(counterTwo));
                            playerThreeTimer.setText(String.valueOf(counterTwo));
                            playerFourTimer.setText(String.valueOf(counterTwo));
                        }
                        counterTwo--;
                    }
                    public  void onFinish(){
                        mplayer.stop();
                        mplayer = MediaPlayer.create(FourPlayerActivity.this, (rand.nextInt(2) == 0) ? R.raw.slowmotion : R.raw.funkysuspense);
                        mplayer.start();
                        disableClickers();
                        playerOneTimer.setText(Game.victorOrLoser(playerOne, playerTwo, playerThree, playerFour));
                        playerTwoTimer.setText(Game.victorOrLoser(playerTwo, playerOne, playerThree, playerFour));
                        playerThreeTimer.setText(Game.victorOrLoser(playerThree, playerOne, playerTwo, playerFour));
                        playerFourTimer.setText(Game.victorOrLoser(playerFour, playerOne, playerTwo, playerThree));
                        updateHighscoreFromDocument();
                        if(Game.wasHighscoreAchieved(playerOne, playerTwo, playerThree, playerFour)) {
                            mplayer = MediaPlayer.create(FourPlayerActivity.this, R.raw.highscore);
                            mplayer.start();
                            restartButton.setClickable(false);
                            highscoreInputBox.setVisibility(View.VISIBLE);
                        }
                        mplayer = MediaPlayer.create(FourPlayerActivity.this, R.raw.slowmotion);
                        restartButton.setVisibility(View.VISIBLE);
                    }
                }.start();
            }
        }.start();
    }

    public void restartGame(View view) {
        Game.resetScores(playerOne, playerTwo, playerThree, playerFour, playerOneScore, playerTwoScore, playerThreeScore, playerFourScore);
        counterOne = 3;
        counterTwo = 30;
        startGame(view);
    }

    public void enableClickers() {
        playerOneButton.setClickable(true);
        playerTwoButton.setClickable(true);
        playerThreeButton.setClickable(true);
        playerFourButton.setClickable(true);
        mplayer.stop();
        Random rand = new Random();
        int track = rand.nextInt(4);
        switch(track)
        {
            case 0:
                track = R.raw.dance;
            case 1:
                track = R.raw.epic;
            case 2:
                track = R.raw.extremeaction;
        }
        mplayer = MediaPlayer.create(FourPlayerActivity.this, track);
        mplayer.start();
    }

    public void disableClickers() {
        playerOneButton.setClickable(false);
        playerTwoButton.setClickable(false);
        playerThreeButton.setClickable(false);
        playerFourButton.setClickable(false);
    }

    public void countPlayerOneClick(View view) {
        Game.countClick(playerOne, playerOneScore);
    }

    public void countPlayerTwoClick(View view) {
        Game.countClick(playerTwo, playerTwoScore);
    }

    public void countPlayerThreeClick(View view) {
        Game.countClick(playerThree, playerThreeScore);
    }

    public void countPlayerFourClick(View view) {
        Game.countClick(playerFour, playerFourScore);
    }

    public void updateHighscoreFromDocument() {
        FileInputStream fis = null;
        try {
            fis = openFileInput("highscore.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            Game.setHighscore(Integer.valueOf(br.readLine()));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to load highscore");
        }
    }

    public void submitHighscore(View view) {
        String highscore = Game.returnNewHighscoreAsString(playerOne, playerTwo, playerThree, playerFour);
        submitHighscoreValue(highscore);
        submitHighscorerName();
        highscoreInputBox.setVisibility(View.GONE);
        restartButton.setClickable(true);
    }

    public void submitHighscorerName() {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("highscorer.txt", MODE_PRIVATE);
            fos.write(highscorerName.getText().toString().getBytes());
            highscorerName.getText().clear();
            Toast.makeText(this, "Saved highscorer to " + getFilesDir(), Toast.LENGTH_LONG);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to save to highscorer.txt");
        } finally {
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void submitHighscoreValue(String highscore) {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("highscore.txt", MODE_PRIVATE);
            fos.write(highscore.getBytes());
            Toast.makeText(this, "Saved highscore to " + getFilesDir(), Toast.LENGTH_LONG);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to save to highscore.txt");
        } finally {
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}