package rmi.net;

import java.io.IOException;

import rmi.message.Request;
import rmi.message.Response;

public class Test {
    public static void main(String args[]) {
        try {
            SocketServer server = SocketServer.getServer(12345);
            SocketRequestHandler handler = new SocketRequestHandler() {
                public Response handle(Request req) {
                    System.out.println(req);
                    return new Response();
                }
            };

            server.bindHandler(handler);

            server.serve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
