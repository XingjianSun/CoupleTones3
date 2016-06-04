package com.example.cody.coupletones;

import android.content.SharedPreferences;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.cody.coupletones.tones.Tones;
import com.example.cody.coupletones.tones.chimeTone;
import com.example.cody.coupletones.tones.defaultTone;
import com.example.cody.coupletones.tones.hornTone;
import com.example.cody.coupletones.tones.inceptionTone;
import com.example.cody.coupletones.tones.relaxTone;
import com.example.cody.coupletones.tones.sonaTone;
import com.example.cody.coupletones.tones.successTone;
import com.example.cody.coupletones.tones.whistleTone;

import java.util.HashMap;

/**
 * Created by allenF on 5/30/16.
 */
public class ToneManager extends AppCompatActivity {
    public static HashMap myTones;
   // private SharedPreferences sharedPreferences, SharedPreferences;
   // android.content.SharedPreferences.Editor editor;
    public ToneManager() {
        myTones = new HashMap();
    }

    public void addTone(String location, String tone){
        myTones.put(location,tone);
    }

    public Tones getTone(String location, Vibrator vibrate){
        String toneName = (String) myTones.get(location);
        Log.v("Success", toneName);
        Tones tone = new defaultTone(vibrate);
        switch (toneName) {
            case "default":
                tone = new defaultTone(vibrate);
                break;
            case "relax":
                tone = new relaxTone(vibrate);
                break;
            case "inception":
                tone = new inceptionTone(vibrate);
                break;
            case "sona":
                tone = new sonaTone(vibrate);
                break;
            case "whistle":
                tone = new whistleTone(vibrate);
                break;
            case "horn":
                tone = new hornTone(vibrate);
                break;
            case "success":
                tone = new successTone(vibrate);
                break;
            case "chime":
                tone = new chimeTone(vibrate);
                break;
            default:
                break;
        }
        return tone;
    }

    public void changeTone(String location, String tone) {
        if (myTones.containsKey(location)) {
            myTones.put(location, tone);
        }
        else {
            Toast.makeText(getBaseContext(), "No Such Location", Toast.LENGTH_LONG).show();
        }
    }

    public HashMap<String,String> getMyTones(){
        return myTones;
    }
}


