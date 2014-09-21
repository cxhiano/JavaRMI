package rmi.net;

public class MissingHandlerException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -554029142504970268L;

    public MissingHandlerException() {
    }

    public MissingHandlerException(String message) {
        super(message);
    }
}
