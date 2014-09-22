package rmi.core;

import java.io.IOException;

import rmi.net.SocketServer;

/**
 * Serve a remote object for remote method invocation
 *
 * @author Chao Xin, Chao Zhang
 */
public class RemoteObjectServer {
    /**
     * Serve the remote object on given port.
     *
     * @param obj
     * @param ref The reference to the remote object
     * @param port
     */
    public static void serveObject(Remote obj, RemoteObjectRef ref, int port)
            throws IOException {
        SocketServer server = SocketServer.getServer(port);
        server.bindHandler(ref.getKey(),
                new RemoteObjectServerHandler(obj, ref));
    }
}
