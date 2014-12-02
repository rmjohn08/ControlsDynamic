package net.rmj.android.controlsdinamic.network;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import net.rmj.android.controlsdinamic.MainApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ronaldo on 9/8/2014.
 */
public class RequestReadSimpleQuake {

    public final String URL = "http://comcat.cr.usgs.gov/fdsnws/event/1/query?starttime=_startdate&minmagnitude=4.5&format=geojson";

    public static final String DETAIL_URL = "http://comcat.cr.usgs.gov/earthquakes/eventpage";

    private JSONArray features=null;

    private List<String> quakes;

    private SimpleDateFormat startdateFormat;  //2014-09-10
    private String timePart = "T0:00:00";

    public JsonObjectRequest makeReadSimpleQuake(final QuakesHandler handler)
    {
        startdateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        String startDate = startdateFormat.format(today)+timePart;
        String u = URL;
        u = u.replace("_startdate",startDate);
        String url = u;

        return new JsonObjectRequest(url,null,
                new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    MainApplication.getInstance().hideSpinner();
                    VolleyLog.v("Response got from: %s",URL);
                    features = response.getJSONArray("features");
                    if (features !=null)
                        Log.i("SimpleQuakeResponse","Features count :: "+ features.length());

                    quakes = new ArrayList<String>();
                    for(int i = 0; i<features.length(); i++)
                    {
                        try {
                            DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:SS");

                            JSONObject jsonObject = features.getJSONObject(i);
                            JSONObject properties = jsonObject.getJSONObject("properties");
                            Date time = new Date(properties.getLong("time"));
                            String eventId = jsonObject.getString("id");

                            String detail = "Magnitude:"+properties.getString("mag") + " Title: " + properties.getString("title") + " " + df.format(time) +
                                    " Event:" + eventId;

                            Log.i("SimpleQuakeResponse",detail);

                            quakes.add(detail);

                        } catch (Exception ee){
                            Log.i("SimpleQuakeResponse","Couldn't parse the time");

                        }
                    }
                    handler.handleQuakes(quakes);

                } catch(JSONException ex) {
                    ex.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error ", error.getMessage());
            }

        });

    }


}
