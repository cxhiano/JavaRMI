package test;

import org.junit.Assert;

import rmi.registry.LocateRegistry;
import rmi.registry.Registry;
import rmi.registry.exception.StubNotFoundException;

public class TestClient {
    public static void main(String args[]) {

        try {
            Registry registry = LocateRegistry.getRegistry(64015);
            Assert.assertNull(registry);
            
            registry = LocateRegistry.getRegistry();
            Assert.assertArrayEquals(TestServer.KEYS, registry.list().toArray());
            Hello alice = (Hello) registry.lookup(TestServer.KEY_ALICE);
            Hello bob = (Hello) registry.lookup(TestServer.KEY_BOB);
            Assert.assertEquals("Hello! My name is Alice", alice.sayHello());
            Assert.assertEquals("Hello! My name is Bob", bob.sayHello());
            Assert.assertEquals("Hello Bob ! My name is Alice",
                    alice.sayHello(bob));
            
            Calculator calc = (Calculator) registry.lookup(TestServer.KEY_CALC);
            Assert.assertEquals(1, calc.add(0, 1));
            Assert.assertEquals(2, calc.divide(5, 2));
            Assert.assertEquals(3, calc.minus(4, 1));
            Assert.assertEquals(4, calc.multiply(2, 2));
            
            Counter aCounter = (Counter) registry.lookup(TestServer.KEY_COUNT);
            aCounter.reset();
            Thread t = new BumpThread(aCounter);
            Thread t2 = new BumpThread(aCounter);
            t.start();
            t2.start();
            t.join();
            t2.join();
            Assert.assertTrue(aCounter.getCount() <= 2 * BumpThread.COUNT);
            Assert.assertTrue(aCounter.getCount() >= BumpThread.COUNT);
            
            Counter sCounter = (Counter) registry
                    .lookup(TestServer.KEY_SYNC_COUNT);
            sCounter.reset();
            t = new BumpThread(sCounter);
            t2 = new BumpThread(sCounter);
            t.start();
            t2.start();
            t.join();
            t2.join();
            Assert.assertTrue(sCounter.getCount() == 2 * BumpThread.COUNT);
            
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
