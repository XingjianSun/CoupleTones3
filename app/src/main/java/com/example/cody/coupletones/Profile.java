package com.example.cody.coupletones;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;


/*
   * File containing user profile information.
   * -Name
   * -List of favorites
   * -Vibration/Sound notification toggling
   * -Option to logout
   *
 */
public class Profile extends AppCompatActivity {
    public static HashMap myFavLocs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeComponents();

        viewFavsListener();
        logOutListener();
        settingsListener();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void logOutListener() {
        Button button_sbm = (Button)findViewById(R.id.log_out_button);
        button_sbm.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder a_builder = new AlertDialog.Builder(Profile.this);
                        a_builder.setMessage("Are you sure you want to log out?")
                                .setCancelable(false)
                                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        resetProfile();
                                        Intent i = new Intent(Profile.this, MainActivity.class);
                                        startActivity(i);
                                    }
                                })
                                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                }) ;
                        AlertDialog alert = a_builder.create();
                        alert.setTitle("Log Out");
                        alert.show();
                    }
                }
        );
    }

    public void viewFavsListener()
    {
        Button favorites = (Button) findViewById(R.id.view_favorites);
        favorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profile.this, Favorites.class);
                startActivity(i);
            }
        });
    }

    public void settingsListener()
    {
        Button settings = (Button) findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profile.this, NotificationSettings.class);
                startActivity(i);
            }
        });
    }

    private void initializeComponents()
    {
        // Set fonts for objects in the activity page
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.otf");
        TextView name = (TextView) findViewById(R.id.name);
        if(MainActivity.uname != null) {
            name.setText(MainActivity.uname);
        }

        Button view_favorites = (Button) findViewById(R.id.view_favorites);
        Button log_out = (Button) findViewById(R.id.log_out_button);
        Button settings = (Button) findViewById(R.id.settings);

        //Null checks
        if(name != null) name.setTypeface(font);
        if(view_favorites != null) view_favorites.setTypeface(font);
        if(log_out != null) log_out.setTypeface(font);
        if(settings != null) settings.setTypeface(font);

        //yet to change----------------------------------------------------------------------
        //name.setText("Name");
    }
    //--------------------------------------------------------------------------------
    private void resetProfile()
    {

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
