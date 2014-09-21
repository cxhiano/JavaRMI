package rmi.registry.exception;

/**
 * Remote Exception
 * 
 * @author Chao
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
