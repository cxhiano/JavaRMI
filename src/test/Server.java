package test;

import rmi.core.Unicast;
import rmi.registry.LocateRegistry;
import rmi.registry.Registry;

public class Server {
    public static void main(String args[]) {
        Registry registry = LocateRegistry.getRegistry();
        Hello h = new HelloImp();
        Hello stub = (Hello)Unicast.export(h, 7777);
        registry.rebind("Hello", stub);
        Hello b = new HelloImp();
        Hello s = (Hello)Unicast.export(b, 8888);
        registry.rebind("Hello2", stub);
        
    }
}
