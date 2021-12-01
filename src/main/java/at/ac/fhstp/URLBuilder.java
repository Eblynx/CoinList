package at.ac.fhstp;

import java.net.URISyntaxException;
import java.net.http.HttpResponse;

public class URLBuilder 
{
    private final String url;
    private String type = "";
    private String sort = "";
    private String currency = "";
    private String limit = "";

    public URLBuilder(String url) 
    {
        this.url = url;
    }

    public URLBuilder type(String type)
    {
        this.type = "&cryptocurrency_type="+type;
        return this;
    }

    public URLBuilder sort(String sort)
    {
        this.sort = "&sort="+sort;
        return this;
    }

    public URLBuilder currency(String currency)
    {
        this.currency = "&convert="+currency;
        return this;
    }

    public URLBuilder limit(String limit)
    {
        this.limit = "&limit="+limit;
        return this;
    }

    public URL build() throws URISyntaxException {
        URL uri =  new URL(url+"?", type, sort, currency, limit);
        validateURL(uri.getFinalURL());
        return uri;
    }

    public void validateURL(String checkurl) throws URISyntaxException 
    {
        HttpResponse<String> res = HttpClientToken.httpStart(checkurl);
        if(res.statusCode()!=200)
        {
            System.err.println("Invalid URL");
            //to do exception
        }
    }
    
}
