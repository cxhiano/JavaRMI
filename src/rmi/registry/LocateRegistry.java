package rmi.registry;

import rmi.message.AuthRequest;
import rmi.message.AuthResponse;
import rmi.net.SocketRequest;

/**
 * LocateRegistry is used to obtain a reference to a registry object on a 
 * specific port. Note that a getRegistry call will send AuthRequest
 * 
 * @author Chao
 *
 */
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
        AuthResponse resp = (AuthResponse) SocketRequest.request(host, port,
                new AuthRequest());
        if (resp != null)
            return new Registry(host, port);
        return null;
    }
}
