package rmi.message;

import rmi.core.RemoteException;

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
	
	public boolean ok;
	
	public Response() {
		this.ok = false;
	}
	
	public static boolean valid(Response response) {
		return response != null && response.ok;
	}
	
	public static void raiseIfInvalid(Response response) {
		if (!valid(response))
			throw new RemoteException("Cannot talk to registry.");
	}
}
