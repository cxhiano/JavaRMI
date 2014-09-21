package test;

import rmi.core.Remote;

public interface Hello extends Remote {
    public String sayHello();

    public String sayHello(Hello hello);

    public String getName();

}