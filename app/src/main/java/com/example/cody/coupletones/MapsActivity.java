package com.example.cody.coupletones;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.Criteria;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appinvite.AppInviteInvitation;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.cody.coupletones.LocationChecker;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener
        , View.OnClickListener, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    //private GoogleApiClient client;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private Location currLocation;
    private LocationChecker locationChecker;
    private LatLng tapped;
    public Button addFavLocation;
    public EditText nameLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        addFavLocation = (Button) findViewById(R.id.markFavLoc);
        addFavLocation.setOnClickListener(this);

        nameLocation = (EditText) findViewById(R.id.nameLoc);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.otf");
        Button button = (Button) findViewById(R.id.markFavLoc);
        EditText editText = (EditText) findViewById(R.id.nameLoc);
        if(button != null) button.setTypeface(font);
        if(editText != null) editText.setTypeface(font);

        tapped = new LatLng(0,0);

        locationChecker = new LocationChecker();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setOnMapClickListener(this);
        mMap.setOnMapLongClickListener(this);

        // Add a marker in Moscow and move the camera
        //LatLng Moscow = new LatLng(55,37);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("My location"));

        //location changed
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                //mMap.clear();
                //marker = mMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(),
                //      location.getLongitude())).title("location"));
                Log.v("Location Changed", "Location has changed");
                currLocation = location;
                boolean check = locationChecker.checkForVisit(location);
                if(check) sendSMSMessage("18583978438", "Hello World!");
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                // Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                //startActivity(intent);
            }
        };
        //param1: provider = GPS
        //param2: minTime = 120000 milliseconds between location refresh
        //param3: minDistnace = meters of change required to update location (161 meters = 1/10 of mile in meters
        //param4:

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET}, 10);
            } else {
                int x = 0;
            }
            return;
        }
        locationManager.requestLocationUpdates("gps", 0, 0, locationListener);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    void sendSMSMessage(String phoneNo, String message) {
        Log.i("Send SMS", "In sendSMSMessage()");

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

    //onclick addLocation
    @Override
    public void onClick(View v) {
        if (v == addFavLocation) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            Location toAdd = new Location("");
            toAdd.setLatitude(tapped.latitude);
            toAdd.setLongitude(tapped.longitude);
            Log.v("Button Pressed!", "The Button has been pressed!");
            String name = nameLocation.getText().toString();
            Log.v("Name", name);
            if(locationChecker.addLocation(name, toAdd)){
                mMap.addMarker(new MarkerOptions().position(new LatLng(toAdd.getLatitude(),
                        toAdd.getLongitude())).title(name));
            }
        }
    }

    @Override
    public void onMapClick(LatLng latLng) {
        tapped = latLng;
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        tapped = latLng;
    }
}