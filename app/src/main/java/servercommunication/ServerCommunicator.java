
package servercommunication;

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

import Utils.UtilClass;
import model.ResponseListener;

public class ServerCommunicator {

    private Context context;
    ResponseListener responseInterface;

    public ServerCommunicator(Context context, ResponseListener responseInterface) {
        this.context = context;
        this.responseInterface = responseInterface;
    }

    public void makeGetRequest( final Activity mContext, final String url, final int tag, final Class responseClass) {
        try {

            context = mContext;
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
                                        Log.e("ServerCommunicator", e.toString());
                                        VolleyError error1 = new VolleyError();
                                        error1.initCause(e);
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
                                        Log.e("ServerCommunicator", e.toString());
                                    }
                                }
                            }
                    ) {


            };

            jsonRequest.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            Volley.newRequestQueue(mContext).add(jsonRequest);

        } catch (OutOfMemoryError e) {
            responseInterface.onFailure(new VolleyError(), tag);
            Log.e("ServerCommunicator", e.toString());
        } catch (Exception e) {
            responseInterface.onFailure(new VolleyError(), tag);
            Log.e("ServerCommunicator", e.toString());
        }
    }


}
