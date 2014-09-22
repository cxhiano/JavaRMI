package test;

import rmi.core.Remote;

/**
 * A Hello interface. A robust RMI implementation can support Remote stubs as
 * arguments.
 * 
 * @author Chao
 *
 */
public interface Hello extends Remote {
    public String sayHello();

    public String sayHello(Hello hello);

    public String getName();

}