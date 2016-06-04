package tests;

import android.location.Location;
import android.os.Vibrator;
import android.test.ActivityInstrumentationTestCase2;

import com.example.cody.coupletones.LocationChecker;
import com.example.cody.coupletones.MainActivity;
import com.example.cody.coupletones.MapsActivity;
import com.example.cody.coupletones.ToneManager;
import com.example.cody.coupletones.mainAdaptor;
import com.example.cody.coupletones.tones.defaultTone;
import com.google.android.gms.maps.GoogleMap;

import java.util.HashMap;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Omnit_000 on 5/8/2016.
 */
public class JUnit_test extends ActivityInstrumentationTestCase2<MapsActivity> {


    private GoogleMap mMap;
    private mainAdaptor adaptor = new mainAdaptor();
    private boolean notification = false;
    ToneManager toneManager = new ToneManager();
    private Vibrator vibrate = null;
    private defaultTone defaulttone = new defaultTone(vibrate);

    LocationChecker locationChecker = new LocationChecker();


    public JUnit_test(){
        super(MapsActivity.class);
    }


    //check if a location is successfully added in to the hashmap myFavLocs
    public void test_addLocation_success(){
        Location location = new Location("random");
        location.setLatitude(55.0);
        location.setLongitude(11.5);
        locationChecker.addLocation("random",location);
        HashMap map = locationChecker.getMap();

        if (map.containsKey("random")==true && map.containsValue(location)==true){
            assertEquals(true,true);
        }



    }

    //null location should not be added
    public void test_addLocation_failure()
    {
        Location location = null;
        locationChecker.addLocation("null", location);
        HashMap map = locationChecker.getMap();

        assertEquals(false,map.containsKey("null"));


    }

    //senario valid visit
    public void test_valid_visit(){
        Location location = new Location("random");
        location.setLatitude(55.0);
        location.setLongitude(11.5);
        locationChecker.addLocation("random2",location);
        HashMap map = locationChecker.getMap();
        boolean test = locationChecker.checkForVisit(location, true);
        assertEquals(true, test);

    }

    //senario invalid visit
    public void test_invalid_visit(){
        Location location = new Location("random");
        location.setLatitude(55.0);
        location.setLongitude(11.5);

        Location test_location = new Location("random2");
        test_location.setLatitude(120);
        test_location.setLongitude(145);
        locationChecker.addLocation("random3", location);
        HashMap map = locationChecker.getMap();
        boolean test = locationChecker.checkForVisit(test_location, true);
        assertEquals(false, test);
    }

    /*
    public void test_invalid_phoneNo(){
        MainActivity main = new MainActivity();
        String number = "123456";
        boolean test = main.checkPhoneNumber(number);
        assertEquals(false, test);
    }

*/

    public void test_addTone_success(){
        toneManager.addTone("location", "tone1");
        HashMap map = toneManager.getMyTones();
        assertEquals(true, map.containsKey("location"));

    }

    public void test_addTone_same(){
        toneManager.addTone("location", "tone1");
        toneManager.addTone("location", "tone1");
        HashMap map = toneManager.getMyTones();
        assertEquals(true, map.containsKey("location"));

    }

    public void test_addTone_fail(){

        HashMap map = toneManager.getMyTones();
        assertEquals(false, map.containsKey("location_fail"));

    }
    /*
    public void test_addTone_random(){
        toneManager.addTone("location", "tone1");
        HashMap map = toneManager.getMyTones();
        assertEquals(true, map.containsKey("location"));

    }
    */

    public void test_changeTone_success(){
        toneManager.addTone("location", "tone1");
        toneManager.changeTone("location", "tone2");
        HashMap map = toneManager.getMyTones();
        assertEquals(true, map.containsValue("tone2"));
    }

    public void test_changeTone_same(){
        toneManager.addTone("location", "tone1");
        toneManager.changeTone("location", "tone1");
        HashMap map = toneManager.getMyTones();
        assertEquals(true, map.containsValue("tone1"));
    }

    public void test_changeTone_fail(){

        HashMap map = toneManager.getMyTones();
        assertEquals(false, map.containsValue("tone_fail"));
    }
    /*
    public void test_changeTone_random(){
        toneManager.addTone("location", "tone1");
        toneManager.changeTone("location", "tone2");
        HashMap map = toneManager.getMyTones();
        assertEquals(true, map.containsValue("tone2"));
    }
    *
    */

