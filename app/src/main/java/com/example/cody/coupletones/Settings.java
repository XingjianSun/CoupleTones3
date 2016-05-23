package com.example.cody.coupletones;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.otf");
        TextView toneLabel = (TextView) findViewById(R.id.tone_label);
        Switch toneSwitch = (Switch) findViewById(R.id.tone_switch);
        TextView vibrateLabel = (TextView) findViewById(R.id.vibrate_label);
        Switch vibrateSwitch = (Switch) findViewById(R.id.vibrate_switch);

        if(toneLabel != null) toneLabel.setTypeface(font);
        if(toneSwitch != null) toneSwitch.setTypeface(font);
        if(vibrateLabel != null) vibrateLabel.setTypeface(font);
        if(vibrateSwitch != null) vibrateSwitch.setTypeface(font);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toneSwitchListener();
        vibrateSwitchListener();
    }

    public void toneSwitchListener()
    {
        final Switch toneSwitch = (Switch) findViewById(R.id.tone_switch);
        toneSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (toneSwitch.isChecked() == true) {
                    toneSwitch.setText("On ");
                } else toneSwitch.setText("Off ");
            }
        });
    }

    public void vibrateSwitchListener()
    {
        final Switch toneSwitch = (Switch) findViewById(R.id.vibrate_switch);
        toneSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toneSwitch.isChecked() == true)
                {
                    toneSwitch.setText("On ");
                }
                else toneSwitch.setText("Off ");
            }
        });
    }

}
