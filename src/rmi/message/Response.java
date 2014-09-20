package rmi.message;

public class Response extends Message {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2957626965046466961L;
	
	public boolean ok;
	
	public Response() {
		this.ok = false;
	}
}
