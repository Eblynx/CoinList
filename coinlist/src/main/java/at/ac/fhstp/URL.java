package at.ac.fhstp;

public class URL 

{
    private final String url;
    private final String type;
    private final String sort;
    private final String currency;
    private final String limit;
    

    public URL(String url, String type, String sort, String currency, String limit) {
        this.url = url;
        this.type = type;
        this.sort = sort;
        this.currency = currency;
        this.limit = limit;
    }

    public String getURL()
    {
        String uri = url+type+currency+sort+limit;
        return uri;
    }

}
