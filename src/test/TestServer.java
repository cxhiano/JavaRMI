package test;

import rmi.core.UnicastRemoteObject;
import rmi.registry.LocateRegistry;
import rmi.registry.Registry;

public class TestServer {
	public static final String KEY_BOB = "Bob";
	public static final String KEY_ALICE = "Alice";
	public static final String KEY_CALC = "Calc";
	public static final String KEY_ASYNC = "Async";
	public static final String KEY_SYNC = "Sync";
	public static final String[] KEYS = new String[] { KEY_BOB, KEY_ALICE,
			KEY_CALC, KEY_ASYNC, KEY_SYNC };

	public static void main(String args[]) {
		Registry registry = LocateRegistry.getRegistry();
		Hello bob = new HelloImpl("Bob");
		registry.rebind("Bob", (Hello) UnicastRemoteObject.export(bob, 7777));
		Hello alice = new HelloImpl("Alice");
		registry.rebind("Alice",
				(Hello) UnicastRemoteObject.export(alice, 7777));
		Calculator calc = new SimpleCalculator();
		registry.rebind("Calc",
				(Calculator) UnicastRemoteObject.export(calc, 7777));
		Counter aCounter = new AsyncCounter();
		registry.rebind("Async",
				(Counter) UnicastRemoteObject.export(aCounter, 8888));
		Counter sCounter = new SyncCounter();
		registry.rebind("Sync",
				(Counter) UnicastRemoteObject.export(sCounter, 8888));

	}
}
