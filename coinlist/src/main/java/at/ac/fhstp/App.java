package at.ac.fhstp;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

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
        
        JSONArray jarray = HttpClientCoin.httpStart(price, waehrung, limit);

        System.out.println("=====Kurswert[EUR]-Top"+limit+"=====");      
        printlist(jarray, price, waehrung);
        
        jarray = sortTopMover(jarray, percent_change_24h, waehrung);
        System.out.println("=====TopMover[%]-Top"+limit+"=====");
        printlist(jarray, percent_change_24h, waehrung);
          
    }

    public static void printlist(JSONArray jarray, String type, String waehrung) throws URISyntaxException
    {       
        IntStream.range(0, jarray.length())
        .mapToObj(i -> (JSONObject) jarray.getJSONObject(i))
        .forEach(i -> 
        {
            System.out.print("Token: "+i.getString("name")+" | "+type+": ");
            System.out.println(i.getJSONObject("quote").getJSONObject(waehrung).getDouble(type));
        });
        
        /*
        for(int i=0; i < list.length(); ++i)
        {
            System.out.print("Token: "+list.getJSONObject(i).getString("name")+" | "+type+": ");
            System.out.println(list.getJSONObject(i).getJSONObject("quote").getJSONObject(waehrung).getDouble(type));
        }*/
    }
    public static void filter(JSONArray jarray, String type, String waehrung) throws URISyntaxException
    {       
        IntStream.range(0, jarray.length())
        .mapToObj(i -> (JSONObject) jarray.getJSONObject(i))
        .sorted(Comparator.comparingDouble(keyExtractor))
        .forEach(i -> System.out.println(i.getJSONObject("quote").getJSONObject(waehrung).getDouble(type)));
        
        /*
        for(int i=0; i < list.length(); ++i)
        {
            System.out.print("Token: "+list.getJSONObject(i).getString("name")+" | "+type+": ");
            System.out.println(list.getJSONObject(i).getJSONObject("quote").getJSONObject(waehrung).getDouble(type));
        }
    }*/


    public static JSONArray sortTopMover(JSONArray jarray, String type, String waehrung) throws JSONException {
        List<JSONObject> list = new ArrayList<JSONObject>();
        for (int i = 0; i < jarray.length(); i++) 
        {
                list.add(jarray.getJSONObject(i));
        }
        Collections.sort(list, new SortingToken(type, waehrung));

        JSONArray resultArray = new JSONArray(list);

        return resultArray;
    }

    
}


