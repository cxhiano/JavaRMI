package rmi.core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import rmi.core.RemoteException;
import rmi.message.InvokeRequest;
import rmi.message.InvokeResponse;

public abstract class BaseStub implements Serializable {
    protected Socket sock;
    protected ObjectInputStream in;
    protected ObjectOutputStream out;

    protected Object invoke(InvokeRequest req) throws RemoteException {
        try {
            out.writeObject(req);
            out.flush();
            InvokeResponse ret = (InvokeResponse)in.readObject();
            sock.close();

            if (ret == null || !ret.ok)
                throw new RemoteException("Bad Response.");

            return ret.result;
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
}
