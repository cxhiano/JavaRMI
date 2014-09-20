package test;

import rmi.message.InvokeRequest;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.lang.reflect.*;

public class Unicast {
    public static void createRMIServer(int port) {
        ServerSocket listener;
        try {
            listener = new ServerSocket(port);
        } catch (IOException e) {
            System.out.printf("Could not listen on port %d\n", port);
            return;
        }
        try {
            Socket sock = listener.accept();
            ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
            InvokeRequest request = (InvokeRequest)in.readObject();
            if (request != null) {
                System.out.println(request.className);
            }
        } catch (IOException e) {
            System.out.println(e);
            return;
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }
}
