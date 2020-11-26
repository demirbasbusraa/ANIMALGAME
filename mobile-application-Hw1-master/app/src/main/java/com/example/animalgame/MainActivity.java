package com.example.animalgame;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextToSpeech tt;
    private static int level = 1;
    private static int score = 0;
    int BestScore;

    TextView levelText;
    TextView bestScoreText;
    TextView scoreText;
    TextView timeText;
    Button button;



    CountDownTimer countDownTimer;
    int time;
    long msec;

    private final int[] images = new int[] {
            R.drawable.bear, R.drawable.bee, R.drawable.bird,R.drawable.cat, R.drawable.cock, R.drawable.cow, R.drawable.dog, R.drawable.donkey, R.drawable.dove, R.drawable.elephant, R.drawable.frog, R.drawable.goat, R.drawable.goose, R.drawable.horse, R.drawable.lion, R.drawable.monkey, R.drawable.sheep, R.drawable.snake, R.drawable.turkey, R.drawable.wolf, R.drawable.bear, R.drawable.bee, R.drawable.bird,R.drawable.cat, R.drawable.cock, R.drawable.cow, R.drawable.dog, R.drawable.donkey, R.drawable.dove, R.drawable.elephant, R.drawable.frog, R.drawable.goat, R.drawable.goose, R.drawable.horse, R.drawable.lion, R.drawable.monkey, R.drawable.sheep, R.drawable.snake, R.drawable.turkey, R.drawable.wolf, R.drawable.bear, R.drawable.bee, R.drawable.bird,R.drawable.cat, R.drawable.cock, R.drawable.cow, R.drawable.dog, R.drawable.donkey, R.drawable.dove, R.drawable.elephant, R.drawable.frog, R.drawable.goat, R.drawable.goose, R.drawable.horse, R.drawable.lion, R.drawable.monkey, R.drawable.sheep, R.drawable.snake, R.drawable.turkey, R.drawable.wolf, R.drawable.bear, R.drawable.bee, R.drawable.bird,R.drawable.cat, R.drawable.cock, R.drawable.cow, R.drawable.dog, R.drawable.donkey, R.drawable.dove, R.drawable.elephant, R.drawable.frog, R.drawable.goat, R.drawable.goose, R.drawable.horse, R.drawable.lion, R.drawable.monkey, R.drawable.sheep, R.drawable.snake, R.drawable.turkey, R.drawable.wolf, R.drawable.bear, R.drawable.bee, R.drawable.bird,R.drawable.cat, R.drawable.cock, R.drawable.cow, R.drawable.dog, R.drawable.donkey, R.drawable.dove, R.drawable.elephant, R.drawable.frog, R.drawable.goat, R.drawable.goose, R.drawable.horse, R.drawable.lion, R.drawable.monkey, R.drawable.sheep, R.drawable.snake, R.drawable.turkey, R.drawable.wolf, R.drawable.bear, R.drawable.bee, R.drawable.bird,R.drawable.cat, R.drawable.cock, R.drawable.cow, R.drawable.dog, R.drawable.donkey, R.drawable.dove, R.drawable.elephant, R.drawable.frog, R.drawable.goat, R.drawable.goose, R.drawable.horse, R.drawable.lion, R.drawable.monkey, R.drawable.sheep, R.drawable.snake, R.drawable.turkey, R.drawable.wolf, R.drawable.bear, R.drawable.bee, R.drawable.bird,R.drawable.cat, R.drawable.cock, R.drawable.cow, R.drawable.dog, R.drawable.donkey, R.drawable.dove, R.drawable.elephant, R.drawable.frog, R.drawable.goat, R.drawable.goose, R.drawable.horse, R.drawable.lion, R.drawable.monkey, R.drawable.sheep, R.drawable.snake, R.drawable.turkey, R.drawable.wolf, R.drawable.bear, R.drawable.bee, R.drawable.bird,R.drawable.cat, R.drawable.cock, R.drawable.cow, R.drawable.dog, R.drawable.donkey, R.drawable.dove, R.drawable.elephant, R.drawable.frog, R.drawable.goat, R.drawable.goose, R.drawable.horse, R.drawable.lion, R.drawable.monkey, R.drawable.sheep, R.drawable.snake, R.drawable.turkey, R.drawable.wolf, R.drawable.bear, R.drawable.bee, R.drawable.bird,R.drawable.cat, R.drawable.cock, R.drawable.cow, R.drawable.dog, R.drawable.donkey, R.drawable.dove, R.drawable.elephant, R.drawable.frog, R.drawable.goat, R.drawable.goose, R.drawable.horse, R.drawable.lion, R.drawable.monkey, R.drawable.sheep, R.drawable.snake, R.drawable.turkey, R.drawable.wolf, R.drawable.bear, R.drawable.bee, R.drawable.bird,R.drawable.cat, R.drawable.cock, R.drawable.cow, R.drawable.dog, R.drawable.donkey, R.drawable.dove, R.drawable.elephant, R.drawable.frog, R.drawable.goat, R.drawable.goose, R.drawable.horse, R.drawable.lion, R.drawable.monkey, R.drawable.sheep, R.drawable.snake, R.drawable.turkey, R.drawable.wolf,R.drawable.bear, R.drawable.bee, R.drawable.bird,R.drawable.cat, R.drawable.cock, R.drawable.cow, R.drawable.dog, R.drawable.donkey, R.drawable.dove, R.drawable.elephant, R.drawable.frog, R.drawable.goat, R.drawable.goose, R.drawable.horse, R.drawable.lion, R.drawable.monkey, R.drawable.sheep, R.drawable.snake, R.drawable.turkey, R.drawable.wolf, R.drawable.bear, R.drawable.bee, R.drawable.bird,R.drawable.cat, R.drawable.cock, R.drawable.cow, R.drawable.dog, R.drawable.donkey, R.drawable.dove, R.drawable.elephant, R.drawable.frog, R.drawable.goat, R.drawable.goose, R.drawable.horse, R.drawable.lion, R.drawable.monkey, R.drawable.sheep, R.drawable.snake, R.drawable.turkey, R.drawable.wolf, R.drawable.bear, R.drawable.bee, R.drawable.bird,R.drawable.cat, R.drawable.cock, R.drawable.cow, R.drawable.dog, R.drawable.donkey, R.drawable.dove, R.drawable.elephant, R.drawable.frog, R.drawable.goat, R.drawable.goose, R.drawable.horse, R.drawable.lion, R.drawable.monkey, R.drawable.sheep, R.drawable.snake, R.drawable.turkey, R.drawable.wolf, R.drawable.bear, R.drawable.bee, R.drawable.bird,R.drawable.cat, R.drawable.cock, R.drawable.cow, R.drawable.dog, R.drawable.donkey, R.drawable.dove, R.drawable.elephant, R.drawable.frog, R.drawable.goat, R.drawable.goose, R.drawable.horse, R.drawable.lion, R.drawable.monkey, R.drawable.sheep, R.drawable.snake, R.drawable.turkey, R.drawable.wolf, R.drawable.bear, R.drawable.bee, R.drawable.bird,R.drawable.cat, R.drawable.cock, R.drawable.cow, R.drawable.dog, R.drawable.donkey, R.drawable.dove, R.drawable.elephant, R.drawable.frog, R.drawable.goat, R.drawable.goose, R.drawable.horse, R.drawable.lion, R.drawable.monkey, R.drawable.sheep, R.drawable.snake, R.drawable.turkey, R.drawable.wolf, R.drawable.bear, R.drawable.bee, R.drawable.bird,R.drawable.cat, R.drawable.cock, R.drawable.cow, R.drawable.dog, R.drawable.donkey, R.drawable.dove, R.drawable.elephant, R.drawable.frog, R.drawable.goat, R.drawable.goose, R.drawable.horse, R.drawable.lion, R.drawable.monkey, R.drawable.sheep, R.drawable.snake, R.drawable.turkey, R.drawable.wolf, R.drawable.bear, R.drawable.bee, R.drawable.bird,R.drawable.cat, R.drawable.cock, R.drawable.cow, R.drawable.dog, R.drawable.donkey, R.drawable.dove, R.drawable.elephant, R.drawable.frog, R.drawable.goat, R.drawable.goose, R.drawable.horse, R.drawable.lion, R.drawable.monkey, R.drawable.sheep, R.drawable.snake, R.drawable.turkey, R.drawable.wolf, R.drawable.bear, R.drawable.bee, R.drawable.bird,R.drawable.cat, R.drawable.cock, R.drawable.cow, R.drawable.dog, R.drawable.donkey, R.drawable.dove, R.drawable.elephant, R.drawable.frog, R.drawable.goat, R.drawable.goose, R.drawable.horse, R.drawable.lion, R.drawable.monkey, R.drawable.sheep, R.drawable.snake, R.drawable.turkey, R.drawable.wolf, R.drawable.bear, R.drawable.bee, R.drawable.bird,R.drawable.cat, R.drawable.cock, R.drawable.cow, R.drawable.dog, R.drawable.donkey, R.drawable.dove, R.drawable.elephant, R.drawable.frog, R.drawable.goat, R.drawable.goose, R.drawable.horse, R.drawable.lion, R.drawable.monkey, R.drawable.sheep, R.drawable.snake, R.drawable.turkey, R.drawable.wolf, R.drawable.bear, R.drawable.bee, R.drawable.bird,R.drawable.cat, R.drawable.cock, R.drawable.cow, R.drawable.dog, R.drawable.donkey, R.drawable.dove, R.drawable.elephant, R.drawable.frog, R.drawable.goat, R.drawable.goose, R.drawable.horse, R.drawable.lion, R.drawable.monkey, R.drawable.sheep, R.drawable.snake, R.drawable.turkey, R.drawable.wolf, R.drawable.bear, R.drawable.bee, R.drawable.bird,R.drawable.cat, R.drawable.cock, R.drawable.cow, R.drawable.dog, R.drawable.donkey, R.drawable.dove, R.drawable.elephant, R.drawable.frog, R.drawable.goat, R.drawable.goose, R.drawable.horse, R.drawable.lion, R.drawable.monkey, R.drawable.sheep, R.drawable.snake, R.drawable.turkey, R.drawable.wolf, R.drawable.bear, R.drawable.bee, R.drawable.bird,R.drawable.cat, R.drawable.cock, R.drawable.cow, R.drawable.dog, R.drawable.donkey, R.drawable.dove, R.drawable.elephant, R.drawable.frog, R.drawable.goat, R.drawable.goose, R.drawable.horse, R.drawable.lion, R.drawable.monkey, R.drawable.sheep, R.drawable.snake, R.drawable.turkey, R.drawable.wolf, R.drawable.bear, R.drawable.bee, R.drawable.bird,R.drawable.cat, R.drawable.cock, R.drawable.cow, R.drawable.dog, R.drawable.donkey, R.drawable.dove, R.drawable.elephant, R.drawable.frog, R.drawable.goat, R.drawable.goose, R.drawable.horse, R.drawable.lion, R.drawable.monkey, R.drawable.sheep, R.drawable.snake, R.drawable.turkey, R.drawable.wolf, R.drawable.bear, R.drawable.bee, R.drawable.bird,R.drawable.cat, R.drawable.cock, R.drawable.cow, R.drawable.dog, R.drawable.donkey, R.drawable.dove, R.drawable.elephant, R.drawable.frog, R.drawable.goat, R.drawable.goose, R.drawable.horse, R.drawable.lion, R.drawable.monkey, R.drawable.sheep, R.drawable.snake, R.drawable.turkey, R.drawable.wolf, R.drawable.bear, R.drawable.bee, R.drawable.bird,R.drawable.cat, R.drawable.cock, R.drawable.cow, R.drawable.dog, R.drawable.donkey, R.drawable.dove, R.drawable.elephant, R.drawable.frog, R.drawable.goat, R.drawable.goose, R.drawable.horse, R.drawable.lion, R.drawable.monkey, R.drawable.sheep, R.drawable.snake, R.drawable.turkey, R.drawable.wolf,
            R.drawable.bear, R.drawable.bee, R.drawable.bird,R.drawable.cat, R.drawable.cock, R.drawable.cow, R.drawable.dog, R.drawable.donkey, R.drawable.dove, R.drawable.elephant, R.drawable.frog, R.drawable.goat, R.drawable.goose, R.drawable.horse, R.drawable.lion, R.drawable.monkey, R.drawable.sheep, R.drawable.snake, R.drawable.turkey, R.drawable.wolf, R.drawable.bear, R.drawable.bee, R.drawable.bird,R.drawable.cat, R.drawable.cock, R.drawable.cow, R.drawable.dog, R.drawable.donkey, R.drawable.dove, R.drawable.elephant, R.drawable.frog, R.drawable.goat, R.drawable.goose, R.drawable.horse, R.drawable.lion, R.drawable.monkey, R.drawable.sheep, R.drawable.snake, R.drawable.turkey, R.drawable.wolf, R.drawable.bear, R.drawable.bee, R.drawable.bird,R.drawable.cat, R.drawable.cock, R.drawable.cow, R.drawable.dog, R.drawable.donkey, R.drawable.dove, R.drawable.elephant, R.drawable.frog, R.drawable.goat, R.drawable.goose, R.drawable.horse, R.drawable.lion, R.drawable.monkey, R.drawable.sheep, R.drawable.snake, R.drawable.turkey, R.drawable.wolf, R.drawable.bear, R.drawable.bee, R.drawable.bird,R.drawable.cat, R.drawable.cock, R.drawable.cow, R.drawable.dog, R.drawable.donkey, R.drawable.dove, R.drawable.elephant, R.drawable.frog, R.drawable.goat, R.drawable.goose, R.drawable.horse, R.drawable.lion, R.drawable.monkey, R.drawable.sheep, R.drawable.snake, R.drawable.turkey, R.drawable.wolf, R.drawable.bear, R.drawable.bee, R.drawable.bird,R.drawable.cat, R.drawable.cock, R.drawable.cow, R.drawable.dog, R.drawable.donkey, R.drawable.dove, R.drawable.elephant, R.drawable.frog, R.drawable.goat, R.drawable.goose, R.drawable.horse, R.drawable.lion, R.drawable.monkey, R.drawable.sheep, R.drawable.snake, R.drawable.turkey, R.drawable.wolf,
    };

    public static final int[] sounds = {
            R.raw.bear, R.raw.bee, R.raw.bird, R.raw.cat, R.raw.cock, R.raw.cow, R.raw.dog, R.raw.donkey, R.raw.dove, R.raw.elephant, R.raw.frog, R.raw.goat, R.raw.goose, R.raw.horse, R.raw.lion, R.raw.monkey, R.raw.sheep, R.raw.snake, R.raw.turkey, R.raw.wolf, R.raw.bear, R.raw.bee, R.raw.bird, R.raw.cat, R.raw.cock, R.raw.cow, R.raw.dog, R.raw.donkey, R.raw.dove, R.raw.elephant, R.raw.frog, R.raw.goat, R.raw.goose, R.raw.horse, R.raw.lion, R.raw.monkey, R.raw.sheep, R.raw.snake, R.raw.turkey, R.raw.wolf, R.raw.bear, R.raw.bee, R.raw.bird, R.raw.cat, R.raw.cock, R.raw.cow, R.raw.dog, R.raw.donkey, R.raw.dove, R.raw.elephant, R.raw.frog, R.raw.goat, R.raw.goose, R.raw.horse, R.raw.lion, R.raw.monkey, R.raw.sheep, R.raw.snake, R.raw.turkey, R.raw.wolf, R.raw.bear, R.raw.bee, R.raw.bird, R.raw.cat, R.raw.cock, R.raw.cow, R.raw.dog, R.raw.donkey, R.raw.dove, R.raw.elephant, R.raw.frog, R.raw.goat, R.raw.goose, R.raw.horse, R.raw.lion, R.raw.monkey, R.raw.sheep, R.raw.snake, R.raw.turkey, R.raw.wolf, R.raw.bear, R.raw.bee, R.raw.bird, R.raw.cat, R.raw.cock, R.raw.cow, R.raw.dog, R.raw.donkey, R.raw.dove, R.raw.elephant, R.raw.frog, R.raw.goat, R.raw.goose, R.raw.horse, R.raw.lion, R.raw.monkey, R.raw.sheep, R.raw.snake, R.raw.turkey, R.raw.wolf, R.raw.bear, R.raw.bee, R.raw.bird, R.raw.cat, R.raw.cock, R.raw.cow, R.raw.dog, R.raw.donkey, R.raw.dove, R.raw.elephant, R.raw.frog, R.raw.goat, R.raw.goose, R.raw.horse, R.raw.lion, R.raw.monkey, R.raw.sheep, R.raw.snake, R.raw.turkey, R.raw.wolf, R.raw.bear, R.raw.bee, R.raw.bird, R.raw.cat, R.raw.cock, R.raw.cow, R.raw.dog, R.raw.donkey, R.raw.dove, R.raw.elephant, R.raw.frog, R.raw.goat, R.raw.goose, R.raw.horse, R.raw.lion, R.raw.monkey, R.raw.sheep, R.raw.snake, R.raw.turkey, R.raw.wolf, R.raw.bear, R.raw.bee, R.raw.bird, R.raw.cat, R.raw.cock, R.raw.cow, R.raw.dog, R.raw.donkey, R.raw.dove, R.raw.elephant, R.raw.frog, R.raw.goat, R.raw.goose, R.raw.horse, R.raw.lion, R.raw.monkey, R.raw.sheep, R.raw.snake, R.raw.turkey, R.raw.wolf, R.raw.bear, R.raw.bee, R.raw.bird, R.raw.cat, R.raw.cock, R.raw.cow, R.raw.dog, R.raw.donkey, R.raw.dove, R.raw.elephant, R.raw.frog, R.raw.goat, R.raw.goose, R.raw.horse, R.raw.lion, R.raw.monkey, R.raw.sheep, R.raw.snake, R.raw.turkey, R.raw.wolf, R.raw.bear, R.raw.bee, R.raw.bird, R.raw.cat, R.raw.cock, R.raw.cow, R.raw.dog, R.raw.donkey, R.raw.dove, R.raw.elephant, R.raw.frog, R.raw.goat, R.raw.goose, R.raw.horse, R.raw.lion, R.raw.monkey, R.raw.sheep, R.raw.snake, R.raw.turkey, R.raw.wolf, R.raw.bear, R.raw.bee, R.raw.bird, R.raw.cat, R.raw.cock, R.raw.cow, R.raw.dog, R.raw.donkey, R.raw.dove, R.raw.elephant, R.raw.frog, R.raw.goat, R.raw.goose, R.raw.horse, R.raw.lion, R.raw.monkey, R.raw.sheep, R.raw.snake, R.raw.turkey, R.raw.wolf,R.raw.bear, R.raw.bee, R.raw.bird, R.raw.cat, R.raw.cock, R.raw.cow, R.raw.dog, R.raw.donkey, R.raw.dove, R.raw.elephant, R.raw.frog, R.raw.goat, R.raw.goose, R.raw.horse, R.raw.lion, R.raw.monkey, R.raw.sheep, R.raw.snake, R.raw.turkey, R.raw.wolf, R.raw.bear, R.raw.bee, R.raw.bird, R.raw.cat, R.raw.cock, R.raw.cow, R.raw.dog, R.raw.donkey, R.raw.dove, R.raw.elephant, R.raw.frog, R.raw.goat, R.raw.goose, R.raw.horse, R.raw.lion, R.raw.monkey, R.raw.sheep, R.raw.snake, R.raw.turkey, R.raw.wolf, R.raw.bear, R.raw.bee, R.raw.bird, R.raw.cat, R.raw.cock, R.raw.cow, R.raw.dog, R.raw.donkey, R.raw.dove, R.raw.elephant, R.raw.frog, R.raw.goat, R.raw.goose, R.raw.horse, R.raw.lion, R.raw.monkey, R.raw.sheep, R.raw.snake, R.raw.turkey, R.raw.wolf, R.raw.bear, R.raw.bee, R.raw.bird, R.raw.cat, R.raw.cock, R.raw.cow, R.raw.dog, R.raw.donkey, R.raw.dove, R.raw.elephant, R.raw.frog, R.raw.goat, R.raw.goose, R.raw.horse, R.raw.lion, R.raw.monkey, R.raw.sheep, R.raw.snake, R.raw.turkey, R.raw.wolf, R.raw.bear, R.raw.bee, R.raw.bird, R.raw.cat, R.raw.cock, R.raw.cow, R.raw.dog, R.raw.donkey, R.raw.dove, R.raw.elephant, R.raw.frog, R.raw.goat, R.raw.goose, R.raw.horse, R.raw.lion, R.raw.monkey, R.raw.sheep, R.raw.snake, R.raw.turkey, R.raw.wolf, R.raw.bear, R.raw.bee, R.raw.bird, R.raw.cat, R.raw.cock, R.raw.cow, R.raw.dog, R.raw.donkey, R.raw.dove, R.raw.elephant, R.raw.frog, R.raw.goat, R.raw.goose, R.raw.horse, R.raw.lion, R.raw.monkey, R.raw.sheep, R.raw.snake, R.raw.turkey, R.raw.wolf, R.raw.bear, R.raw.bee, R.raw.bird, R.raw.cat, R.raw.cock, R.raw.cow, R.raw.dog, R.raw.donkey, R.raw.dove, R.raw.elephant, R.raw.frog, R.raw.goat, R.raw.goose, R.raw.horse, R.raw.lion, R.raw.monkey, R.raw.sheep, R.raw.snake, R.raw.turkey, R.raw.wolf, R.raw.bear, R.raw.bee, R.raw.bird, R.raw.cat, R.raw.cock, R.raw.cow, R.raw.dog, R.raw.donkey, R.raw.dove, R.raw.elephant, R.raw.frog, R.raw.goat, R.raw.goose, R.raw.horse, R.raw.lion, R.raw.monkey, R.raw.sheep, R.raw.snake, R.raw.turkey, R.raw.wolf, R.raw.bear, R.raw.bee, R.raw.bird, R.raw.cat, R.raw.cock, R.raw.cow, R.raw.dog, R.raw.donkey, R.raw.dove, R.raw.elephant, R.raw.frog, R.raw.goat, R.raw.goose, R.raw.horse, R.raw.lion, R.raw.monkey, R.raw.sheep, R.raw.snake, R.raw.turkey, R.raw.wolf, R.raw.bear, R.raw.bee, R.raw.bird, R.raw.cat, R.raw.cock, R.raw.cow, R.raw.dog, R.raw.donkey, R.raw.dove, R.raw.elephant, R.raw.frog, R.raw.goat, R.raw.goose, R.raw.horse, R.raw.lion, R.raw.monkey, R.raw.sheep, R.raw.snake, R.raw.turkey, R.raw.wolf, R.raw.bear, R.raw.bee, R.raw.bird, R.raw.cat, R.raw.cock, R.raw.cow, R.raw.dog, R.raw.donkey, R.raw.dove, R.raw.elephant, R.raw.frog, R.raw.goat, R.raw.goose, R.raw.horse, R.raw.lion, R.raw.monkey, R.raw.sheep, R.raw.snake, R.raw.turkey, R.raw.wolf, R.raw.bear, R.raw.bee, R.raw.bird, R.raw.cat, R.raw.cock, R.raw.cow, R.raw.dog, R.raw.donkey, R.raw.dove, R.raw.elephant, R.raw.frog, R.raw.goat, R.raw.goose, R.raw.horse, R.raw.lion, R.raw.monkey, R.raw.sheep, R.raw.snake, R.raw.turkey, R.raw.wolf, R.raw.bear, R.raw.bee, R.raw.bird, R.raw.cat, R.raw.cock, R.raw.cow, R.raw.dog, R.raw.donkey, R.raw.dove, R.raw.elephant, R.raw.frog, R.raw.goat, R.raw.goose, R.raw.horse, R.raw.lion, R.raw.monkey, R.raw.sheep, R.raw.snake, R.raw.turkey, R.raw.wolf,
            R.raw.bear, R.raw.bee, R.raw.bird, R.raw.cat, R.raw.cock, R.raw.cow, R.raw.dog, R.raw.donkey, R.raw.dove, R.raw.elephant, R.raw.frog, R.raw.goat, R.raw.goose, R.raw.horse, R.raw.lion, R.raw.monkey, R.raw.sheep, R.raw.snake, R.raw.turkey, R.raw.wolf, R.raw.bear, R.raw.bee, R.raw.bird, R.raw.cat, R.raw.cock, R.raw.cow, R.raw.dog, R.raw.donkey, R.raw.dove, R.raw.elephant, R.raw.frog, R.raw.goat, R.raw.goose, R.raw.horse, R.raw.lion, R.raw.monkey, R.raw.sheep, R.raw.snake, R.raw.turkey, R.raw.wolf, R.raw.bear, R.raw.bee, R.raw.bird, R.raw.cat, R.raw.cock, R.raw.cow, R.raw.dog, R.raw.donkey, R.raw.dove, R.raw.elephant, R.raw.frog, R.raw.goat, R.raw.goose, R.raw.horse, R.raw.lion, R.raw.monkey, R.raw.sheep, R.raw.snake, R.raw.turkey, R.raw.wolf, R.raw.bear, R.raw.bee, R.raw.bird, R.raw.cat, R.raw.cock, R.raw.cow, R.raw.dog, R.raw.donkey, R.raw.dove, R.raw.elephant, R.raw.frog, R.raw.goat, R.raw.goose, R.raw.horse, R.raw.lion, R.raw.monkey, R.raw.sheep, R.raw.snake, R.raw.turkey, R.raw.wolf, R.raw.bear, R.raw.bee, R.raw.bird, R.raw.cat, R.raw.cock, R.raw.cow, R.raw.dog, R.raw.donkey, R.raw.dove, R.raw.elephant, R.raw.frog, R.raw.goat, R.raw.goose, R.raw.horse, R.raw.lion, R.raw.monkey, R.raw.sheep, R.raw.snake, R.raw.turkey, R.raw.wolf

    };

    private int[] randomImages;
    private int askedSound;
    private int askedSoundIndex;
    private Random random = new Random();


    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        levelText=findViewById(R.id.levelText);
        bestScoreText= findViewById(R.id.bestscoreText);
        timeText = findViewById(R.id.timeText);
        scoreText =findViewById(R.id.scoreText);
        button = findViewById(R.id.button);

        //splash screen
        Intent intent = getIntent();
        talk();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }

        });

        init();

        setLevelOperation();
        startGame();
        findLevelTime();

    }

    public void findBestScore(){
        if(score>BestScore){
            BestScore=score;
            bestScoreText.setText("BEST SCORE: " + BestScore);
        }
    }
    public void findLevelTime(){
        if(level==1 ){
            if(score ==0){
                msec=22000;
                startTimeCounter(msec);
                countDownTimer.start();
            }
            if(score==1){
                msec=22000;
                startTimeCounter(msec);
                countDownTimer.start();
            }
            if(score==2){
                msec=22000;
                startTimeCounter(msec);
                countDownTimer.start();
            }
        if(level == 2){
            if(score==3){
                msec=20000;
                startTimeCounter(msec);
                countDownTimer.start();
            }
            else if(score==4){
                msec=20000;
                startTimeCounter(msec);
                countDownTimer.start();
            }
            else if(score==5){
                msec=20000;
                startTimeCounter(msec);
                countDownTimer.start();
            }
        if(level==3){
            if(score == 6){
                msec=18000;
                startTimeCounter(msec);
                countDownTimer.start();
            }
            if(score == 7){
                msec=18000;
                startTimeCounter(msec);
                countDownTimer.start();
            }
            if(score == 8){
                msec=18000;
                startTimeCounter(msec);
                countDownTimer.start();
            }
        if(level == 4){
            if(score == 9){
                msec=16000;
                startTimeCounter(msec);
                countDownTimer.start();
            }
            if(score == 10){
                msec=16000;
                startTimeCounter(msec);
                countDownTimer.start();
            }
            if(score == 11){
                msec=16000;
                startTimeCounter(msec);
                countDownTimer.start();
            }
        if(level == 5){
            if(score == 12){
                msec=16000;
                startTimeCounter(msec);
                countDownTimer.start();
            }
            if(score == 13){
                msec=16000;
                startTimeCounter(msec);
                countDownTimer.start();
            }
            if(score == 14){
                msec=16000;
                startTimeCounter(msec);
                countDownTimer.start();
            }
        } } } } }
    }

    private void talk(){
        //TEXT2SPEECH
        tt = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = tt.setLanguage(Locale.ENGLISH);
                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast.makeText(getApplicationContext(), "This language is not supported", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        speak("IF YOU DON'T SEE THE RIGHT ANIMAL IMAGE CLICK CHANGE ANIMAL ");
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Initialization failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
    }

    void speak(String s){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Bundle bundle = new Bundle();
            bundle.putInt(TextToSpeech.Engine.KEY_PARAM_STREAM, AudioManager.STREAM_MUSIC);
            tt.speak(s, TextToSpeech.QUEUE_ADD, bundle, null);
        } else {
            //old api
            HashMap<String, String> param = new HashMap<>();
            param.put(TextToSpeech.Engine.KEY_PARAM_STREAM, String.valueOf(AudioManager.STREAM_MUSIC));
            tt.speak(s, TextToSpeech.QUEUE_ADD, param);
        }
    }


    public void writeBestScore() {  // write the best score to txt file(bestScore.txt)
        // add-write text into file
        try {
            FileOutputStream fileout=openFileOutput("bestScore.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write("" + BestScore);
            outputWriter.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readBestScore() {  // read the best score from txt file(bestScore.txt)
        //reading text from file
        try {
            FileInputStream fileIn=openFileInput("bestScore.txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn);

            char[] inputBuffer= new char[2];    //Max Best Score is 50.
            String s="";
            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                // char to string conversion
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }

            BestScore = Integer.parseInt(s);

            InputRead.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init(){
        gridView = findViewById(R.id.grid_view);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, final long id) {

                if(images[askedSoundIndex] == randomImages[position]){ //RESİM SES EŞLEŞİRSE

                    if(score == 0){ //level 1 deyim
                        //correct
                        Log.e("ANSWER","CORRECT");
                        score++;
                        scoreText.setText("Score : " + String.valueOf(score));
                        speak("WELL DONE ITS TRUE");
                        findBestScore();
                        startGame();
                        //time
                        msec=22000;
                        startTimeCounter(msec);
                        countDownTimer.start();
                    }
                     else if (score == 1) {
                        score++;
                        scoreText.setText("Score : " + String.valueOf(score));
                        speak("WELL DONE ITS TRUE");
                        findBestScore();
                        startGame();
                        //time
                        msec=22000;
                        startTimeCounter(msec);
                        countDownTimer.start();
                    }
                    else if(score == 2){
                        score++;
                        scoreText.setText("Score : " + String.valueOf(score));
                        speak("WELL DONE ITS TRUE");
                        findBestScore();
                        startGame();
                        //time
                        msec=22000;
                        startTimeCounter(msec);
                        countDownTimer.start();

                    }
                    if(score == 3){                     //LEVEL 2
                        level++;
                        levelText.setText("Level : " + String.valueOf(level));

                        scoreText.setText("Score : " + String.valueOf(score));
                        setLevelOperation();
                        startGame();
                        findBestScore();
                        //time
                        msec=20000;
                        startTimeCounter(msec);
                        countDownTimer.start();


                        levelText.setTextColor(getResources().getColor(R.color.colorAccent));
                        bestScoreText.setTextColor(getResources().getColor(R.color.colorAccent));
                        scoreText.setTextColor(getResources().getColor(R.color.colorAccent));
                        timeText.setTextColor(getResources().getColor(R.color.colorAccent));
                        button.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                        score++;
                    }
                    else if(score == 4){
                       //score++;
                        scoreText.setText("Score : " + String.valueOf(score));
                        speak("WELL DONE ITS TRUE");
                        findBestScore();
                        startGame();
                        //time
                        msec=20000;
                        startTimeCounter(msec);
                        countDownTimer.start();

                        levelText.setTextColor(getResources().getColor(R.color.colorAccent));
                        bestScoreText.setTextColor(getResources().getColor(R.color.colorAccent));
                        scoreText.setTextColor(getResources().getColor(R.color.colorAccent));
                        timeText.setTextColor(getResources().getColor(R.color.colorAccent));
                        button.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                        score++;
                    }
                    else if(score == 5){
                        //score++;
                        scoreText.setText("Score : " + String.valueOf(score));
                        speak("WELL DONE ITS TRUE");
                        findBestScore();
                        startGame();
                        //time
                        msec=20000;
                        startTimeCounter(msec);
                        countDownTimer.start();

                        levelText.setTextColor(getResources().getColor(R.color.colorAccent));
                        bestScoreText.setTextColor(getResources().getColor(R.color.colorAccent));
                        scoreText.setTextColor(getResources().getColor(R.color.colorAccent));
                        timeText.setTextColor(getResources().getColor(R.color.colorAccent));
                        button.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                        score++;
                    }
                   else if(score == 6){
                        level=3;
                        levelText.setText("Level : " + String.valueOf(level));

                        scoreText.setText("Score : " + String.valueOf(score));
                        setLevelOperation();
                        startGame();
                        findBestScore();
                        //time
                        msec=18000;
                        startTimeCounter(msec);
                        countDownTimer.start();

                        levelText.setTextColor(getResources().getColor(R.color.colorGreen));
                        bestScoreText.setTextColor(getResources().getColor(R.color.colorGreen));
                        scoreText.setTextColor(getResources().getColor(R.color.colorGreen));
                        timeText.setTextColor(getResources().getColor(R.color.colorGreen));
                        button.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                        score++;
                    }
                    else if(score == 7){

                        speak("WELL DONE ITS TRUE");
                        scoreText.setText("Score : " + String.valueOf(score));
                        findBestScore();
                        startGame();
                        //time
                        msec=18000;
                        startTimeCounter(msec);
                        countDownTimer.start();

                        levelText.setTextColor(getResources().getColor(R.color.colorGreen));
                        bestScoreText.setTextColor(getResources().getColor(R.color.colorGreen));
                        scoreText.setTextColor(getResources().getColor(R.color.colorGreen));
                        timeText.setTextColor(getResources().getColor(R.color.colorGreen));
                        button.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                        score++;
                    }
                    else if(score == 8){

                        speak("WELL DONE ITS TRUE");
                        scoreText.setText("Score : " + String.valueOf(score));
                        findBestScore();
                        startGame();
                        //time
                        msec=18000;
                        startTimeCounter(msec);
                        countDownTimer.start();

                        levelText.setTextColor(getResources().getColor(R.color.colorGreen));
                        bestScoreText.setTextColor(getResources().getColor(R.color.colorGreen));
                        scoreText.setTextColor(getResources().getColor(R.color.colorGreen));
                        timeText.setTextColor(getResources().getColor(R.color.colorGreen));
                        button.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                        score++;
                    }
                    else if(score == 9){  //LEVEL 4
                        level=4;
                        levelText.setText("Level : " + String.valueOf(level));
                        scoreText.setText("Score : " + String.valueOf(score));
                        setLevelOperation();
                        startGame();
                        findBestScore();
                        //time
                        msec=16000;
                        startTimeCounter(msec);
                        countDownTimer.start();

                        levelText.setTextColor(getResources().getColor(R.color.colorDarkRed));
                        bestScoreText.setTextColor(getResources().getColor(R.color.colorDarkRed));
                        scoreText.setTextColor(getResources().getColor(R.color.colorDarkRed));
                        timeText.setTextColor(getResources().getColor(R.color.colorDarkRed));
                        button.setBackgroundColor(getResources().getColor(R.color.colorDarkRed));
                        score++;
                    }
                    else if(score == 10){

                        speak("WELL DONE ITS TRUE");
                        scoreText.setText("Score : " + String.valueOf(score));
                        findBestScore();
                        startGame();
                        //time
                        msec=16000;
                        startTimeCounter(msec);
                        countDownTimer.start();

                        levelText.setTextColor(getResources().getColor(R.color.colorDarkRed));
                        bestScoreText.setTextColor(getResources().getColor(R.color.colorDarkRed));
                        scoreText.setTextColor(getResources().getColor(R.color.colorDarkRed));
                        timeText.setTextColor(getResources().getColor(R.color.colorDarkRed));
                        button.setBackgroundColor(getResources().getColor(R.color.colorDarkRed));
                        score++;
                    }
                    else if(score == 11){
                        scoreText.setText("Score : " + String.valueOf(score));
                        speak("WELL DONE ITS TRUE");
                        findBestScore();
                        startGame();
                        //time
                        msec=16000;
                        startTimeCounter(msec);
                        countDownTimer.start();

                        levelText.setTextColor(getResources().getColor(R.color.colorDarkRed));
                        bestScoreText.setTextColor(getResources().getColor(R.color.colorDarkRed));
                        scoreText.setTextColor(getResources().getColor(R.color.colorDarkRed));
                        timeText.setTextColor(getResources().getColor(R.color.colorDarkRed));
                        button.setBackgroundColor(getResources().getColor(R.color.colorDarkRed));
                        score++;
                    }
                    else if(score == 12){   //LEVEL 5
                        level=5;
                        levelText.setText("Level : " + String.valueOf(level));

                        scoreText.setText("Score : " + String.valueOf(score));
                        setLevelOperation();
                        startGame();
                        findBestScore();
                        //time
                        msec=16000;
                        startTimeCounter(msec);
                        countDownTimer.start();

                        levelText.setTextColor(getResources().getColor(R.color.colorDPurple));
                        bestScoreText.setTextColor(getResources().getColor(R.color.colorDPurple));
                        scoreText.setTextColor(getResources().getColor(R.color.colorDPurple));
                        timeText.setTextColor(getResources().getColor(R.color.colorDPurple));
                        button.setBackgroundColor(getResources().getColor(R.color.colorDarkPurple));
                        score++;
                    }
                    else if(score == 13){
                        //score++;
                        scoreText.setText("Score : " + String.valueOf(score));
                        speak("WELL DONE ITS TRUE");
                        findBestScore();
                        startGame();
                        //time
                        msec=16000;
                        startTimeCounter(msec);
                        countDownTimer.start();


                        levelText.setTextColor(getResources().getColor(R.color.colorDPurple));
                        bestScoreText.setTextColor(getResources().getColor(R.color.colorDPurple));
                        scoreText.setTextColor(getResources().getColor(R.color.colorDPurple));
                        timeText.setTextColor(getResources().getColor(R.color.colorDPurple));
                        button.setBackgroundColor(getResources().getColor(R.color.colorDarkPurple));
                        score++;
                    }
                    else if(score == 14){
                        //score++;
                        scoreText.setText("Score : " + String.valueOf(score));
                        speak("WELL DONE ITS TRUE");
                        findBestScore();
                        bestScoreText.setText("BEST POINT : 14\nGOOD JOB!!!");
                        startGame();
                        //time
                        msec=16000;
                        startTimeCounter(msec);
                        countDownTimer.start();


                        levelText.setTextColor(getResources().getColor(R.color.colorDPurple));
                        bestScoreText.setTextColor(getResources().getColor(R.color.colorDPurple));
                        scoreText.setTextColor(getResources().getColor(R.color.colorDPurple));
                        timeText.setTextColor(getResources().getColor(R.color.colorDPurple));
                        button.setBackgroundColor(getResources().getColor(R.color.colorDarkPurple));


                        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                        alert.setTitle("Do you want to play again?");
                        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                init();
                                level =1;
                                score=0;

                                setLevelOperation();
                                startGame();
                                findBestScore();
                                findLevelTime();
                            }
                        });
                        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "GAME OVER!! EXIT THE GAME", Toast.LENGTH_LONG).show();
                                finishAffinity();

                            }
                        });
                        alert.show();
                    }

                }else{ //RESİM SES EŞLEŞMEZSE
                    //incorrect
                    Log.e("ANSWER","INCORRECT");
                    if(level == 1){
                        levelText.setText("Level : " + String.valueOf(level));
                        score=0;
                        scoreText.setText("Score : " + String.valueOf(score));
                        speak("WRONG ANSWER, TURN TO LEVEL 1 BEGINNING");
                        findBestScore();
                        setLevelOperation();
                        startGame();
                        msec=22000;
                        startTimeCounter(msec);
                        countDownTimer.start();
                    }
                    if(level == 2){
                        level=1;
                        levelText.setText("Level : " + String.valueOf(level));
                        score=0;
                        scoreText.setText("Score : " + String.valueOf(score));
                        levelText.setTextColor(getResources().getColor(R.color.colorDarkBlue));
                        bestScoreText.setTextColor(getResources().getColor(R.color.colorDarkBlue));
                        scoreText.setTextColor(getResources().getColor(R.color.colorDarkBlue));
                        timeText.setTextColor(getResources().getColor(R.color.colorDarkBlue));
                        button.setBackgroundColor(getResources().getColor(R.color.colorDarkBlue));

                        speak("WRONG ANSWER, FINISHED THE CURRENT LEVEL, TURN TO LEVEL 1 BEGINNING");
                        init();
                        setLevelOperation();
                        startGame();
                        findBestScore();
                        msec=22000;
                        startTimeCounter(msec);
                        countDownTimer.start();
                    }
                    if(level == 3){
                        level=2;
                        levelText.setText("Level : " + String.valueOf(level));
                        score=3;
                        scoreText.setText("Score : " + String.valueOf(score));

                        levelText.setTextColor(getResources().getColor(R.color.colorAccent));
                        bestScoreText.setTextColor(getResources().getColor(R.color.colorAccent));
                        scoreText.setTextColor(getResources().getColor(R.color.colorAccent));
                        timeText.setTextColor(getResources().getColor(R.color.colorAccent));
                        button.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                        findBestScore();
                        speak("WRONG ANSWER, TURN TO LEVEL 2 BEGINNING");
                        setLevelOperation();
                        startGame();
                        msec=20000;
                        startTimeCounter(msec);
                        countDownTimer.start();

                    }if(level == 4){
                        level=3;
                        levelText.setText("Level : " + String.valueOf(level));
                        score=6;
                        scoreText.setText("Score : " + String.valueOf(score));
                        levelText.setTextColor(getResources().getColor(R.color.colorGreen));
                        bestScoreText.setTextColor(getResources().getColor(R.color.colorGreen));
                        scoreText.setTextColor(getResources().getColor(R.color.colorGreen));
                        timeText.setTextColor(getResources().getColor(R.color.colorGreen));
                        button.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                        findBestScore();
                        speak("WRONG ANSWER, TURN TO LEVEL 3 BEGINNING");
                        setLevelOperation();
                        startGame();
                        msec=18000;
                        startTimeCounter(msec);
                        countDownTimer.start();
                    }if(level == 5){
                        level=4;
                        levelText.setText("Level : " + String.valueOf(level));
                        score=9;
                        scoreText.setText("Score : " + String.valueOf(score));
                        levelText.setTextColor(getResources().getColor(R.color.colorDarkRed));
                        bestScoreText.setTextColor(getResources().getColor(R.color.colorDarkRed));
                        scoreText.setTextColor(getResources().getColor(R.color.colorDarkRed));
                        timeText.setTextColor(getResources().getColor(R.color.colorDarkRed));
                        button.setBackgroundColor(getResources().getColor(R.color.colorDarkRed));
                        findBestScore();
                        speak("WRONG ANSWER, TURN TO LEVEL 4 BEGINNING");
                        setLevelOperation();
                        startGame();
                        msec=16000;
                        startTimeCounter(msec);
                        countDownTimer.start();
                    }
                }
            }
        });
    }


    private void setLevelOperation(){
        if(level == 1){
            randomImages = new int[2];
        }else if(level == 2){
            randomImages = new int[3];
        }else if(level == 3){
            randomImages = new int[4];
        }else if(level == 4){
            randomImages = new int[6];
        }else if(level == 5){
            randomImages = new int[8];
        }
    }

    private void startGame(){
        askedSoundIndex = random.nextInt(sounds.length);
        askedSound = sounds[askedSoundIndex];
        Sound.playSound(this,askedSound);
        for(int i=0;i<randomImages.length;i++){
            randomImages[i] = images[random.nextInt(images.length)];
        }

        gridView.setAdapter(new ImageGridAdapter(this,randomImages));
    }







    public void startTimeCounter (long milisec) {       //Time counter for each level
        if(countDownTimer!=null){
            countDownTimer.cancel();
        }
        countDownTimer=new CountDownTimer(milisec,1000){
            @Override
            public void onTick(long millisUntilFinished){
                timeText.setText("TIME: "+String.valueOf(millisUntilFinished/1000));
                time = (int) (millisUntilFinished/1000);
            }

            public void onFinish(){
                //timeText.setText("TIME FINISHED!!");
                if(score==3){
                    msec=25000;
                    startTimeCounter(msec);
                    countDownTimer.start();
                }
                else if(score==4){
                    msec=25000;
                    startTimeCounter(msec);
                    countDownTimer.start();
                }
                else if(score==5){
                    msec=25000;
                    startTimeCounter(msec);
                    countDownTimer.start();
                }
                if(score==6){
                    msec=25000;
                    startTimeCounter(msec);
                    countDownTimer.start();
                }
                else if(score==7){
                    msec=25000;
                    startTimeCounter(msec);
                    countDownTimer.start();
                }
                else if(score==8){
                    msec=25000;
                    startTimeCounter(msec);
                    countDownTimer.start();
                }
                if(score==9){
                    msec=25000;
                    startTimeCounter(msec);
                    countDownTimer.start();
                }
                else if(score==10){
                    msec=25000;
                    startTimeCounter(msec);
                    countDownTimer.start();
                }
                else if(score==11){
                    msec=25000;
                    startTimeCounter(msec);
                    countDownTimer.start();
                }
                if(score==12){
                    msec=25000;
                    startTimeCounter(msec);
                    countDownTimer.start();
                }
                else if(score==13){
                    msec=25000;
                    startTimeCounter(msec);
                    countDownTimer.start();
                }
                else if(score==14){
                    msec=25000;
                    startTimeCounter(msec);
                    countDownTimer.start();
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart Game?");
                alert.setMessage("Are you sure to restart game?");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        //restart
//                        Intent intent = getIntent();
//                        startActivity(intent); //tekrar başlattım
//                        finish(); //güncel aktivite ful bitti
                        setLevelOperation();
                        startGame();
                        findBestScore();
                        findLevelTime();
                        Log.d("FFF", "CODE YES");

                    }
                });

                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Game Over!! If you want to play again start the game?", Toast.LENGTH_LONG).show();
                        finishAffinity();
                    }
                });
                alert.show();


            }
        }.start();
    }




}
