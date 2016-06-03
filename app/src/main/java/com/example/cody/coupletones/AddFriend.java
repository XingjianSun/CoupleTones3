package com.example.cody.coupletones;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

public class AddFriend extends AppCompatActivity {

    static String partner = ""; //holds email of partner
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.otf");
        TextView addPartnerText = (TextView) findViewById(R.id.add_partner_text);
        final EditText partnerEmail = (EditText) findViewById(R.id.partner_email);
        final Button addPartnerButton = (Button) findViewById(R.id.add_partner_button);
        if(addPartnerText != null) addPartnerText.setTypeface(font);
        if(partnerEmail != null) partnerEmail.setTypeface(font);
        if(addPartnerButton != null) addPartnerButton.setTypeface(font);
        addPartnerListener();
        Log.v("on start", "came here2");
    }

/*    @Override
    protected void onStart() {
        super.onStart();
        if(finalPartner != "")
        {
            Intent i = new Intent(AddFriend.this, HomePage.class);
            startActivity(i);
        }
    }
*/
    @Override
    public void onBackPressed() {
    }

    private void addPartnerListener()
    {
        Button addPartnerButton = (Button) findViewById(R.id.add_partner_button);
        name = (EditText) findViewById(R.id.partner_email);
        addPartnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Firebase firebase = MainActivity.mainFireBase.child("users").child(MainActivity.uname).child("partner");
                firebase.setValue(name.getText().toString());
                partner = name.getText().toString();
                Firebase firebase2 = MainActivity.mainFireBase.child("users");
                Query query = firebase2.orderByChild("partner").equalTo(MainActivity.uname);
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.getValue() == null)
                        {
                            Log.v("on start", "came here3");
                            Toast.makeText(getBaseContext(), "Your partner has not added you back yet.", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Intent i = new Intent(AddFriend.this, HomePage.class);
                            startActivity(i);
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {}
                });
            }
        });
    }
}
