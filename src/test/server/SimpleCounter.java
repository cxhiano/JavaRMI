package test.server;

import rmi.core.RemoteException;
import test.base.Counter;

/**
 * A asynchronous counter. If called in multiple threads, this counter never
 * assures consistency.
 * 
 * @author Chao
 *
 */
public class SimpleCounter implements Counter {

    private Integer count;

    public SimpleCounter() {
        count = 0;
    }

    @Override
    public void reset() throws RemoteException {
        count = 0;
    }

    @Override
    public void bump() throws RemoteException {
        ++count;
    }

    @Override
    public Integer getCount() throws RemoteException {
        return count;
    }

}
