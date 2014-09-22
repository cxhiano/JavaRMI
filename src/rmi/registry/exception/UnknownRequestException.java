package rmi.registry.exception;

/**
 * Exception representing a request is unkown or invalid
 * 
 * @author Chao
 *
 */
public class UnknownRequestException extends RuntimeException {
    /**
	 * 
	 */
    private static final long serialVersionUID = -554029142504970268L;

    public UnknownRequestException() {
    }

    public UnknownRequestException(String message) {
        super(message);
    }

}
