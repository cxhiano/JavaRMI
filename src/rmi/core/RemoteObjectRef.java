package rmi.core;

import java.io.Serializable;

public class RemoteObjectRef implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 3625861985375739675L;
	public String host;
	public int port;
	public String key;
	public String interfaceName;

	public RemoteObjectRef() {

	}

	public RemoteObjectRef(String host, int port, Remote remote) {
		this.host = host;
		this.port = port;
		this.key = String.format("%s:%d/%s@%s", host, port, interfaceName,
				System.identityHashCode(remote));
		this.interfaceName = remote.getClass().getName();
	}

	public RemoteObjectRef(String host, int port, String key,
			String interfaceName) {
		this.host = host;
		this.port = port;
		this.key = key;
		this.interfaceName = interfaceName;
	}

	public String getKey() {
		return this.key;
	}

}
