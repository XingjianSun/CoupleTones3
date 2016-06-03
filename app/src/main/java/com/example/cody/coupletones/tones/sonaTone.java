package com.example.cody.coupletones.tones;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Vibrator;

import com.example.cody.coupletones.R;
import com.example.cody.coupletones.Tone;

/**
 * Created by Omnit on 6/3/2016.
 */
public class sonaTone implements Tone{
    MediaPlayer player;
    Vibrator vibrator;
    long[] vibrationPatterns;

    public sonaTone(Vibrator vibrator){
        vibrationPatterns = new long[] {0, 100, 400, 100};
        this.vibrator = vibrator;
    }

    @Override
    public void play(Context context) {
        player = MediaPlayer.create(context, R.raw.sona);
        player.start();
        vibrator.vibrate(vibrationPatterns, -1);
    }
}
