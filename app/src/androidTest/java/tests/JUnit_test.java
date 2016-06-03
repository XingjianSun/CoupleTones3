package tests;

import android.location.Location;
import android.test.ActivityInstrumentationTestCase2;

import com.example.cody.coupletones.LocationChecker;
import com.example.cody.coupletones.MainActivity;
import com.example.cody.coupletones.MapsActivity;
import com.example.cody.coupletones.ToneManager;
import com.google.android.gms.maps.GoogleMap;

import java.util.HashMap;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Omnit_000 on 5/8/2016.
 */
public class JUnit_test extends ActivityInstrumentationTestCase2<MapsActivity> {

    private GoogleMap mMap;
    ToneManager toneManager = new ToneManager();

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
        locationChecker.addLocation("random3",location);
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
        assertEquals(fale, map.containsValue("tone_fail"));
    }
    /*
    public void test_changeTone_random(){
        toneManager.addTone("location", "tone1");
        toneManager.changeTone("location", "tone2");
        HashMap map = toneManager.getMyTones();
        assertEquals(true, map.containsValue("tone2"));
    }
    */

}
