package test;

import rmi.registry.LocateRegistry;
import rmi.registry.Registry;

public class Client {
    public static void main(String args[]) {
        Registry registry = LocateRegistry.getRegistry();
        System.out.println(registry.list());
        Hello h = (Hello) registry.lookup("Hello");
        System.out.println(h.sayHello(10, 'c'));
        System.out.println(h.sayHello());
        Hello h2 = (Hello) registry.lookup("Hello2");
        System.out.println(h.sayHello(h2));
        try {
			Class.forName("test.Hello");
			System.out.println("Client JVM has interface test.HelloImpl");
			Class.forName("test.HelloImpl");
			System.out.println("Client JVM has class test.HelloImpl");
		} catch (ClassNotFoundException e) {
			System.out.println("Client JVM does NOT have class test.HelloImpl");
		}
    }

}
