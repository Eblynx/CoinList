package at.ac.fhstp;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

class App {

    public static void main(String[] args) throws URISyntaxException
    {
        System.out.println("=====Kurswert[€]-Top5=====");
        printList("price", "EUR");
        System.out.println("=====TopMover[%]-Top5=====");
        printList("percent_change_24h", "EUR");
  
    }
    
    
    public static void printList(String type, String waehrung) throws URISyntaxException
    {
        String apiKey = "ab1ef4d4-7ad7-4c7f-838c-d2d8b13112c0";
        URI uri = new URI("https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest?sort="+type+"&convert="+waehrung);
        
        try 
        {
            HttpRequest req = HttpRequest
            .newBuilder()
            .uri(uri)
            .header("X-CMC_PRO_API_KEY", apiKey)
            .GET()
            .build();
            
            HttpResponse<String> res = HttpClient
            .newBuilder()
            .build()
            .send(req, HttpResponse.BodyHandlers.ofString());

            JSONArray listorg = new JSONObject(res.body()).getJSONArray("data");



            for (int i=0; i < 5; ++i)
            {
                System.out.println("Name: " + listorg.getJSONObject(i).getString("name"));
                System.out.println(listorg.getJSONObject(i).getJSONObject("quote").getJSONObject(waehrung).getDouble(type));
            }
                

        } 
        
        catch (IOException e) 
        {
            e.printStackTrace();
        } 
        
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }

        
    }

}


