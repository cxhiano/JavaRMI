package test;

import rmi.core.Remote;
import rmi.core.RemoteException;

public interface Hello extends Remote {
    public String sayHello(Integer a, Character c) throws RemoteException;

    public void sayHello() throws RemoteException;

    public int sum(int a, int c) throws RemoteException;
}