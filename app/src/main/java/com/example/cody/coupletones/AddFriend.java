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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddFriend extends AppCompatActivity {

    static String partnerEmail = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.otf");

        TextView addPartnerText = (TextView) findViewById(R.id.add_partner_text);
        EditText partnerEmail = (EditText) findViewById(R.id.partner_email);
        Button addPartnerButton = (Button) findViewById(R.id.add_partner_button);
        if(addPartnerText != null) addPartnerText.setTypeface(font);
        if(partnerEmail != null) partnerEmail.setTypeface(font);
        if(addPartnerButton != null) addPartnerButton.setTypeface(font);

        addPartnerListener();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        if(partnerEmail != "") {
            Intent i = new Intent(AddFriend.this, HomePage.class);
            startActivity(i);
        }
    }

    @Override
    public void onBackPressed() {
    }

    private void addPartnerListener()
    {
        Button addPartnerButton = (Button) findViewById(R.id.add_partner_button);
        final EditText email = (EditText) findViewById(R.id.partner_email);
        addPartnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
                    partnerEmail = email.getText().toString();
                    Toast.makeText(getBaseContext(), "Successful Add", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(AddFriend.this, HomePage.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(getBaseContext(), "Invalid Partner Email", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
