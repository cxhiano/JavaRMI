package test;

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
