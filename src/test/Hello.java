package test;

import rmi.core.Remote;
import rmi.registry.exception.RemoteException;

public interface Hello extends Remote {
    public String sayHello() throws RemoteException;

    public int sum(int a, int c) throws RemoteException;

    public Integer sum(Integer a, Integer b) throws RemoteException;

    public int divide(int a, int b) throws RemoteException;

    public String sayHello(Hello hello) throws RemoteException;

    public String getName() throws RemoteException;
}