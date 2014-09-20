package test;

public class Server {
    public static void main(String args[]) {
        Unicast.createRMIServer(12345);
    }
}
