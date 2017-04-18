package com.example.brian.magicapp;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class piano extends AppCompatActivity {


    Button buttonA, buttonB, buttonC, buttonD, buttonE;

    private SoundPool soundPool;
    private int sound_buttonA, sound_buttonB, sound_buttonC, sound_buttonD, sound_buttonE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piano);

        Button button;
        buttonA = (Button) findViewById(R.id.buttonA);
        buttonB = (Button) findViewById(R.id.buttonB);
        buttonC = (Button) findViewById(R.id.buttonC);
        buttonD = (Button) findViewById(R.id.buttonD);
        buttonE = (Button) findViewById(R.id.buttonE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            soundPool = new SoundPool.Builder().setMaxStreams(5).build();
        } else {
            soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }

        sound_buttonA = soundPool.load(this, R.raw.pianoa, 1);
        sound_buttonB = soundPool.load(this, R.raw.pianob, 1);
        sound_buttonC = soundPool.load(this, R.raw.pianoc, 1);
        sound_buttonD = soundPool.load(this, R.raw.pianod, 1);
        sound_buttonE = soundPool.load(this, R.raw.pianoe, 1);


        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View view) {
                soundPool.play(sound_buttonA, 1, 1, 0, 0, 1);
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(sound_buttonB, 1, 1, 0, 0, 1);
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(sound_buttonC, 1, 1, 0, 0, 1);
            }
        });

        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(sound_buttonD, 1, 1, 0, 0, 1);
            }
        });

        buttonE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(sound_buttonE, 1, 1, 0, 0, 1);
            }
        });
    }
}
