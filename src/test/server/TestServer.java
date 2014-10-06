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
        registry.rebind(UnicastRemoteObject
                .export(Constants.KEY_BOB, bob, 7777));
        Hello alice = new HelloImpl("Alice");
        registry.rebind(UnicastRemoteObject.export(Constants.KEY_ALICE, alice,
                7777));
        Calculator calc = new SimpleCalculator();
        registry.rebind(UnicastRemoteObject.export(Constants.KEY_CALC, calc,
                7777));
        Counter aCounter = new SimpleCounter();
        registry.rebind(UnicastRemoteObject.export(Constants.KEY_COUNT,
                aCounter, 8888));
        Counter sCounter = new SynchronizedCounter();
        registry.rebind(UnicastRemoteObject.export(Constants.KEY_SYNC_COUNT,
                sCounter, 8888));

    }
}
