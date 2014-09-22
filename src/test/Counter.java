package test;

import rmi.core.Remote;

/**
 * A Counter interface. It has two implementation, an asynchronous version and
 * another synchronous version. A correct RMI implementation should deal with
 * them correspondingly.
 * 
 * @author Chao
 *
 */
public interface Counter extends Remote {
    public void reset();

    public void bump();

    public Integer getCount();
}
