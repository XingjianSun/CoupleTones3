package com.example.cody.coupletones;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.view.Menu;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;

import java.io.IOException;
import java.util.ArrayList;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

public class HomePage extends AppCompatActivity {

    static String phoneNo = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar!= null) setSupportActionBar(toolbar);
        if(toolbar != null) getSupportActionBar().setTitle("CoupleTones");

        ListView lv = (ListView) findViewById(R.id.visits);

        ArrayList<String> test = new ArrayList<String>();
        //still to fix
        test.add("Location");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, test );

//        getRegId();

        enterPhoneNo();

        lv.setAdapter(arrayAdapter);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
     public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.profile) {
            Intent i = new Intent(HomePage.this, Profile.class);
            startActivity(i);
            return true;
        }

        if(id == R.id.add_location)
        {
            Intent i = new Intent(HomePage.this, MapsActivity.class);
            startActivity(i);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    private void enterPhoneNo()
    {
        final EditText input = (EditText) findViewById(R.id.phoneNum);
        if(input.toString() == "Enter Partner's Phone No."){

            Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.otf");
            TextView title = (TextView) findViewById(R.id.title);
            TextView button = (TextView) findViewById(R.id.button);
            if(title != null) title.setTypeface(font);
            if(button != null) button.setTypeface(font);

            Button login = (Button) findViewById(R.id.button);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });
        }

/*

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter Partner's Phone Number");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_PHONE);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                phoneNo = input.getText().toString();
                LocationChecker.receiptNo = phoneNo;
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        if(phoneNo == "") builder.show();*/
    }

}
