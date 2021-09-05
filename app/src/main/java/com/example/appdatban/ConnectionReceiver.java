package com.example.appdatban;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionReceiver extends BroadcastReceiver {
    private static final int TIMEOUT = 3000;
    private static final String SERVER_URL = "https://demo4368667.mockable.io";

    @Override
    public void onReceive(Context context, Intent intent) {
    }

    public static boolean isConnected() {
        ConnectivityManager
                cm = (ConnectivityManager) WifiApp.getInstance().getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if( ni != null && ni.isConnectedOrConnecting()) {
//            Runtime runtime = Runtime.getRuntime();
//            try {
//                Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
//                int exitValue = ipProcess.waitFor();
//                return (exitValue == 0);
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            return false;
            return true;
        }
        else
            return false;

    }

    public static boolean isConnectedSever() {
        try {
            ConnectivityManager
                    cm = (ConnectivityManager)
                    WifiApp.getInstance().getApplicationContext()
                            .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();

            if (netInfo != null && netInfo.isConnected()) {
                // Network is available but check if we can get access from the network
                URL url = new URL(SERVER_URL);
                HttpURLConnection urlc = (HttpURLConnection) url
                        .openConnection();
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(TIMEOUT); // Timeout 2 seconds.
                urlc.connect();

                if (urlc.getResponseCode() == 200) // Successful response.
                {
                    return true;
                } else {
                    Log.d("NO INTERNET", "NO INTERNET");

                    return false;
                }
            } else {
                Log.d("NO INTERNET CONNECTION", "NO INTERNET CONNECTION");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }
}
