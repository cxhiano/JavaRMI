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
     * This is equivalent to localize().
     *
     * @param obj
     * @param ref
     * @return Stub for obj
     * @throws ClassNotFoundException
     * @throws IllegalArgumentException
     */
    public static Remote toStub(RemoteObjectRef ref)
            throws IllegalArgumentException, ClassNotFoundException {
        RemoteInvocationHandler handler = new RemoteInvocationHandler(ref);
        Remote proxy = (Remote) Proxy.newProxyInstance(
                Remote.class.getClassLoader(),
                new Class[] { Class.forName(ref.interfaceName) }, handler);
        return proxy;
    }

    /**
     * Export a Remote obj to a given port and start a remote server at port
     *
     * @param name
     * @param obj
     * @param host
     * @param port
     * @return RemoteObjectRef for the obj
     */
    public static RemoteObjectRef export(String name, Remote obj, String host,
            int port) {
        RemoteObjectRef ref = new RemoteObjectRef(host, port, name, obj
                .getClass().getInterfaces()[0].getName());
        try {
            RemoteObjectServer.serveObject(obj, ref, port);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return ref;
    }

    /**
     * Export a Remote obj to a given port and start a remote server at port
     *
     * @param name
     * @param obj
     * @param port
     * @return RemoteObjectRef for the obj
     */
    public static RemoteObjectRef export(String name, Remote obj, int port) {
        return export(name, obj, "localhost", port);
    }
}