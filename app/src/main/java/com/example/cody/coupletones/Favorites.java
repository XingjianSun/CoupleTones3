package com.example.cody.coupletones;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Favorites extends AppCompatActivity {

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
    }

    public void userFavoritesListener()
    {
        Button userFavorites = (Button) findViewById(R.id.user_favorites);
        userFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Test User Favorites", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void partnerFavoritesListener()
    {
        Button partnerFavorites = (Button) findViewById(R.id.partner_favorites);
        partnerFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Test Partner Favorites", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void initializeComponents(){
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.otf");
        Button userFavorites = (Button) findViewById(R.id.user_favorites);
        Button partnerFavorites = (Button) findViewById(R.id.partner_favorites);

        if(userFavorites != null) userFavorites.setTypeface(font);
        if(partnerFavorites != null) partnerFavorites.setTypeface(font);
    }

}