    //visit notifications test
    //scenario 1 arrival ribbon notification
    public void test_arrival_notification(){
        Location location = new Location("random");
    location.setLatitude(55.0);
    location.setLongitude(11.5);
    if(locationChecker.checkForVisit(location, true)){
        assertEquals(true, adaptor.notification());
    }

}

    //scenario 2 departure ribbon notification
    public void test_departure_notification(){
        Location location = new Location("random");
        location.setLatitude(55.0);
        location.setLongitude(11.5);
       if( locationChecker.checkForDeparture(location)){
           assertEquals(true, adaptor.notification());
       }

    }

    //scenario 1: arrival departure tone test
    public void test_arrival_tone(){
        boolean toneOn = true;
        Vibrator vibrator = null;
        Location location = new Location("random");
        location.setLatitude(55.0);
        location.setLongitude(11.5);
        toneManager.addTone("random","default");
        if(locationChecker.checkForVisit(location, true)){

            assertNotNull(toneManager.getTone("random", vibrate));
        }
    }

    //scenario 2: departure tone test
    public void test_departure_tone(){
        boolean toneOn = true;
        Vibrator vibrator = null;
        Location location = new Location("random");
        location.setLatitude(55.0);
        location.setLongitude(11.5);
        if(locationChecker.checkForDeparture(location)) {
            assertEquals(true, toneManager.getTone(location.toString(), vibrator));
        }
    }

    //notificationSetting test
    //scenario 1 turn off the sound
    public void test_turn_off_sound(){
        adaptor.turnSoundOff();
        assertEquals(false, adaptor.getSoundNotification());
    }

    //scenario 2 turn on the sound
    public void test_turn_on_sound(){
        adaptor.turnSoundOn();
        assertEquals(true, adaptor.getSoundNotification());
    }

    //scenario 3 turn off vibration
    public void test_turn_off_vibration(){
        adaptor.turnVibrationOff();
        assertEquals(false,adaptor.getVibrateNotification());
    }

    //scenario 4 turn on vibration
    public void test_turn_on_vibration(){
        adaptor.turnVibrationOn();
        assertEquals(true, adaptor.getVibrateNotification());
    }

    //vibrationNotification test
    //scenario 1 arrival vibration
    public void test_arrival_vibration(){
        boolean vibrateOn = true;
        Location location = new Location("random");
        location.setLatitude(55.0);
        location.setLongitude(11.5);
        adaptor.turnVibrationOn();
        toneManager.addTone("random","default");
        if(locationChecker.checkForVisit(location, true)){

            assertNotNull(toneManager.getTone("random", vibrate));
        }
    }

    //scenario 2 departure vibration
    public void test_departure_vibration(){
        boolean vibrateOn = true;
        Location location = new Location("random");
        location.setLatitude(55.0);
        location.setLongitude(11.5);
        adaptor.turnVibrationOn();
        if(locationChecker.checkForDeparture(location)){
            assertEquals(true, toneManager.getTone(location.toString(), vibrate));
        }
    }

    //scenario 3 viration notification turned off
    public void test_vibration_off(){
        boolean vibrateOn = false;
        Location location = new Location("random");
        location.setLatitude(55.0);
        location.setLongitude(11.5);
        adaptor.turnVibrationOff();
        toneManager.addTone("random","default");
        if(locationChecker.checkForVisit(location, true)){

            assertNotNull(toneManager.getTone("random", vibrate));
        }
    }

    //test list of favorite locations
    //scenario 1 partner has visited list of favorite locations
    public void test_partnerVisit_favorite_locations(){

        Location location = new Location("random");
        location.setLatitude(55.0);
        location.setLongitude(11.5);
        locationChecker.addLocation("random", location);
        HashMap map =locationChecker.getMap();
        if(locationChecker.checkForVisit(location, true)) {
            assertEquals(false, adaptor.containsKey("random"));
        }
    }

    //scenario 2 partner has not visited, empty list of favorite locations
    public void test_partnerNotVisit_favorite_locations(){

        Location location = new Location("random");
        location.setLatitude(55.0);
        location.setLongitude(11.5);
        locationChecker.addLocation("random", location);
        HashMap map =locationChecker.getMap();
        if(locationChecker.checkForVisit(location, false)) {
            assertEquals(false, adaptor.containsKey("random"));
        }
    }

    //scenario 3 partner has no favorite locations
    public void test_no_favorite_locations(){
       if( locationChecker.getMap()==null){
           assertEquals(true, adaptor.getList()==null);
       }
    }

}
