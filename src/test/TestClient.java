package test;

import org.junit.Assert;

import rmi.registry.LocateRegistry;
import rmi.registry.Registry;
import rmi.registry.exception.StubNotFoundException;

/**
 * A Test Client. Make sure RegistryServer and TestServer are already launched
 * before launching this client. If tests are passed, "Test OK!" will eventually
 * be printed out. Otherwise, "Test Failed" will be printed out.
 * 
 * @author Chao
 *
 */
public class TestClient {
    public static void main(String args[]) {

        try {
            // Try to connect a nonexistent port, should return null
            Registry registry = LocateRegistry.getRegistry(64015);
            Assert.assertNull(registry);

            // Connect to RegistryServer at default port(15640), should not
            // raise any exceptions
            registry = LocateRegistry.getRegistry("10.0.0.6");

            // List all names on RegistryServer
            Assert.assertArrayEquals(TestServer.KEYS, registry.list().toArray());

            // Lookup Hello instance Alice and Bob
            Hello alice = (Hello) registry.lookup(TestServer.KEY_ALICE);
            Assert.assertNotNull(alice);
            Hello bob = (Hello) registry.lookup(TestServer.KEY_BOB);
            Assert.assertNotNull(bob);
            // Test if stub works correctly
            Assert.assertEquals("Hello! My name is Alice", alice.sayHello());
            Assert.assertEquals("Hello! My name is Bob", bob.sayHello());
            // Test if stub could be successfully passed as arguments
            Assert.assertEquals("Hello Bob ! My name is Alice",
                    alice.sayHello(bob));

            // Calculator instance Calc
            Calculator calc = (Calculator) registry.lookup(TestServer.KEY_CALC);
            // Make sure we can separate methods with primitive types from
            // methods with object types
            Assert.assertSame(1, calc.add(0, 1));
            Assert.assertSame(1, calc.add(new Integer(0), new Integer(1))
                    .intValue());
            Assert.assertSame(2, calc.divide(5, 2));
            Assert.assertSame(3, calc.minus(4, 1));
            Assert.assertSame(4, calc.multiply(2, 2));

            // Asynchronous counter
            Counter aCounter = (Counter) registry.lookup(TestServer.KEY_COUNT);
            aCounter.reset();
            Thread t = new BumpThread(aCounter);
            Thread t2 = new BumpThread(aCounter);
            t.start();
            t2.start();
            t.join();
            t2.join();
            // Consistency is not assured, so the count result should be in
            // range [COUNT, 2 * COUNT]
            Assert.assertTrue(aCounter.getCount() <= 2 * BumpThread.COUNT);
            Assert.assertTrue(aCounter.getCount() >= BumpThread.COUNT);

            // Synchronous counter
            Counter sCounter = (Counter) registry
                    .lookup(TestServer.KEY_SYNC_COUNT);
            sCounter.reset();
            t = new BumpThread(sCounter);
            t2 = new BumpThread(sCounter);
            t.start();
            t2.start();
            t.join();
            t2.join();
            // Consistency is assured, so the count result should be exactly 2 *
            // COUNT
            Assert.assertTrue(sCounter.getCount() == 2 * BumpThread.COUNT);

            // Lookup an instance and expect a StubNotFoundException
            Exception e = null;
            try {
                registry.lookup("Bad");
            } catch (Exception exception) {
                e = exception;
            }
            Assert.assertTrue(e instanceof StubNotFoundException);

            System.out.println("Test OK!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Test Failed!");
        }
    }

    /**
     * Bump a counter 100 times
     * 
     * @author Chao
     *
     */
    private static class BumpThread extends Thread {

        public static final int COUNT = 100;
        private Counter counter;

        public BumpThread(Counter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            for (int i = 0; i < COUNT; ++i)
                this.counter.bump();
        }

    }
}
