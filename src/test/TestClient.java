package test;

import org.junit.Assert;

import rmi.registry.LocateRegistry;
import rmi.registry.Registry;

public class TestClient {
    public static void main(String args[]) {

        Registry registry = LocateRegistry.getRegistry();
        try {
            Assert.assertArrayEquals(TestServer.KEYS, registry.list().toArray());
            Hello alice = (Hello) registry.lookup(TestServer.KEY_ALICE);
            Hello bob = (Hello) registry.lookup(TestServer.KEY_BOB);
            Calculator calc = (Calculator) registry.lookup(TestServer.KEY_CALC);
            Counter counter = (Counter) registry.lookup(TestServer.KEY_COUNT);
            Assert.assertEquals("Hello! My name is Alice", alice.sayHello());
            Assert.assertEquals("Hello! My name is Bob", bob.sayHello());
            Assert.assertEquals("Hello Bob ! My name is Alice",
                    alice.sayHello(bob));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Test finished");
    }

}
