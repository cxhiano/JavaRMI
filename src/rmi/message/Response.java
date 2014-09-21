package rmi.message;

/**
 * Abstraction of Response in RMI framework
 *
 * @author Chao
 *
 */
public class Response extends Message {

    /**
	 *
	 */
    private static final long serialVersionUID = -2957626965046466961L;

    public RuntimeException e;

    public Response() {
        this.e = null;
    }

    public static boolean valid(Response response) {
        return response != null && response.e == null;
    }

    public static void throwIfInvalid(Response response) {
        if (!valid(response))
            throw response.e;
    }
}
