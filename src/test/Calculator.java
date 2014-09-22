package test;

import rmi.core.Remote;

/**
 * A Calculator interface. Provides four basic operations, for each opeartion,
 * this interface offers two functions.
 * 
 * @author Chao
 *
 */
public interface Calculator extends Remote {

    public int add(int a, int b);

    public Integer add(Integer a, Integer b);

    public int minus(int a, int b);

    public Integer minus(Integer a, Integer b);

    public int multiply(int a, int b);

    public Integer multiply(Integer a, Integer b);

    public int divide(int a, int b);

    public Integer divide(Integer a, Integer b);
}
