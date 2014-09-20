package rmi.core;

import rmi.core.Remote;

public class Unicast {
    public static Remote export(Remote obj, int port) {
        RemoteObjectServer server = new RemoteObjectServer(obj, port);
        server.start();
        try {
            return (Remote)Class.forName(obj.getClass().getName() + "_stub").newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }
}
