package com.example.cody.coupletones;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberUtils;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.graphics.Typeface;
import android.widget.Toast;
import android.widget.Button;
import android.content.Intent;

//main Acitivty
public class MainActivity extends AppCompatActivity {
    static String name = "";
    static String email = "";
    static String phoneNo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences("partner_info", MODE_PRIVATE);
        if(sharedPreferences.getString("Partner's phone number", "").length() == 10 ||
                sharedPreferences.getString("Partner's phone number", "").length() == 4){
            Intent i = new Intent(MainActivity.this, HomePage.class);
            startActivity(i);
        }
        else {
            setContentView(R.layout.activity_main);
            Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.otf");
            TextView title = (TextView) findViewById(R.id.title);
            TextView button = (TextView) findViewById(R.id.button);
            TextView register = (TextView) findViewById(R.id.register);
            EditText email = (EditText) findViewById(R.id.email);
            EditText password = (EditText) findViewById(R.id.password);
            if (title != null) title.setTypeface(font);
            if (button != null) button.setTypeface(font);
            if(register != null) register.setTypeface(font);
            if(email != null) email.setTypeface(font);
            if(password != null) password.setTypeface(font);

            Button login = (Button) findViewById(R.id.button);
            enterPhoneNo();
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ((phoneNo.length() == 10 || phoneNo.length() == 4) && PhoneNumberUtils.isGlobalPhoneNumber(phoneNo)) {

                        Intent i = new Intent(MainActivity.this, HomePage.class);
                        startActivity(i);
                        SharedPreferences preferences = getSharedPreferences("partner_info", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("Partner's phone number", phoneNo); // value to store
                        editor.apply();
                    } else {
                        enterPhoneNo();
                        Toast.makeText(getBaseContext(), "Invalid phone number", Toast.LENGTH_LONG).show();
                    }
                }
            });
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, HomePage.class);
                    startActivity(i);
                    Toast.makeText(getBaseContext(), "Test Register Button", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
            Toast.makeText(getBaseContext(), "Test", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void enterPhoneNo()
    {

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.otf");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        builder.setTitle("Enter User Information");

        // Set up the input
        final EditText name = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        name.setHint("Name");
        name.setInputType(InputType.TYPE_CLASS_TEXT);
        name.setTypeface(font);
        name.setTextColor(Color.parseColor("#9d4747"));
        layout.addView(name);

        final EditText email = new EditText(this);
        email.setHint("Email");
        email.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        email.setTypeface(font);
        email.setTextColor(Color.parseColor("#9d4747"));
        layout.addView(email);

        final EditText password = new EditText(this);
        password.setHint("Password");
        password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        password.setTransformationMethod(new PasswordTransformationMethod());
        password.setTypeface(font);
        password.setTextColor(Color.parseColor("#9d4747"));
        layout.addView(password);

        final EditText verify = new EditText(this);
        verify.setHint("Verify Password");
        verify.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        verify.setTransformationMethod(new PasswordTransformationMethod());
        verify.setTypeface(font);
        verify.setTextColor(Color.parseColor("#9d4747"));
        layout.addView(verify);

        builder.setView(layout);

        // Set up the buttons
        builder.setPositiveButton("Register", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                phoneNo = name.getText().toString();
                //LocationChecker.receiptNo = phoneNo;

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        if(phoneNo.length() < 10) builder.show();
    }

}
