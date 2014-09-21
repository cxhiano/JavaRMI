package test;

import rmi.core.*;

public interface Hello extends Remote {
    public String sayHello(Integer a, Character c);

    public void sayHello();
}