package rmi.message;

import rmi.core.Remote;

public class RebindRequest extends Request {

    /**
	 * 
	 */
    private static final long serialVersionUID = -836045415455500522L;
    public static final String TOKEN = "Rebind";

    public String key;
    public Remote stub;

    public RebindRequest() {
    }

    public RebindRequest(String key, Remote stub) {
        this.key = key;
        this.stub = stub;
    }

    @Override
    public String getToken() {
        return TOKEN;
    }
}