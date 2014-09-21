package test;

//import rmi.core.UnicastRemoteObject;
import rmi.registry.LocateRegistry;
import rmi.registry.Registry;

public class Server {
    public static void main(String args[]) {
        Registry registry = LocateRegistry.getRegistry();
        registry.lookup("sadf");
        /*
        Hello h = new HelloImp("Chao Ma");
        Hello stub = (Hello)UnicastRemoteObject.export(h, 7777);
        registry.rebind("Hello", stub);
        Hello b = new HelloImp("Da Chao Ma");
        Hello s = (Hello)UnicastRemoteObject.export(b, 8888);
        registry.rebind("Hello2", s);
        */

    }
}
