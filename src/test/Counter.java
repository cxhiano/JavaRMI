package test;

import rmi.core.Remote;

public interface Counter extends Remote {
    public void reset();

    public void bump();

    public Integer getCount();
}
