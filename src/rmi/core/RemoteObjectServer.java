package rmi.core;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import rmi.net.SocketServer;

public class RemoteObjectServer {
    private static Lock lock = new ReentrantLock();

    public static void serveObject(Remote obj, RemoteObjectRef ref, int port)
            throws IOException {
        SocketServer server = SocketServer.getServer(port);
        server.bindHandler(ref.getKey(),
                new RemoteObjectServerHandler(obj, ref));

        lock.lock();
        // Start the server if it's not yet started
        if (server.getState().equals(Thread.State.NEW))
            server.start();
        lock.unlock();
    }
}
