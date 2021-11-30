package at.ac.fhstp;

import java.util.Comparator;

import org.json.JSONException;
import org.json.JSONObject;

public class SortingToken implements Comparator<JSONObject> {

    
    private String type;
    private String waehrung;

    public SortingToken(String type, String waehrung) {
        this.type = type;
        this.waehrung = waehrung;
    }
    
    
    @Override
    public int compare(JSONObject array1, JSONObject array2) {
        double d1 = array1.getJSONObject("quote").getJSONObject(waehrung).getDouble(type);
        double d2 = array2.getJSONObject("quote").getJSONObject(waehrung).getDouble(type);
        
        try {
            return Double.compare(d2, d1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
       return 0;

    }   
}