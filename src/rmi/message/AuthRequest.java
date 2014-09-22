package rmi.message;

/**
 * 
 * Authentication request sent from client to {@link rmi.registry.RegistryServer} acknowledge
 * registry server's existence
 * 
 * @author Chao
 *
 */
public class AuthRequest extends Request {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1053935546768634051L;

    public static final String TOKEN = "Auth";

    @Override
    public String getToken() {
        return TOKEN;
    }
}
