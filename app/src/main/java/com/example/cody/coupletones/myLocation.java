package com.example.cody.coupletones;


/**
 * Created by Utkrisht on 6/3/2016.
 */
public class myLocation {
    private String locName;
    private String assignedTone;

    public myLocation() {}

    public myLocation(String locName)
    {
        this.locName = locName;
        assignedTone = "default";
    }

    public String getName(){
        return locName;
    }
    public String getAssignedTone(){
        return assignedTone;
    }
    public void assignTone(String tone){
        assignedTone = tone;
    }
}
