package test.base;

import rmi.core.Remote;
import rmi.core.RemoteException;

/**
 * A Hello interface. A robust RMI implementation can support Remote stubs as
 * arguments.
 * 
 * @author Chao
 *
 */
public interface Hello extends Remote {
    public String sayHello() throws RemoteException;

    public String sayHello(Hello hello) throws RemoteException;

    public String getName() throws RemoteException;

}