package test;

import rmi.core.Unicast;

public class Server {
    public static void main(String args[]) {
        Hello h = new HelloImp();

        Unicast.export(h, 12345);

        while (true);
    }
}
