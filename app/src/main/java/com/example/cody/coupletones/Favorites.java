package com.example.cody.coupletones;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class Favorites extends AppCompatActivity {

    private ToneManager toneManager;
    private Vibrator vibrate;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initializeComponents();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        userFavoritesListener();
        partnerFavoritesListener();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void userFavoritesListener() {
        Button userFavorites = (Button) findViewById(R.id.user_favorites);
        userFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Test User Favorites", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void partnerFavoritesListener() {
        Button partnerFavorites = (Button) findViewById(R.id.partner_favorites);
        partnerFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Toast.makeText(getBaseContext(), "Test Partner Favorites", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initializeComponents() {
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.otf");
        Button userFavorites = (Button) findViewById(R.id.user_favorites);
        Button partnerFavorites = (Button) findViewById(R.id.partner_favorites);

        if (userFavorites != null) userFavorites.setTypeface(font);
        if (partnerFavorites != null) partnerFavorites.setTypeface(font);

        vibrate = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        final long[] once = { 0, 100 };
        final long[] twice = { 0, 100, 400, 100 };
        final long[] thrice = { 0, 100, 400, 100, 400, 100 };


        toneManager = new ToneManager();
        ListView lv = (ListView) findViewById(R.id.visits);
        ArrayList<String> listofLocations = new ArrayList<String>();

        listofLocations.add("Geisel Library");
        listofLocations.add("Price Center");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listofLocations);
        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
                Toast.makeText(getBaseContext(), "Test", Toast.LENGTH_LONG).show();
                AlertDialog.Builder builderSingle = new AlertDialog.Builder(Favorites.this);

                builderSingle.setTitle("Select Your Tone and Vibration");

                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                        Favorites.this,
                        android.R.layout.select_dialog_singlechoice);
                arrayAdapter.add("default");
                arrayAdapter.add("relax");
                arrayAdapter.add("inception");
                arrayAdapter.add("sona");
                arrayAdapter.add("whistle");
                arrayAdapter.add("horn");
                arrayAdapter.add("guitar");
                arrayAdapter.add("chime");
                arrayAdapter.add("success");
                arrayAdapter.add("bells");


                builderSingle.setNegativeButton(
                        "cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                AlertDialog.Builder cancel = builderSingle.setAdapter(
                        arrayAdapter,
                        new DialogInterface.OnClickListener() {
                            int userChoice = 1;
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final String toneName = arrayAdapter.getItem(which);
                                AlertDialog.Builder builderInner = new AlertDialog.Builder(
                                        Favorites.this);
                                builderInner.setMessage(toneName);
                                builderInner.setTitle("Your Selected Tone is");

                                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                                MediaPlayer player = MediaPlayer.create(Favorites.this, R.raw.default1);


                                try {

                                    switch(toneName) {
                                        case "default":
                                            player = MediaPlayer.create(Favorites.this, R.raw.default1);
                                            userChoice = 1;
                                            break;
                                        case "relax":
                                            player = MediaPlayer.create(Favorites.this, R.raw.relax);
                                            userChoice = 2;
                                            break;
                                        case "inception":
                                            player = MediaPlayer.create(Favorites.this, R.raw.inception);
                                            userChoice = 3;
                                            break;
                                        case "sona":
                                            player = MediaPlayer.create(Favorites.this, R.raw.sona);
                                            userChoice = 1;
                                            break;
                                        case "whistle":
                                            player = MediaPlayer.create(Favorites.this, R.raw.whistle);
                                            userChoice = 2;
                                            break;
                                        case "horn":
                                            player = MediaPlayer.create(Favorites.this, R.raw.horn);
                                            userChoice = 3;
                                            break;
                                        case "success":
                                            player = MediaPlayer.create(Favorites.this, R.raw.success);
                                            userChoice = 1;
                                            break;
                                        case "guitar":
                                            player = MediaPlayer.create(Favorites.this, R.raw.guitar);
                                            userChoice = 2;
                                            break;
                                        case "bells":
                                            player = MediaPlayer.create(Favorites.this, R.raw.bells);
                                            userChoice = 3;
                                            break;
                                        case "chime":
                                            player = MediaPlayer.create(Favorites.this, R.raw.chime);
                                            userChoice = 1;
                                            break;
                                        default:break;
                                    }

                                    switch(userChoice){
                                        case 1: vibrate.vibrate(once, -1);
                                            break;
                                        case 2: vibrate.vibrate(twice,-1);
                                            break;
                                        case 3: vibrate.vibrate(thrice,-1);
                                            break;
                                        default: break;

                                    }

                                    player.start();



                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                AlertDialog.Builder builder = builderInner.setPositiveButton(
                                        "Set As Tone",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(
                                                    DialogInterface dialog,
                                                    int which) {
                                                toneManager.addTone((String) parent.getItemAtPosition(position), toneName);
                                                //Toast.makeText(getBaseContext(), (String)toneManager.myTones.get("Geisel Library"), Toast.LENGTH_LONG).show();
                                                dialog.dismiss();

                                            }
                                        });


                                builderInner.setNegativeButton(
                                        "Cancel",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(
                                                    DialogInterface dialog,
                                                    int which) {
                                                dialog.dismiss();

                                            }
                                        });
                                builderInner.show();
                            }
                        });
                builderSingle.show();


               /* adb.setMessage(" selected Item is="
                        +parent.getItemAtPosition(position));
                adb.setPositiveButton("Ok", null);
                */

                               }
        });

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Favorites Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.cody.coupletones/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Favorites Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.cody.coupletones/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
