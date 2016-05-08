package com.example.cody.coupletones;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // if single
        setContentView(R.layout.activity_profile);

        // if not single
        //setContentView(R.layout.content_taken_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.otf");
        TextView name = (TextView) findViewById(R.id.name);
        Button add_partner = (Button) findViewById(R.id.add_partner);
        Button view_favorites = (Button) findViewById(R.id.view_favorites);
        Button log_out = (Button) findViewById(R.id.log_out_button);
        if(name != null) name.setTypeface(font);
        if(add_partner != null) add_partner.setTypeface(font);
        if(view_favorites != null) view_favorites.setTypeface(font);
        if(log_out != null) log_out.setTypeface(font);

        Person cody = new Person("Cody");

        TextView textViewToChange = (TextView) findViewById(R.id.name);
        textViewToChange.setText(cody.getName());

        Button login = (Button) findViewById(R.id.add_partner);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Add Partner", Toast.LENGTH_LONG).show();
            }
        });

        Button favorites = (Button) findViewById(R.id.view_favorites);
        favorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "View Favorites", Toast.LENGTH_LONG).show();
            }
        });

        onButtonClickListener();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onButtonClickListener() {
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

}
