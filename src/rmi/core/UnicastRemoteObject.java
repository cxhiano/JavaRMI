package rmi.core;

import java.lang.reflect.Proxy;
import java.io.IOException;

/**
 * Used for exporting a remote object and obtaining its client-side stub. On
 * client-side, all * method invocations of object obj are intercepted. Instead
 * of invoking the 'real' method, the proxy forwards the data associated with
 * the invocation to the host where the 'real' object is served.
 *
 * @author Chao Xin, Chao Zhang
 *
 */
public class UnicastRemoteObject {
   /**
     * Generate the stub for object obj by using Proxy.
     *
     * @param obj
     * @param ref
     * @return Stub for obj
     */
    private static Remote generateStub(Remote obj, RemoteObjectRef ref) {
        RemoteInvocationHandler handler = new RemoteInvocationHandler(ref);
        Remote proxy = (Remote) Proxy.newProxyInstance(
                Remote.class.getClassLoader(), obj.getClass().getInterfaces(),
                handler);
        return proxy;
    }

    /**
     * Export a Remote obj to a given port and start a remote server at port
     *
     * @param obj
     * @param port
     * @return stub for obj
     */
    public static Remote export(Remote obj, String host, int port) {
        RemoteObjectRef ref = new RemoteObjectRef(host, port, obj);
        try {
            RemoteObjectServer.serveObject(obj, ref, port);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return generateStub(obj, ref);
    }
    /**
     * Export a Remote obj to a given port and start a remote server at port
     *
     * @param obj
     * @param port
     * @return stub for obj
     */
    public static Remote export(Remote obj, int port) {
        return export(obj, "localhost", port);
    }
}