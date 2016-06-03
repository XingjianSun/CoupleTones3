package com.example.cody.coupletones;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;


public class MainActivity extends AppCompatActivity {
    static String uname = "";
    static String uemail = "";
    static String upassword = "";
    static Firebase firebase;
    static final Firebase mainFireBase = firebase = new Firebase("https://urajkuma-110.firebaseio.com/");
    Button login;
    Button register;
    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeStartScreen();
    }

    private void initializeStartScreen() {
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.otf");
        TextView title = (TextView) findViewById(R.id.title);
        TextView button = (TextView) findViewById(R.id.button);
        TextView register = (TextView) findViewById(R.id.register);
        EditText email = (EditText) findViewById(R.id.email);
        EditText password = (EditText) findViewById(R.id.password);
        if (title != null) title.setTypeface(font);
        if (button != null) button.setTypeface(font);
        if (register != null) register.setTypeface(font);
        if (email != null) email.setTypeface(font);
        if (password != null) password.setTypeface(font);
    }

    protected void saveLoginData() {
        Firebase newUserRef = mainFireBase.child("users").child(uname);
        User newUser = new User(uname, uemail, upassword);
        newUserRef.setValue(newUser);
    }

    protected void onStart() {
        super.onStart();
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.button);
        register = (Button) findViewById(R.id.register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override //k
            public void onClick(View view) {
                String Email = email.getText().toString();
                String Password = password.getText().toString();

                if (checkLogin(Email, Password)) {

                    Intent i = new Intent(MainActivity.this, AddFriend.class);
                    startActivity(i);
                } else {
                    email.clearComposingText();
                    email.clearComposingText();
                    password.clearComposingText();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registration();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.profile) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void registration() {

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

        builder.setPositiveButton("Register", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog toShow = builder.create();
        toShow.show();
        toShow.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString() == "" || email.getText().toString() == null
                        || password.getText().toString() == null || verify.getText().toString() == null) {
                    Toast.makeText(getBaseContext(), "Missing Some Required Fields", Toast.LENGTH_LONG).show();
                } else if (password.getText().toString().equals("")) {
                    Toast.makeText(getBaseContext(), "Password Cannot be Empty", Toast.LENGTH_LONG).show();
                } else if (!password.getText().toString().equals(verify.getText().toString())) {
                    Toast.makeText(getBaseContext(), "Passwords Do Not Match", Toast.LENGTH_LONG).show();
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
                    Toast.makeText(getBaseContext(), "Invalid Email", Toast.LENGTH_LONG).show();
                } else {
                    uname = name.getText().toString();
                    uemail = email.getText().toString();
                    upassword = password.getText().toString();
                    saveLoginData();
                    Toast.makeText(getBaseContext(), "Registration Success", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(MainActivity.this, AddFriend.class);
                    startActivity(i);
                    toShow.dismiss();
                }
            }
        });
    }

    public boolean checkLogin(String Email, String Password) {
        if (Email.equals(uemail) && Password.equals(upassword) && android.util.Patterns.EMAIL_ADDRESS.matcher(uemail).matches()) {
            Toast.makeText(getBaseContext(), "Login success", Toast.LENGTH_LONG).show();
            return true;
        } else {
            Toast.makeText(getBaseContext(), "Invalid Email or Password", Toast.LENGTH_LONG).show();
            return false;
        }
    }
}
