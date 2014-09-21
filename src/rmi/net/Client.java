package rmi.net;

import rmi.message.Request;
import rmi.registry.*;
import rmi.core.*;
import test.*;

public class Client {
    public static void main(String args[]) {
        Registry registry = LocateRegistry.getRegistry();
        System.out.println(registry.list());
        try {
            Hello h = (Hello) registry.lookup("Hello");

            //Primitive type test
            System.out.println(h.sum(10, 20));
            System.out.println(h.sum(new Integer(10), new Integer(Integer.MAX_VALUE)));

            //Error handling test
            //System.out.println(h.divide(10, 0));

            System.out.println(h.sayHello());

            Hello h2 = (Hello) registry.lookup("Hello2");
            System.out.println(h2.sayHello(h));
        }  catch (RemoteException e) {
            e.printStackTrace();
        }

        System.out.println("Test finished");
    }
}
