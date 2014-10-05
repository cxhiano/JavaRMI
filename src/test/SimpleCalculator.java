package test;

import rmi.core.RemoteException;

/**
 * A simple calculator implementation. This provides four basic operations, for
 * each opeartion, this interface offers two functions. One is for int primitive
 * types and one for Integer object types. A correct RMI implementation should
 * successfully separate these two functions from each other and return value of
 * correct type.
 * 
 * @author Chao
 *
 */
public class SimpleCalculator implements Calculator {

    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }

    @Override
    public Integer add(Integer a, Integer b) throws RemoteException {
        return a + b;
    }

    @Override
    public int minus(int a, int b) throws RemoteException {
        return a - b;
    }

    @Override
    public Integer minus(Integer a, Integer b) throws RemoteException {
        return a - b;
    }

    @Override
    public int multiply(int a, int b) throws RemoteException {
        return a * b;
    }

    @Override
    public Integer multiply(Integer a, Integer b) throws RemoteException {
        return a * b;
    }

    @Override
    public int divide(int a, int b) throws RemoteException {
        return a / b;
    }

    @Override
    public Integer divide(Integer a, Integer b) throws RemoteException {
        return a / b;
    }

}
