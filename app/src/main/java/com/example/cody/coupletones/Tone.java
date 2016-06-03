package com.example.cody.coupletones;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Vibrator;

/**
 * Created by allenF on 6/2/16.
 */
public interface Tone {
    public void play(Context context);
    /*
    private String name;
    private int vibrationPattern;
    MediaPlayer player;
    Vibrator vibrate;
    final long[] once = { 0, 100 };
    final long[] twice = { 0, 100, 400, 100 };
    final long[] thrice = { 0, 100, 400, 100, 400, 100 };


    public Tone() {
        name = "default";
        vibrationPattern = 1;
    }

    public Tone(String toneName, Vibrator vibrator){
        name = toneName;
        vibrate = vibrator;
    }


    public void play(Context context) {
        try {
            switch (name) {
                case "default":
                    player = MediaPlayer.create(context, R.raw.default1);
                    vibrationPattern = 1;
                    break;
                case "relax":
                    player = MediaPlayer.create(context, R.raw.relax);
                    vibrationPattern = 2;
                    break;
                case "inception":
                    player = MediaPlayer.create(context, R.raw.inception);
                    vibrationPattern = 3;
                    break;
                case "sona":
                    player = MediaPlayer.create(context, R.raw.sona);
                    vibrationPattern = 1;
                    break;
                case "whistle":
                    player = MediaPlayer.create(context, R.raw.whistle);
                    vibrationPattern = 2;
                    break;
                case "horn":
                    player = MediaPlayer.create(context, R.raw.horn);
                    vibrationPattern = 3;
                    break;
                case "success":
                    player = MediaPlayer.create(context, R.raw.success);
                    vibrationPattern = 1;
                    break;
                case "guitar":
                    player = MediaPlayer.create(context, R.raw.guitar);
                    vibrationPattern = 2;
                    break;
                case "bells":
                    player = MediaPlayer.create(context, R.raw.bells);
                    vibrationPattern = 3;
                    break;
                case "chime":
                    player = MediaPlayer.create(context, R.raw.chime);
                    vibrationPattern = 1;
                    break;
                default:
                    player = MediaPlayer.create(context, R.raw.default1);
                    vibrationPattern = 1;
                    break;

            }
            switch (vibrationPattern) {
                case 1:
                    vibrate.vibrate(once, -1);
                    break;
                case 2:
                    vibrate.vibrate(twice, -1);
                    break;
                case 3:
                    vibrate.vibrate(thrice, -1);
                    break;
                default:
                    break;

            }
            player.start();
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }


*/

}


