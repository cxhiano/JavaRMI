package rmi.registry;

import rmi.core.SocketHandler;
import rmi.message.AuthRequest;
import rmi.message.AuthResponse;

public class LocateRegistry {
	
	public static Registry getRegistry() {
		return getRegistry(Registry.DEFAULT_HOST, Registry.DEFAULT_PORT);
	}
	
	public static Registry getRegistry(int port) {
		return getRegistry(Registry.DEFAULT_HOST, port);
	}
	
	public static Registry getRegistry(String host) {
		return getRegistry(host, Registry.DEFAULT_PORT);
	}
	
	public static Registry getRegistry(String host, int port) {
		AuthResponse resp = (AuthResponse)SocketHandler.request(host, port, new AuthRequest());
		if (resp != null)
			return new Registry(host, port);
		return null;
	}
}
