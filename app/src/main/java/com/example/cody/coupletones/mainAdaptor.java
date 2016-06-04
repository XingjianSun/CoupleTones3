package com.example.cody.coupletones;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by allenF on 6/3/16.
 */
public class mainAdaptor {

    private ArrayList<String> FavoriteLocations;

    public mainAdaptor(){
        FavoriteLocations = new ArrayList<>();
    }

    private boolean soundNotification,vibrateNotification;
    public boolean notification(){
        FavoriteLocations.add("random");
        return true;
    }
    public void turnSoundOff(){soundNotification = false;}
    public void turnSoundOn(){soundNotification=true;}
    public void turnVibrationOff(){vibrateNotification = false;}
    public void turnVibrationOn(){vibrateNotification=true;}
    public boolean getSoundNotification(){return soundNotification;}
    public boolean getVibrateNotification(){return vibrateNotification;}
    public boolean containsKey(String location){
        return FavoriteLocations.contains(location);
    }
    public List getList(){
        return FavoriteLocations;
    }

}
