package test;

import rmi.core.UnicastRemoteObject;
import rmi.registry.LocateRegistry;
import rmi.registry.Registry;

public class TestServer {
    public static final String KEY_BOB = "Bob";
    public static final String KEY_ALICE = "Alice";
    public static final String KEY_CALC = "Calc";
    public static final String KEY_COUNT = "Count";
    public static final String KEY_SYNC_COUNT = "SyncCount";
    public static final String[] KEYS = new String[] { KEY_BOB, KEY_ALICE,
            KEY_CALC, KEY_COUNT, KEY_SYNC_COUNT };

    public static void main(String args[]) {
        Registry registry = LocateRegistry.getRegistry();
        Hello bob = new HelloImpl("Bob");
        registry.rebind(KEY_BOB, (Hello) UnicastRemoteObject.export(bob, "10.0.0.6", 7777));
        Hello alice = new HelloImpl("Alice");
        registry.rebind(KEY_ALICE,
                (Hello) UnicastRemoteObject.export(alice, "10.0.0.6", 7777));
        Calculator calc = new SimpleCalculator();
        registry.rebind(KEY_CALC,
                (Calculator) UnicastRemoteObject.export(calc, "10.0.0.6", 7777));
        Counter aCounter = new SimpleCounter();
        registry.rebind(KEY_COUNT,
                (Counter) UnicastRemoteObject.export(aCounter, "10.0.0.6", 8888));
        Counter sCounter = new SynchronizedCounter();
        registry.rebind(KEY_SYNC_COUNT,
                (Counter) UnicastRemoteObject.export(sCounter, "10.0.0.6", 8888));

    }
}
