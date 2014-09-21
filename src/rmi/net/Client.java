package rmi.net;

import rmi.message.Request;

public class Client {
    public static void main(String args[]) {
        Request r = new Request("Chao Ma");


        System.out.println(SocketRequest.request("localhost", 12345, r));
    }
}
