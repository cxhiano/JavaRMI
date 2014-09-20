package rmi.core;

public class RemoteObjectReference {
	public String host;
	public int port;
	public String key;
	public String stubName;

	public RemoteObjectReference() {
		
	}

	/**
	 * 
	 * @return a stub object
	 */
	public Object localize() {
		return null;
	}
}
