package at.ac.fhstp;

import java.net.URISyntaxException;
import java.util.stream.IntStream;
import org.json.JSONArray;
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
        
        System.out.println("=====TopMover[%]-Top"+limit+"=====");
        filter(jarray, percent_change_24h, waehrung);
 
          
    }

    public static void printlist(JSONArray jarray, String type, String waehrung) throws URISyntaxException
    {       
        for(int i=0; i < jarray.length(); ++i)
        {
            System.out.print("Token: "+jarray.getJSONObject(i).getString("name")+" | "+type+": ");
            System.out.println(jarray.getJSONObject(i).getJSONObject("quote").getJSONObject(waehrung).getDouble(type));
        }
    }
    
    public static void filter(JSONArray jarray, String type, String waehrung) throws URISyntaxException
    {       
        IntStream.range(0, jarray.length())
        .mapToObj(i -> (JSONObject) jarray.getJSONObject(i))
        .sorted((i,j)-> Double.compare(j.getJSONObject("quote").getJSONObject(waehrung).getDouble(type), i.getJSONObject("quote").getJSONObject(waehrung).getDouble(type)))
        .forEach(i -> 
        {
            System.out.print("Token: "+i.getString("name")+" | "+type+": ");
            System.out.println(i.getJSONObject("quote").getJSONObject(waehrung).getDouble(type));
        });
    }
       
}


