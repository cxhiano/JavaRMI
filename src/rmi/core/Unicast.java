package rmi.core;

import java.lang.reflect.Proxy;

public class Unicast {
    private static Remote generateStub(Remote obj, int port) {
        StubHandler handler = new StubHandler("localhost", port);

        Remote proxy = (Remote) Proxy.newProxyInstance(
                            Remote.class.getClassLoader(),
                            obj.getClass().getInterfaces(),
                            handler);

        return proxy;
    }

    public static Remote export(Remote obj, int port) {
        RemoteObjectServer server = new RemoteObjectServer(obj, port);
        server.start();

        return generateStub(obj, port);
    }
}