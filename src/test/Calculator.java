package test;

import rmi.core.Remote;

/**
 * A Calculator interface. This provides four basic operations, for each
 * opeartion, this interface offers two functions. One is for int primitive
 * types and one for Integer object types. A correct RMI implementation should
 * successfully separate these two functions from each other and return value of
 * correct type.
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
