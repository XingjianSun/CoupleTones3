package com.example.cody.coupletones;
import com.firebase.client.Firebase;

/**
 * Created by Utkrisht on 5/18/2016.
 */
public class FirebaseContext extends android.app.Application {
    public void onCreate(){
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}

