package rmi.message;

/**
 * Request sent from client to {@link rmi.registry.RegistryServer} lookup the stub by specified name
 * 
 * @author Chao
 *
 */
public class LookupRequest extends Request {

    /**
	 * 
	 */
    private static final long serialVersionUID = 6700342405839325673L;

    public String name;

    public static final String TOKEN = "Lookup";

    public LookupRequest() {
    }

    public LookupRequest(String name) {
        this.name = name;
    }

    @Override
    public String getToken() {
        return TOKEN;
    }
}
