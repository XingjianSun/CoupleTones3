package com.example.cody.coupletones;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

//homepage UI+ login
public class HomePage extends AppCompatActivity {
    ListView lv;
    ArrayList<String> listofLocations;
    ArrayAdapter<String> arrayAdapter;
    Firebase firebase;
    static String uemail = "";
    boolean init = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) setSupportActionBar(toolbar);
        if (toolbar != null) getSupportActionBar().setTitle("Partner's Visited Locations");
        //if(uemail == "") addFriend();
        initializeComponents();
        firebase = new Firebase("https://urajkuma-110.firebaseio.com/ProjectDemo");
        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String data = dataSnapshot.getValue(String.class);
                String toAdd = data.substring(16);
                listofLocations.add(toAdd);
                lv.setAdapter(arrayAdapter);
                Toast.makeText(getBaseContext(), data, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {}
        });
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

        if (id == R.id.add_location) {
            Intent i = new Intent(HomePage.this, MapsActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void initializeComponents() {
        lv = (ListView) findViewById(R.id.visits);
        listofLocations = new ArrayList<String>();

        listofLocations.add("Geisel Library");
        listofLocations.add("Price Center");

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listofLocations);
        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getBaseContext(), "Test", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void addFriend() {

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.otf");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Add Partner");

        // Set up the input
        final EditText name = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        name.setHint("Partner Email");
        name.setInputType(InputType.TYPE_CLASS_TEXT);
        name.setTypeface(font);
        name.setTextColor(Color.parseColor("#9d4747"));
        builder.setView(name);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(name.getText().toString()).matches()) {
                    uemail = name.getText().toString();
                    Toast.makeText(getBaseContext(), "Successful Add", Toast.LENGTH_LONG).show();
                } else Toast.makeText(getBaseContext(), "Invalid Email", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
}
