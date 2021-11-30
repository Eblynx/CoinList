package at.ac.fhstp;

import java.net.URISyntaxException;
import java.util.stream.IntStream;
import org.json.JSONArray;
import org.json.JSONObject;

class App {

    public static void main(String[] args) throws URISyntaxException
    {       
        String price = "price";
        String currency = "EUR";
        String percent_change_24h = "percent_change_24h";
        int limit = 150;
        
        JSONArray jarray = HttpClientCoin.httpStart(price, currency, limit);

        System.out.println("=====Kurswert[EUR]-Top"+limit+"=====");      
        printlist(jarray, price, currency);
        
        System.out.println("=====TopMover[%]-Top"+limit+"=====");
        sort(jarray, percent_change_24h, currency);
    }

    public static void printlist(JSONArray jarray, String type, String currency) throws URISyntaxException
    {       
        for(int i=0; i < jarray.length(); ++i)
        {
            System.out.print("Token: "+jarray.getJSONObject(i).getString("name")+" | "+type+": ");
            System.out.println(jarray.getJSONObject(i).getJSONObject("quote").getJSONObject(currency).getDouble(type));
        }
    }
    
    public static void sort(JSONArray jarray, String type, String currency) throws URISyntaxException
    {       
        IntStream.range(0, jarray.length())
        .mapToObj(i -> (JSONObject) jarray.getJSONObject(i))
        .sorted((i,j)-> Double.compare(j.getJSONObject("quote").getJSONObject(currency).getDouble(type), i.getJSONObject("quote").getJSONObject(currency).getDouble(type)))
        .forEach(i -> 
        {
            System.out.print("Token: "+i.getString("name")+" | "+type+": ");
            System.out.println(i.getJSONObject("quote").getJSONObject(currency).getDouble(type));
        });
    }
       
}


