package rmi.message;

/**
 * Abstraction of Request in RMI framework
 *
 * @author Chao
 *
 */
public class Request extends Message {

	/**
	 *
	 */
	private static final long serialVersionUID = 8242580035359822066L;

    protected String token;

    public Request(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }


}
