package rmi.registry;

import java.util.List;

import rmi.core.Remote;
import rmi.message.ListRequest;
import rmi.message.ListResponse;
import rmi.message.LookupRequest;
import rmi.message.LookupResponse;
import rmi.message.RebindRequest;
import rmi.util.SocketHelper;
import rmi.registry.exception.*;

public class Registry {
	public static final int DEFAULT_PORT = 15640;
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
	public Remote lookup(String key) throws StubNotFoundException {
		LookupResponse resp = (LookupResponse) SocketHelper.request(host,
								port, new LookupRequest(key));

		if (!LookupResponse.valid(resp))
			throw resp.e;
		return resp.stub;
	}

	/**
	 *
	 * @param key
	 * @param ref
	 */
	public void rebind(String key, Remote stub) {
		SocketHelper.request(host,
				port, new RebindRequest(key, stub));
	}

	/**
	 *
	 * @return
	 */
	public List<String> list() {
		ListResponse resp = (ListResponse) SocketHelper.request(host,
				port, new ListRequest());
		if (resp != null)
			return resp.keys;
		return null;
	}
}
