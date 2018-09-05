
package com.androidproject.servercommunication;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import com.androidproject.utils.UtilClass;
import com.androidproject.model.ResponseListener;

public class ServerCommunicator {

    private ResponseListener responseInterface;
    private static final String TAG = ServerCommunicator.class.getSimpleName();

    public ServerCommunicator(Context context, ResponseListener responseInterface) {
        this.responseInterface = responseInterface;
    }

    public void makeGetRequest( final Activity mContext, final String url, final int tag, final Class responseClass) {
        try {

            JsonObjectRequest jsonRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {

                                        responseInterface.onSuccess(
                                                UtilClass.parseJson(response.toString(),
                                                        responseClass), tag);
                                    } catch (Exception e) {
                                        Log.e(TAG, e.toString());
                                        VolleyError error1 = new VolleyError();
                                        responseInterface.onFailure(error1, tag);

                                    }
                                }
                            },
                            new Response.ErrorListener() {

                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    try {
                                        error.printStackTrace();
                                        responseInterface.onFailure(error, tag);
                                    } catch (Exception e) {
                                        Log.e(TAG, e.toString());
                                    }
                                }
                            }
                    ) {


            };

            jsonRequest.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            Volley.newRequestQueue(mContext).add(jsonRequest);

        } catch (OutOfMemoryError e) {
            responseInterface.onFailure(new VolleyError(), tag);
            Log.e(TAG, e.toString());
        } catch (Exception e) {
            responseInterface.onFailure(new VolleyError(), tag);
            Log.e(TAG, e.toString());
        }
    }


}
