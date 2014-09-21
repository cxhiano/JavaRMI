package rmi.net;

import rmi.message.Request;

public class Client {
    public static void main(String args[]) {
        Request r = new Request();


        System.out.println(SocketRequest.request("localhost", 12345, r));
    }
}
