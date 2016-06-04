package com.example.cody.coupletones.tones;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Vibrator;

import com.example.cody.coupletones.R;

/**
 * Created by allenF on 6/3/16.
 */
public class successTone implements Tones {

    MediaPlayer player;
    Vibrator vibrate;
    long[]vibrationPattern;

    public successTone(Vibrator vibrator){

        vibrationPattern = new long[]{ 0, 100, 400, 100 };
        vibrate = vibrator;
    }
    @Override
    public void play(Context context) {

        player = MediaPlayer.create(context, R.raw.success);
        player.start();
        vibrate.vibrate(vibrationPattern,-1);
    }
}
