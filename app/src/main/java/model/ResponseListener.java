package model;

import com.android.volley.VolleyError;

/**
 * Created by Avinash on 8/28/2018.
 */
public interface ResponseListener {

    public void onSuccess(Object obj, int tag);
    public void onFailure(VolleyError error, int tag);
}
