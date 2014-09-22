package rmi.core;

import java.io.IOException;

import rmi.net.SocketServer;

public class RemoteObjectServer {
    public static void serveObject(Remote obj, RemoteObjectRef ref, int port)
            throws IOException {
        SocketServer server = SocketServer.getServer(port);
        server.bindHandler(ref.getKey(),
                new RemoteObjectServerHandler(obj, ref));
    }
}
