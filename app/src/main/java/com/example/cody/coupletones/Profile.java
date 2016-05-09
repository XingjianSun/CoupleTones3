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
import android.widget.Toast;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set fonts for objects in the activity page
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.otf");
        TextView name = (TextView) findViewById(R.id.name);
        Button view_favorites = (Button) findViewById(R.id.view_favorites);
        Button log_out = (Button) findViewById(R.id.log_out_button);
        if(name != null) name.setTypeface(font);
        if(view_favorites != null) view_favorites.setTypeface(font);
        if(log_out != null) log_out.setTypeface(font);

        viewFavsListener();
        logOutListener();

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
                                        SharedPreferences preferences = getSharedPreferences("partner_info", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = preferences.edit();
                                        editor.putString("Partner's phone number", "");
                                        editor.apply();
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
                Toast.makeText(getBaseContext(), "View Favorites", Toast.LENGTH_LONG).show();
            }
        });
    }
}
