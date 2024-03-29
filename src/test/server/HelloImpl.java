package test.server;

import rmi.core.RemoteException;
import test.base.Hello;

/**
 * Implementation of Hello Interface. A robust RMI implementation can support
 * Remote stubs as arguments.
 * 
 * @author Chao
 *
 */
public class HelloImpl implements Hello {
    public String myName;

    public HelloImpl(String name) throws RemoteException {
        myName = name;
    }

    @Override
    public String getName() throws RemoteException{
        return myName;
    }

    @Override
    public String sayHello() throws RemoteException {
        return String.format("Hello! My name is %s", myName);
    }

    @Override
    public String sayHello(Hello hello) throws RemoteException {
        return String.format("Hello %s ! My name is %s", hello.getName(),
                myName);
    }
}
