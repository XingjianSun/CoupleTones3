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

    String txtphoneNo = "18583978438";
    String txtMessage = "Test This";

    private static HashMap myFavLocs;
    public static float check = (float) 160.934;
    private GoogleMap mMap;

    public LocationChecker(GoogleMap mMap) {
        this.mMap = mMap;
        myFavLocs = new HashMap();
    }

    public void addLocation(String name, Location currLocation) {

        if(name.equals("") || name.equals(null) || myFavLocs.containsKey(name)) {
            Log.w("error", "No names specified");
            return;
        }
        if(currLocation == null) {
            Log.w("Location is Null", "Null Location");
            return;

        }
        myFavLocs.put(name, currLocation);
        if (mMap!=null) {
            mMap.addMarker(new MarkerOptions().position(new LatLng(currLocation.getLatitude(),
                    currLocation.getLongitude())).title(name));
        }
        Log.v("Success", "Successfully added Location");
    }

    public static boolean checkForVisit(Location location){
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
                return true;
            }
        }
        return false;
    }

    public HashMap getMap(){
        return myFavLocs;
    }

    protected void sendSMSMessage() {
        Log.i("Send SMS", "");
        String phoneNo = txtphoneNo;//.getText().toString();
        String message = txtMessage;//.getText().toString();

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
        }

        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS faild, please try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

}
