package test;

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
    public synchronized void reset() {
        count = 0;
    }

    @Override
    public synchronized void bump() {
        ++count;
    }

    @Override
    public synchronized Integer getCount() {
        return count;
    }

}
