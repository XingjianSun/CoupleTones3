package com.example.cody.coupletones;

/**
 * Created by Cody on 5/7/16.
 */
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.xml.sax.helpers.LocatorImpl;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LocationChecker extends AppCompatActivity{


    SmsManager smsManager = SmsManager.getDefault();

    private static HashMap myFavLocs;
    public static float check = (float) 160.934;
    public static String receiptNo = (String) "";
    //private GoogleMap mMap;

    public LocationChecker() {
        //this.mMap = mMap;
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

    public boolean checkForVisit(Location location){
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

                smsManager.sendTextMessage(receiptNo, null, "Your partner has visited" + pair.getKey(), null, null);
                return true;
            }
        }
        return false;
    }

    public HashMap getMap(){
        return myFavLocs;
    }



}
