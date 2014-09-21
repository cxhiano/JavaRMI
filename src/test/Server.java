package test;

import rmi.core.Unicast;
import rmi.registry.LocateRegistry;
import rmi.registry.Registry;

public class Server {
    public static void main(String args[]) {
        Hello h = new HelloImp();
        Hello stub = (Hello)Unicast.export(h, 12345);
        System.out.println(stub.getClass());
        for (Class c : stub.getClass().getInterfaces())
            System.out.println(c);
        Registry registry = LocateRegistry.getRegistry("localhost", 3044);
        registry.rebind("Hello", stub);
        while (true);
    }
}
