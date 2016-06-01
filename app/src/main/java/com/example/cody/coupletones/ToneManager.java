package com.example.cody.coupletones;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

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
       SharedPreferences  sharedPreferences = getSharedPreferences("User info", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(location,tone);
        editor.apply();
    }

    public void changeTone(String location, String tone) {
        if (myTones.containsKey(location)) {
            myTones.put(location, tone);
        }
        else {
            Toast.makeText(getBaseContext(), "no such location", Toast.LENGTH_LONG).show();
        }
    }

    public HashMap<String,String> getMyTones(){
        return myTones;
    }
}


