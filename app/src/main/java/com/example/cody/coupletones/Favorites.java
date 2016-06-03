package com.example.cody.coupletones;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

public class Favorites extends AppCompatActivity {

    private ToneManager toneManager;
    private Vibrator vibrate;
    public static ArrayList<String> myList = new ArrayList<String>();
    public static ArrayList<String> theirList = new ArrayList<String>();
    public static ArrayAdapter<String> arrayAdapter;
    private ArrayAdapter<String> theirAdapter;

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
        if (toolbar != null) getSupportActionBar().setTitle("My Favorites");
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
                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);
                if (toolbar != null) getSupportActionBar().setTitle("My Favorites");
                ListView lv = (ListView) findViewById(R.id.visits);
                lv.setAdapter(arrayAdapter);
            }
        });
    }

    public void partnerFavoritesListener() {
        Button partnerFavorites = (Button) findViewById(R.id.partner_favorites);
        partnerFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);
                if (toolbar != null) getSupportActionBar().setTitle("Partner Favorites");
                ListView lv = (ListView) findViewById(R.id.visits);
                lv.setAdapter(theirAdapter);
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
        toneManager = new ToneManager();
        ListView lv = (ListView) findViewById(R.id.visits);

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myList);
        theirAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, theirList);
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
                initializeAdaptor(arrayAdapter);

                builderSingle.setNegativeButton(
                        "Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                AlertDialog.Builder cancel = builderSingle.setAdapter(
                        arrayAdapter,
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final String toneName = arrayAdapter.getItem(which);
                                AlertDialog.Builder builderInner = new AlertDialog.Builder(
                                        Favorites.this);
                                builderInner.setMessage(toneName);
                                builderInner.setTitle("Your Selected Tone is");

                                MediaPlayer player = MediaPlayer.create(Favorites.this, R.raw.default1);
                                Tones tone = new defaultTone(vibrate);
                                    switch (name) {
                                        case "default":
                                            tone = new defaultTone(vibrate);
                                            break;
                                        case "relax":
                                            tone = new relaxTone(vibrate);
                                            break;
                                        case "inception":
                                            tone = new inceptionTone(vibrate);
                                            break;
                                        case "sona":
                                            tone = new sonaTone(vibrate);
                                            break;
                                        case "whistle":
                                            tone = new whistleTone(vibrate);
                                            break;
                                        case "horn":
                                            tone = new hornTone(vibrate);
                                            break;
                                        case "success":
                                            tone = new successTone(vibrate);
                                            break;
                                        case "chime":
                                            tone = new chimeTone(vibrate);
                                            break;
                                        default:
                                            break;
                                    }
                               
                                tone.play(Favorites.this);


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


    public void initializeAdaptor(ArrayAdapter arrayAdapter){
        arrayAdapter.add("default");
        arrayAdapter.add("relax");
        arrayAdapter.add("inception");
        arrayAdapter.add("sona");
        arrayAdapter.add("whistle");
        arrayAdapter.add("horn");
        arrayAdapter.add("chime");
        arrayAdapter.add("success");
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
