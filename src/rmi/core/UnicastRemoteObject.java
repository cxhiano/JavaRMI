package rmi.core;

import java.lang.reflect.Proxy;
import java.io.IOException;

/**
 * UnicastRemoteObject
 *
 * @author Chao
 *
 */
public class UnicastRemoteObject {
    private static Remote generateStub(Remote obj, RemoteObjectRef ref) {
        RemoteInvocationHandler handler = new RemoteInvocationHandler(ref);
        Remote proxy = (Remote) Proxy.newProxyInstance(
                            Remote.class.getClassLoader(),
                            obj.getClass().getInterfaces(),
                            handler);
        return proxy;
    }

    /**
     * Export a Remote obj to a given port, this will start a remote server
     *
     * @param obj
     * @param port
     * @return stub for obj
     */
    public static Remote export(Remote obj, int port) {
    	RemoteObjectRef ref = new RemoteObjectRef("localhost", port, null, obj.getClass().getName());

        try {
            RemoteObjectServer.serveObject(obj, ref, port);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return generateStub(obj, ref);
    }
}