package rmi.registry;

import rmi.core.Constants;
import rmi.message.AuthRequest;
import rmi.message.AuthResponse;
import rmi.net.SocketRequest;

/**
 * LocateRegistry is used to obtain a reference to a registry object on a
 * specific port. Note that a <code> getRegistry() </code> call will send
 * AuthRequest and expect to receive AuthResponse to acknowlege registry
 * server's existence.
 * 
 * @author Chao
 *
 */
public class LocateRegistry {

    public static Registry getRegistry() {
        return getRegistry(Constants.DEFAULT_HOST,
                Constants.DEFAULT_REGISTRY_PORT);
    }

    public static Registry getRegistry(int port) {
        return getRegistry(Constants.DEFAULT_HOST, port);
    }

    public static Registry getRegistry(String host) {
        return getRegistry(host, Constants.DEFAULT_REGISTRY_PORT);
    }

    public static Registry getRegistry(String host, int port) {
        AuthResponse resp = (AuthResponse) SocketRequest.request(host, port,
                new AuthRequest());
        if (resp != null)
            return new Registry(host, port);
        return null;
    }
}
