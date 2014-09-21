package rmi.core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.lang.reflect.Proxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.io.Serializable;

import rmi.message.InvokeRequest;
import rmi.message.InvokeResponse;

public class StubHandler implements Serializable, InvocationHandler {
    private Socket sock;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private String host;
    private int port;

    public StubHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws RemoteException {
        setupSocket();

        InvokeRequest req = new InvokeRequest(method.getName(), args);

        try {
            out.writeObject(req);
            out.flush();
            InvokeResponse response = (InvokeResponse)in.readObject();
            sock.close();

            if (response == null || !response.ok)
                throw new RemoteException("Bad Response.");

            return method.getReturnType().cast(response.result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setupSocket() {
        try {
            sock = new Socket(host, port);
            out = new ObjectOutputStream(sock.getOutputStream());
            in = new ObjectInputStream(sock.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
