package rmi.message;

/**
 * 
 * Response sent from object server to client to return invocation result
 * 
 * @author Chao
 *
 */
public class InvokeResponse extends Response {

    /**
	 * 
	 */
    private static final long serialVersionUID = -522358804782183527L;

    public Object result;
}
