package com.example.cody.coupletones;

import android.content.SharedPreferences;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.client.realtime.util.StringListReader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/*
    * a class that takes care adding new favorite locations and
    * checking valid visit
    *
 */
public class LocationChecker extends AppCompatActivity{

    private static HashMap myFavLocs;
    public static float check = (float) 160.934; //1/10 of a mile in meters
    String currentlyAt = "";
    Location current = null;

    public LocationChecker() {
        myFavLocs = new HashMap();
    }

    public boolean addLocation(String name, Location currLocation) {
        if(name.equals("") || name.equals(null) || myFavLocs.containsKey(name)) {
            Log.w("error", "No names specified");
            return false;
        }
        if(currLocation == null) {
            Log.w("Location is Null", "Null Location");
            return false;
        }
        myFavLocs.put(name, currLocation);
        SharedPreferences  sharedPreferences = getSharedPreferences("User info", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(name,"default");
        editor.apply();
        Log.v("Success", "Successfully added Location");

        return true;
    }
    public boolean checkForVisit(Location location, boolean test){
        Iterator it = myFavLocs.entrySet().iterator();
        if(myFavLocs.isEmpty()) {
            return false;
        }
        while(it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Location toComp = (Location) pair.getValue();
            float dist = location.distanceTo(toComp);
            if (dist <= check && currentlyAt.equals("")) {
                Log.v("Visit", "A Visit has occurred!!");
                if (!test) {
                    currentlyAt = (String) pair.getKey();
                    current = toComp;
                    MainActivity.firebase = new Firebase("https://urajkuma-110.firebaseio.com/ProjectDemo");
                    MainActivity.firebase.setValue("Your partner has visited " + pair.getKey());
                    SharedPreferences sharedPreferences = getSharedPreferences("User info", MODE_PRIVATE);
                    String name = sharedPreferences.getString(currentlyAt, "");
                }
                return true;
            }
        }
        return false;
    }

    public boolean checkForDeparture(Location location){
        if(current == null){
            return false;
        }
        float dist = location.distanceTo(current);
        if(dist > check){
            MainActivity.firebase = new Firebase("https://urajkuma-110.firebaseio.com/ProjectDemo");
            MainActivity.firebase.setValue("Your partner has departed " + currentlyAt);
            currentlyAt = "";
            current = null;
            return true;
        }
        return false;
    }

    public HashMap getMap(){
        return myFavLocs;
    }
}
