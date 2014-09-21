package rmi.core;

import rmi.message.InvokeRequest;
import rmi.core.Remote;

import java.net.Socket;
import java.net.ServerSocket;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.*;

public class RemoteObjectServer extends Thread {
    private Remote ref = null;
    private int port;

    public RemoteObjectServer(Remote ref, int port) {
        this.ref = ref;
        this.port = port;
    }

    private Object handleRequest(InvokeRequest req) {
        try {
            Class cls = ref.getClass();

            Class[] args = new Class[req.args.size()];
            for (int i = 0; i < req.args.size(); ++i)
                args[i] = req.args.get(i).getClass();

            Method method = cls.getMethod(req.methodName, args);

            return method.invoke(this.ref, req.args.toArray());
        } catch (NoSuchMethodException e ) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void run() {
        ServerSocket listener;
        try {
            while (true) {
                listener = new ServerSocket(port);

                Socket sock = listener.accept();

                ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(sock.getInputStream());

                InvokeRequest request = (InvokeRequest)in.readObject();
                if (request != null) {
                    Object ret = handleRequest(request);
                    out.writeObject(ret);
                }

                sock.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
