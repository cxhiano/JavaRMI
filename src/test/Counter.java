package test;

import rmi.core.Remote;
import rmi.core.RemoteException;

/**
 * A Counter interface. It has two implementation, an asynchronous version and
 * another synchronous version. A correct RMI implementation should deal with
 * them correspondingly.
 * 
 * @author Chao
 *
 */
public interface Counter extends Remote {
    public void reset() throws RemoteException;

    public void bump() throws RemoteException;

    public Integer getCount() throws RemoteException;
}