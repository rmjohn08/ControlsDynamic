
package net.rmj.android.controlsdinamic;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;
import com.cri.android.app.AlertDialogUtils;

/**
 * Created by Ronaldo on 9/8/2014.
 */
public class MainApplication extends Application {

    private static MainApplication instance;
    private RequestQueue requestQueue;

    public static final String TAG = "VolleyPatterns";

    private AlertDialog progress =null;


    private static Context mContext;

    public MainApplication()
    {
        super();

    }
    @Override
    public void onCreate(){
        super.onCreate();
        //instance = this;
        mContext = getApplicationContext();

    }

    public static Context getContext() {
        return mContext;
    }

    public static synchronized MainApplication getInstance() {
        if (instance==null) instance = new MainApplication();
        return instance;
    }


    public RequestQueue getRequestQueue(){

        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(getContext());
        }

        return requestQueue;

    }

    public <T> void addToRequestQueue(Request<T> req, String tag)
    {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        VolleyLog.d("Adding request to queue:  %s", req.getUrl());
        getRequestQueue().add(req);

    }

    public <T> void addToRequestQueue(Request<T> req){
        req.setTag(TAG);
        getRequestQueue().add(req);

    }

    public void cancelPendingRequests(Object tag){
        if(requestQueue != null) {
            requestQueue.cancelAll(TAG);

        }

    }

    public void showSpinner(Activity ac){

        if (progress==null)
            progress = AlertDialogUtils.createProgressDialog(ac);

        progress.show();
    }

    public void hideSpinner() {
        progress.dismiss();
    }


}
