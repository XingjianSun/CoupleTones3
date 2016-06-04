package com.example.cody.coupletones.tones;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Vibrator;

import com.example.cody.coupletones.R;
import com.example.cody.coupletones.Tone;

/**
 * Created by Omnit on 6/3/2016.
 */


/**
 * Created by allenF on 6/3/16.
 */
public class sonaTone implements Tones {

    MediaPlayer player;
    Vibrator vibrate;
    long[]vibrationPattern;

    public sonaTone(Vibrator vibrator){
        vibrationPattern = new long[]{ 0, 100, 400, 100 };
        vibrate = vibrator;
    }
    @Override
    public void play(Context context) {

        player = MediaPlayer.create(context, R.raw.sona);
        player.start();
        vibrate.vibrate(vibrationPattern,-1);
    }
}
