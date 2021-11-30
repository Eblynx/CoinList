package at.ac.fhstp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

public class AppTest {

    @Test
    public void checkAPIKey_checkAPI() throws URISyntaxException, IOException, InterruptedException
    {
        final String apiKey = "ab1ef4d4-7ad7-4c7f-838c-d2d8b13112c0";
       
        HttpRequest req = HttpRequest
            .newBuilder()
            .uri(new URI("https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest"))
            .header("X-CMC_PRO_API_KEY", apiKey)
            .GET()
            .build();
            
            HttpResponse<String> res = HttpClient
            .newBuilder()
            .build()
            .send(req, HttpResponse.BodyHandlers.ofString());

            res.statusCode();

        
        assertThat(res.statusCode(), equalTo(HttpStatus.SC_OK));
        System.out.println("API und API-Key funktionieren korrekt");
    }


}