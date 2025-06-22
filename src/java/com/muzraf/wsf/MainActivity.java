package com.muzraf.wsf;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.os.CountDownTimer;

import android.media.MediaPlayer;

import java.text.NumberFormat;
import java.text.DecimalFormat;

public class MainActivity extends Activity implements View.OnClickListener{
    private TextView t1;
    private TextView t2;
    private TextView t3;
    private LinearLayout ll;
    private CountDownTimer timer;
    private long rmsuf = 30*60*1000;
    private long msme = 180*1000;
    private Boolean paused = true;
    private NumberFormat f = new DecimalFormat("00");
    private MediaPlayer mp;
    int a = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = findViewById(R.id.text_time);
        t2 = findViewById(R.id.text_time_value);
        t3 = findViewById(R.id.text_paused);
        ll = findViewById(R.id.llayout);

        t1.setText("Time");
        ll.setOnClickListener(this);

        t2.setText("00:30:00");
        t3.setText("Tap to Start");

        mp = MediaPlayer.create(this, R.raw.slash);
       /** mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.reset();
                mp.release();
                mp = null;
            }
        }); **/
    }
 
    @Override
    public void onClick(View v) {
        if (paused == true) {
            paused = false;
        } else {
            paused = true;
        }

        if (paused == true) {
            timer.cancel();
            t3.setText("Paused");
        } else {
            if ((rmsuf/msme)  % 2 == 0) {
                t3.setText("walk slow");
            } else {
                t3.setText("walk fast");
            }
            //t3.setText("");
            mp.start();
            timer = new CountDownTimer(rmsuf, 1000) {
                @Override
                public void onTick(long msuf) {
                    rmsuf = msuf;
                    long hour = (msuf/3600000)%24;
                    long minute = (msuf/60000)%60;
                    long second = (msuf/1000)%60;
                    String s;
                    if (msuf % msme <= 1000) {
                        if ((msuf/msme) % 2 == 0) {
                            s = "walk slow";
                        } else {
                            s = "walk fast";
                        }
                        t3.setText(s);
                        mp.start();
                    } else {
                        long k = msuf % msme;
                        //t1.setText(Long.toString(k));
                    }
                    t2.setText(f.format(hour) + ":" + f.format(minute) + ":" + f.format(second));
                }
                @Override
                public void onFinish() {
                    t2.setText("00:00:00");
                    rmsuf = 30*60*1000;
                    t3.setText("Tap to Start again");
                }
            };
            timer.start();
        }

    }

    @Override
    public void onDestroy() {
        mp.reset();
        mp.release();
    }
}
