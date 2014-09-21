package rmi.utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import rmi.message.InvokeRequest;

public abstract class BaseStub {
    protected Socket sock;
    protected ObjectInputStream in;
    protected ObjectOutputStream out;

    protected Object invoke(InvokeRequest req) {
        Object ret;
        try {
            out.writeObject(req);
            out.flush();
            ret = in.readObject();
            sock.close();
            return ret;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void setupSocket(String host, int port) {
        try {
            sock = new Socket(host, port);
            out = new ObjectOutputStream(sock.getOutputStream());
            in = new ObjectInputStream(sock.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected String getCurrentMethodName() {
        StackTraceElement elements[] = (new Throwable()).getStackTrace();
        return elements[1].getMethodName();
    }

}
