package com.example.cody.coupletones;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/*
    * a class that takes care adding new favorite locations and
    * checking valid visit
    *
 */
public class LocationChecker extends AppCompatActivity{

    private static HashMap myFavLocs;
    public static float check = (float) 160.934; //1/10 of a mile in meters

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
        Log.v("Success", "Successfully added Location");

        return true;
    }
    public boolean checkForVisit(Location location, boolean test){
        Iterator it = myFavLocs.entrySet().iterator();
        if(myFavLocs.isEmpty()) {
            return false;
        }
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            Location toComp = (Location) pair.getValue();
            float dist = location.distanceTo(toComp);
            if(dist <= check){
                Log.v("Visit", "A Visit has occurred!!");
                if(!test){
                    MainActivity.firebase.setValue("Your partner has visited "+pair.getKey());
                }
                return true;
            }
        }
        return false;
    }

    public HashMap getMap(){
        return myFavLocs;
    }
}
