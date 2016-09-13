package com.tes.vi.myfirebase;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by taufiqotulfaidah on 9/13/16.
 */
public class CrowdWeather extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
