package at.ac.fhstp;

import java.net.URISyntaxException;

import org.json.JSONArray;


class App {

    public static void main(String[] args) throws URISyntaxException
    {
        
       
        System.out.println("=====Kurswert[€]-Top5=====\n");
        printlist("price", "EUR");

        
        

        //System.out.println("=====TopMover[%]-Top5=====");

  
    }



    public static void printlist(String type, String waehrung) throws URISyntaxException
    {
        
        JSONArray list = HttpClientCoin.httpStart(type, waehrung);
        for(int i=0; i < 5; ++i)
        {
            System.out.println(list.getJSONObject(i).getJSONObject("quote").getJSONObject(waehrung).getDouble(type));
        }
    }
    
    
    

}


