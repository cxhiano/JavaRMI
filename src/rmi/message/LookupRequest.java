package rmi.message;

public class LookupRequest extends Request {

    /**
	 * 
	 */
    private static final long serialVersionUID = 6700342405839325673L;

    public String key;
    
    public static final String TOKEN = "Lookup";

    public LookupRequest() {
    }

    public LookupRequest(String key) {
        this.key = key;
    }
    
    @Override
    public String getToken() {
        return TOKEN;
    }
}
