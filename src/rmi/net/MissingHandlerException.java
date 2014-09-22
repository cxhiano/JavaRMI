package rmi.net;

/**
 * This exception will be raised when the server cannot find a handler to
 * handle a request.
 *
 * @author Chao Xin, Chao Zhang
 */
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
