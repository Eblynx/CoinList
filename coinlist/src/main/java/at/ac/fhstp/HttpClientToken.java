package at.ac.fhstp;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpClientToken 
{
    private static final String apiKey = "ab1ef4d4-7ad7-4c7f-838c-d2d8b13112c0";


    public static HttpResponse<String> httpStart(String url) throws URISyntaxException
    {    
        URI uri = new URI(url);
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

            return res;
        } 
        
        catch (IOException e) 
        {
            e.printStackTrace();
        } 
        
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
        
        return null;
    }

    public static JSONArray httpJSONArray(String url) throws JSONException, URISyntaxException
    {
        JSONArray listorg = new JSONObject(httpStart(url).body()).getJSONArray("data");
        return listorg;
    }
    
}
