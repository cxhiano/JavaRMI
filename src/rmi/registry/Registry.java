package rmi.registry;

import rmi.core.Remote;
import rmi.core.SocketHandler;
import rmi.message.LookupRequest;
import rmi.message.LookupResponse;
import rmi.message.RebindRequest;

public class Registry {
	public static final int DEFAULT_PORT = 18814;
	public static final String DEFAULT_HOST = "localhost";
	
	public String host;
	public int port;

	public Registry(String host, int port) {
		this.host = host;
		this.port = port;
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public Remote lookup(String key) {
		LookupResponse resp = (LookupResponse) SocketHandler.request(host,
				port, new LookupRequest(key));
		if (resp != null)
			return resp.stub;
		return null;
	}

	/**
	 * 
	 * @param key
	 * @param ref
	 */
	public void rebind(String key, Remote stub) {
		SocketHandler.request(host,
				port, new RebindRequest(key, stub));
	}
}
