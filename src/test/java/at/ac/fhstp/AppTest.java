package at.ac.fhstp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.json.HTTP;

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
        Exception exception = assertThrows(NumberFormatException.class, () -> 
        {
            Integer.parseInt("1a");
        });
        String expectedMessage = "For input string";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        System.out.println(expectedMessage);
    } 

    @Test
    void exceptionTesting() 
    {
        String httpStr = null;
        NullPointerException thrown = assertThrows(
            NullPointerException.class,
            () -> HTTP.toJSONObject(httpStr),
            "Expected doThing() to throw, but it didn't"
        );
        System.out.println("Inhalt der JSON Datei ist "+ httpStr);

        assertTrue(thrown.getMessage().contains("null"));
    } 
}
