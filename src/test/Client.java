package test;

import rmi.registry.LocateRegistry;
import rmi.registry.Registry;

public class Client {
    public static void main(String args[]) {
        Registry registry = LocateRegistry.getRegistry();
        Hello h = (Hello) registry.lookup("Hello");
        System.out.println(h.sayHello(10, 'c'));
        h.sayHello();
    }

}
