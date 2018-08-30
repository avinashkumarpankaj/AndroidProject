package utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import com.google.gson.Gson;

public class UtilClass {

    public static Object parseJson(String jsonString, Class responseClass) {
        try {
            if (jsonString != null) {
                return (Object) new Gson().fromJson(jsonString, responseClass);
            }
        } catch (Exception e) {
            Log.e("JsonParser", e.toString());
        }
        return null;

    }

    public static boolean isNetworkAvailable(Context context) {

        /** Handling network management connections */
        ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable()
                && conMgr.getActiveNetworkInfo().isConnected()) {
            return true; // Returning true if we are connecting to network
        } else {
            return false; // Returns false
        }

    }
}
