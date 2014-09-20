package rmi.core;

public class RemoteObjectReference {
	String host;
	int port;
	String key;
	String stubName;

	public RemoteObjectReference(String host, int port, String key,
			String stubName) {
		this.host = host;
		this.port = port;
		this.key = key;
		this.stubName = stubName;
	}

	/**
	 * 
	 * @return a stub object
	 */
	public Object localize() {
		return null;
	}
}
