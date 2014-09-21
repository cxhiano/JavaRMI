package test;

import java.lang.reflect.*;
import rmi.core.*;

public class Test {
    public static void main(String args[]) {
        Hello h = new HelloImp(),
              stub = (Hello) Unicast.generateStub(h, 5555);

        for (Method m : stub.getClass().getDeclaredMethods())
            System.out.println(m);

    }

}
