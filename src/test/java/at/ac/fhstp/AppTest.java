package at.ac.fhstp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;


import org.apache.http.HttpStatus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppTest 
{
   @Test
    public void checkAPIKey_checkAPI() throws URISyntaxException, IOException, InterruptedException
    {
        final String url = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
        HttpResponse<String> res = HttpClientToken.httpStart(url);
        assertThat(res.statusCode(), equalTo(HttpStatus.SC_OK));
        System.out.println("API und API-Key funktionieren korrekt");
    }

    @Test
    public void ExceptionThrown() 
    {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
         URL url = new URLBuilder("").build();    
         
        });
        
        Assertions.assertEquals("URI with undefined scheme", exception.getMessage());
        System.out.println("URI with undefined scheme"); 
    } 

    @Test
    public void ExceptionThrownURL() 
    {
        Exception exception = Assertions.assertThrows(URLFailedException.class, () -> {
         URL url = new URLBuilder("https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest").limit("a").build();    
         
        });
         
        Assertions.assertEquals("URL not valid", exception.getMessage());
        System.out.println("URL not valid");
    } 

}
