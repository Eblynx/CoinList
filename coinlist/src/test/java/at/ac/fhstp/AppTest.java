package at.ac.fhstp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

public class AppTest 
{


    @Test
    public void checkAPIKey_checkAPI() throws URISyntaxException, IOException, InterruptedException
    {
        HttpResponse<String> res = HttpClientToken.httpStart("price", "EUR", 5);
        assertThat(res.statusCode(), equalTo(HttpStatus.SC_OK));
        System.out.println("API und API-Key funktionieren korrekt");
    }


}