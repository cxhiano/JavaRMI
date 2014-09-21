package rmi.message;

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
