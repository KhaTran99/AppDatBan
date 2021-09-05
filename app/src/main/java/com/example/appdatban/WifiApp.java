package com.example.appdatban;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class WifiApp extends Application {
    static WifiApp wifiInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        wifiInstance = this;
        Stetho.initializeWithDefaults(this);
    }
    public static synchronized WifiApp getInstance() {
        return wifiInstance;
    }
}
