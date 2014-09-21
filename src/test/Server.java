package test;

import rmi.core.Unicast;
import rmi.registry.LocateRegistry;
import rmi.registry.Registry;

public class Server {
    public static void main(String args[]) {
        Hello h = new HelloImp();
        Hello stub = (Hello)Unicast.export(h, 12345);
        Registry registry = LocateRegistry.getRegistry();
        registry.rebind("Hello", stub);
        while (true);
    }
}
