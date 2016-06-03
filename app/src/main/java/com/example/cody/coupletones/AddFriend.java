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

    static String partner; //holds email of partner
    static String potentialPartner;

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
        Firebase firebase = MainActivity.mainFireBase.child("users").child(MainActivity.uname);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if(user.toString() == "Accepted"){
                    partner = potentialPartner;
                    Intent i = new Intent(AddFriend.this, HomePage.class);
                    startActivity(i);
                }
                else if(user.toString() == "Declined")
                {
                    potentialPartner = "";
                }
                else {
                    alertDialogBuilder.setTitle(user.toString() + " would like to add you as their partner! Would you like to add them back?");
                    alertDialogBuilder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Firebase firebase = MainActivity.mainFireBase.child("users").child(dataSnapshot.getValue(User.class).toString());
                            firebase.setValue("Accepted");
                            partner = dataSnapshot.getValue(User.class).toString();
                            Intent i = new Intent(AddFriend.this, HomePage.class);
                            startActivity(i);

                        }
                    });
                    alertDialogBuilder.setNegativeButton("Decline", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Firebase firebase = MainActivity.mainFireBase.child("users").child(dataSnapshot.getValue(User.class).toString());
                            firebase.setValue("Declined");
                            dialog.cancel();
                        }
                    });
                    alertDialogBuilder.create();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {}
        });
        addPartnerListener();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        if(partner != "") {
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
                potentialPartner = email.getText().toString();
                Query query = MainActivity.mainFireBase.orderByChild("users").equalTo(email.getText().toString());
                query.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Firebase firebase = MainActivity.mainFireBase.child("users").child(email.getText().toString());
                        firebase.setValue(MainActivity.uemail);
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
                /*if (android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
                    partnerEmail = email.getText().toString();
                    Toast.makeText(getBaseContext(), "Successful Add", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(AddFriend.this, HomePage.class);
                    startActivity(i);
                }
                else {

                }*/
            }
        });
    }

}
