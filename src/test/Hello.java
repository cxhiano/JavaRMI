package test;

import rmi.core.Remote;

public interface Hello extends Remote {
    public String sayHello();

    public int sum(int a, int c);

    public Integer sum(Integer a, Integer b);

    public int divide(int a, int b);

    public String sayHello(Hello hello);

    public String getName();
}