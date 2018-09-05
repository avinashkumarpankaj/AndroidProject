package com.androidproject.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.google.gson.Gson;

public class UtilClass {

    private final static String TAG =UtilClass.class.getSimpleName();

    public static Object parseJson(String jsonString, Class responseClass) {
        try {
            if (jsonString != null) {
                return new Gson().fromJson(jsonString, responseClass);
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return null;

    }

    public static boolean isNetworkAvailable(Context context) {

        try {
            /** Handling network management connections */
            ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            if (conMgr != null) {
                // Returning true if we are connecting to network
                NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
                return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }
}
