package test.server;

import rmi.core.RemoteException;
import test.base.Counter;

/**
 * A synchronous counter. If called in multiple threads, this counter assures
 * consistency.
 * 
 * @author Chao
 *
 */
public class SynchronizedCounter implements Counter {

    private Integer count;

    public SynchronizedCounter() {
        count = 0;
    }

    @Override
    public synchronized void reset() throws RemoteException {
        count = 0;
    }

    @Override
    public synchronized void bump() throws RemoteException {
        ++count;
    }

    @Override
    public synchronized Integer getCount() throws RemoteException {
        return count;
    }

}
