package ca.ubc.cs.cpsc210.meetup.parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ca.ubc.cs.cpsc210.meetup.model.EatingPlace;
import ca.ubc.cs.cpsc210.meetup.model.Place;
import ca.ubc.cs.cpsc210.meetup.model.PlaceFactory;
import ca.ubc.cs.cpsc210.meetup.util.LatLon;

/**
 * Created by Yu-Tang Clan on 2015-03-26.
 */
public class FourSquareParser {
    List<EatingPlace> eatingPlaces = new ArrayList<EatingPlace>();

    public void parse(String jSONOfPlaces) {
        try {
            JSONObject stringToParse = new JSONObject(jSONOfPlaces);
            JSONObject response = stringToParse.getJSONObject("response");
            JSONArray groups = response.getJSONArray("groups");
            for (int i = 0; i < groups.length(); i++) {
                JSONObject group = groups.getJSONObject(i);
                JSONArray items = group.getJSONArray("items");
                for (int j = 0; j < items.length(); j++) {
                    JSONObject item = items.getJSONObject(j);
                    JSONObject venue = item.getJSONObject("venue");
                    String name = venue.getString("name");
                    JSONObject location = venue.getJSONObject("location");
                    Double lat = location.getDouble("lat");
                    Double lon = location.getDouble("lng");
                    LatLon latlon = new LatLon(lat,lon);
                    EatingPlace eatingPlace = new EatingPlace(name, latlon);
                    PlaceFactory.getInstance().add(eatingPlace);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
