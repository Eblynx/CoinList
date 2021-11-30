package at.ac.fhstp;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


class App {

    public static void main(String[] args) throws URISyntaxException
    {       
        String price = "price";
        String waehrung = "EUR";
        String percent_change_24h = "percent_change_24h";
        int limit = 5;
        
        JSONArray list = HttpClientCoin.httpStart(price, waehrung, limit);

        System.out.println("=====Kurswert[EUR]-Top5=====");      
        printlist(list, price, waehrung);
        
        list = sortTopMover(list, percent_change_24h, waehrung);
        System.out.println("=====TopMover[%]-Top5=====");
        printlist(list, percent_change_24h, waehrung);
          
    }

    public static void printlist(JSONArray list, String type, String waehrung) throws URISyntaxException
    {
        for(int i=0; i < list.length(); ++i)
        {
            System.out.print("Token: "+list.getJSONObject(i).getString("name")+" | "+type+": ");
            System.out.println(list.getJSONObject(i).getJSONObject("quote").getJSONObject(waehrung).getDouble(type));
        }
    }

    public static JSONArray sortTopMover(JSONArray array, String type, String waehrung) throws JSONException {
        List<JSONObject> list = new ArrayList<JSONObject>();
        for (int i = 0; i < array.length(); i++) 
        {
                list.add(array.getJSONObject(i));
        }
        Collections.sort(list, new SortingCoin(type, waehrung));

        JSONArray resultArray = new JSONArray(list);

        return resultArray;
    }
    
    
    

}


