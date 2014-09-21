package rmi.registry.exception;

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
