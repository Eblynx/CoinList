package at.ac.fhstp;

import java.net.URISyntaxException;
import java.util.stream.IntStream;
import org.json.JSONArray;
import org.json.JSONObject;

class App {

    //API Schnittstelle
    private static final String mainurl = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";

    public static void main(String[] args) throws URISyntaxException
    {       
        //Definierung der Forderungen in Variablen
        String price = "price", type = "tokens", percent_change_24h = "percent_change_24h", limit = "5", currency = "EUR";
        
        //Zusammenbau der API-Url
        URL url = new URLBuilder(mainurl).type(type).currency(currency).sort(price).limit(limit).build();

        //JSON Array durch die Abfrage der API (bekommt ein JSON String zurück)
        JSONArray jarray = HttpClientToken.httpJSONArray(url.getFinalURL());
        
        //Ausgabe der jeweiligen Soriterung via API
        System.out.println("=====Kurswert[EUR]-Top"+limit+"=====");     
        printlist(jarray, price, currency);

        //Andere Sortierung des aktuellen geladenen JSON Array
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


