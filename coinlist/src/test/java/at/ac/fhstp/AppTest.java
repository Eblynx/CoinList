package at.ac.fhstp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.io.IOException;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;

public class AppTest {

    @Test
    public void checkAPIKey_checkAPI() throws ClientProtocolException, IOException 
    {
        final String apiKey = "ab1ef4d4-7ad7-4c7f-838c-d2d8b13112c0";
        CloseableHttpClient client = HttpClientBuilder.create().build();    
        HttpGet request = new HttpGet("https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest?cryptocurrency_type=tokens");
        request.addHeader("X-CMC_PRO_API_KEY", apiKey);
        CloseableHttpResponse response = client.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();
        assertThat(statusCode, equalTo(HttpStatus.SC_OK));
        System.out.println("API und API-Key funktionieren korrekt");
    }


}