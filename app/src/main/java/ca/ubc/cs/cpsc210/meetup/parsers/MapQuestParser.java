package ca.ubc.cs.cpsc210.meetup.parsers;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yu-Tang Clan on 2015-03-19.
 */
public class MapQuestParser {
    String name;
    List<GeoPoint> geoPointList = new ArrayList<GeoPoint>();
    double lat;
    double lon;

    public List<GeoPoint> parse(String input) {


        try {
            JSONObject toParse = new JSONObject(input);
            Log.i("********************************toParse",toParse.toString());
            JSONObject route = toParse.getJSONObject("route");
            Log.i("********************************route",route.toString());
            JSONArray legs = route.getJSONArray("legs");
            Log.i("********************************legs", Integer.toString(legs.length()));
            for (int i = 0; i < legs.length(); i++) {
                JSONArray maneuvers = legs.getJSONObject(i).getJSONArray("maneuvers");
                Log.i("********************************maneuver # " + Integer.toString(i),legs.getJSONObject(i).toString());
                Log.i("................................maneuver length", Integer.toString(maneuvers.length()));
                for (int j = 0; j < maneuvers.length(); j++) {
                    Log.i("********************************maneuver # " + Integer.toString(j),maneuvers.getJSONObject(j).toString());
                    JSONObject maneuver = maneuvers.getJSONObject(j);
                    Log.i("********************************starting point", maneuver.getJSONObject("startPoint").toString());
                    JSONObject startPoint = maneuver.getJSONObject("startPoint");
                    double lon = startPoint.getDouble("lng");
                    double lat = startPoint.getDouble("lat");
                    GeoPoint latlon = new GeoPoint(lat, lon);

                    geoPointList.add(latlon);
                }
                Log.d("How many geoPoints you got mang!?", Integer.toString(geoPointList.size()));
                return geoPointList;
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
