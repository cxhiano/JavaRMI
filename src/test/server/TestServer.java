package test.server;

import rmi.core.UnicastRemoteObject;
import rmi.registry.LocateRegistry;
import rmi.registry.Registry;
import test.base.Calculator;
import test.base.Constants;
import test.base.Counter;
import test.base.Hello;

public class TestServer {

    public static void main(String args[]) {
        Registry registry = LocateRegistry.getRegistry();
        Hello bob = new HelloImpl("Bob");
        registry.rebind(Constants.KEY_BOB, (Hello) UnicastRemoteObject.export(bob, 7777));
        Hello alice = new HelloImpl("Alice");
        registry.rebind(Constants.KEY_ALICE,
                (Hello) UnicastRemoteObject.export(alice, 7777));
        Calculator calc = new SimpleCalculator();
        registry.rebind(Constants.KEY_CALC,
                (Calculator) UnicastRemoteObject.export(calc, 7777));
        Counter aCounter = new SimpleCounter();
        registry.rebind(Constants.KEY_COUNT,
                (Counter) UnicastRemoteObject.export(aCounter, 8888));
        Counter sCounter = new SynchronizedCounter();
        registry.rebind(Constants.KEY_SYNC_COUNT,
                (Counter) UnicastRemoteObject.export(sCounter, 8888));

    }
}
