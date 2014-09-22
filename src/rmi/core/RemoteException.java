package rmi.core;

/**
 * This exception will be raised when failing to invoke a remote method
 *
 * @author Chao Xin, Chao Zhang
 *
 */
public class RemoteException extends RuntimeException {

    /**
	 *
	 */
    private static final long serialVersionUID = 5775213995740956172L;

    public RemoteException() {

    }

    public RemoteException(String message) {
        super(message);
    }

}
