package rmi.net;

import java.io.IOException;

import rmi.message.Request;
import rmi.message.Response;
import rmi.registry.*;
import rmi.core.*;
import test.*;

public class Test {
    public static void main(String args[]) {
        Registry registry = LocateRegistry.getRegistry();
        Hello h = new HelloImp("Chao Ma");
        Hello stub = (Hello)UnicastRemoteObject.export(h, 8888);
        registry.rebind("Hello", stub);
        Hello b = new HelloImp("Da Chao Ma");
        Hello s = (Hello)UnicastRemoteObject.export(b, 8888);
        registry.rebind("Hello2", s);
    }

}
