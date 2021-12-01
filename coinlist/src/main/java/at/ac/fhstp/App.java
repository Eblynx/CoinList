package at.ac.fhstp;

import java.net.URISyntaxException;
import java.util.stream.IntStream;
import org.json.JSONArray;
import org.json.JSONObject;



class App {

    private static final String mainurl = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";

    public static void main(String[] args) throws URISyntaxException
    {       
        String price = "price";
        String type = "tokens";
        String percent_change_24h = "percent_change_24h";
        String limit = "5";
        String currency = "EUR";
        
        URL url = new URLBuilder(mainurl).type(type).currency(currency).sort(price).limit(limit).build();

        JSONArray jarray = HttpClientToken.httpJSONArray(url.getURL());
        
        System.out.println("=====Kurswert[EUR]-Top"+limit+"=====");     
        printlist(jarray, price, currency);

        System.out.println("=====TopMover[%]-Top"+limit+"=====");
        sort(jarray, percent_change_24h, currency);
    }

    public static void printlist(JSONArray jarray, String sort, String currency) throws URISyntaxException
    {       
        for(int i=0; i < jarray.length(); ++i)
        {
            System.out.print("Token: "+jarray.getJSONObject(i).getString("name")+" | "+sort+": ");
            System.out.println(jarray.getJSONObject(i).getJSONObject("quote").getJSONObject(currency).getDouble(sort));
        }
    }
    
    public static void sort(JSONArray jarray, String sort, String currency) throws URISyntaxException
    {       
        IntStream.range(0, jarray.length())
        .mapToObj(i -> (JSONObject) jarray.getJSONObject(i))
        .sorted((i,j)-> Double.compare(j.getJSONObject("quote").getJSONObject(currency).getDouble(sort), i.getJSONObject("quote").getJSONObject(currency).getDouble(sort)))
        .forEach(i -> 
        {
            System.out.print("Token: "+i.getString("name")+" | "+sort+": ");
            System.out.println(i.getJSONObject("quote").getJSONObject(currency).getDouble(sort));
        });
    }
       
}


