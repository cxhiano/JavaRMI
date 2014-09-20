package test;

import rmi.core.*;

public interface Hello extends Remote {
    public void sayHello(int a, char c);
}