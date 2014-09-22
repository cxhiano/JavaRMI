package rmi.message;

/**
 * Request sent from client to {@link rmi.registry.RegistryServer} list all names
 * 
 * @author Chao
 *
 */
public class ListRequest extends Request {

    /**
	 * 
	 */
    private static final long serialVersionUID = 7787920866713686636L;
    public static final String TOKEN = "List";

    @Override
    public String getToken() {
        return TOKEN;
    }
}
