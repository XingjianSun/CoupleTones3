package com.example.cody.coupletones;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
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

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

//main Acitivty
public class MainActivity extends AppCompatActivity {
  static String uname = "";
  static String uemail = "";
  static String upassword = "";
  Button login;
  Button register;
  static Firebase firebase;
  EditText email,password;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    SharedPreferences sharedPreferences = getSharedPreferences("partner_info", MODE_PRIVATE);
    setContentView(R.layout.activity_main);

    /*if(sharedPreferences.getString("Partner's phone number", "").length() == 10 ||
     sharedPreferences.getString("Partner's phone number", "").length() == 4){
     Intent i = new Intent(MainActivity.this, HomePage.class);
     startActivity(i);
     }
     else {
     setContentView(R.layout.activity_main);*/
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
     /*
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
     }*/


  }
  protected void onStart(){
    super.onStart();
    email = (EditText)findViewById(R.id.email);
    password = (EditText)findViewById(R.id.password);
    login = (Button)findViewById(R.id.button);
    register = (Button)findViewById(R.id.register);

    login.setOnClickListener(new View.OnClickListener() {
      @Override //k
      public void onClick(View view) {
        String Email = email.getText().toString();
        String Password = password.getText().toString();
        
        if (checkLogin(Email,Password)) {
          
          Intent i = new Intent(MainActivity.this, AddFriend.class);
          startActivity(i);
        }
        else{
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
  
  private void registration()
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
    
    builder.setPositiveButton("Register", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        //TODO
        if (name.getText().toString() == "" || email.getText().toString() == null
            || password.getText().toString() == null || verify.getText().toString() == null) {
          Toast.makeText(getBaseContext(), "Missing Some Required Fields", Toast.LENGTH_LONG).show();
        }
        else if (password.getText().toString().equals("")) {
            Toast.makeText(getBaseContext(), "Password Cannot be Empty", Toast.LENGTH_LONG).show();
        }
        else if (!password.getText().toString().equals(verify.getText().toString())) {
          Toast.makeText(getBaseContext(), "Passwords Do Not Match", Toast.LENGTH_LONG).show();
        }
        else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
            Toast.makeText(getBaseContext(), "Invalid Email", Toast.LENGTH_LONG).show();
        }
        else {
          uname = name.getText().toString();
          uemail = email.getText().toString();
          upassword = password.getText().toString();
          Toast.makeText(getBaseContext(), "Register Success", Toast.LENGTH_LONG).show();
            Intent i = new Intent(MainActivity.this, AddFriend.class);
            startActivity(i);
        }
      }
    });
    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        dialog.cancel();
      }
    });
    builder.show();
    //TODO - if password != veriypassword, then show and send toast that they don't match
  }
  
  public boolean checkLogin(String Email, String Password){
    if(Email.equals(uemail)&& Password.equals(upassword) && android.util.Patterns.EMAIL_ADDRESS.matcher(uemail).matches()){
      Toast.makeText(getBaseContext(), "Login success", Toast.LENGTH_LONG).show();
      return true;
    }
    else{
      Toast.makeText(getBaseContext(), "Invalid Email or Password", Toast.LENGTH_LONG).show();
      // Toast.makeText(getBaseContext(), uemail, Toast.LENGTH_LONG).show();
      return false;
    }
    
  }
  
}
