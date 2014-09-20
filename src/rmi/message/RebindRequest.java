package rmi.message;

import rmi.core.Remote;

public class RebindRequest extends Request {

	/**
	 * 
	 */
	private static final long serialVersionUID = -836045415455500522L;

	public String key;
	public Remote stub;

	public RebindRequest() {
	}

	public RebindRequest(String key, Remote stub) {
		this.key = key;
		this.stub = stub;
	}
}