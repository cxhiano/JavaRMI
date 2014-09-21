package rmi.core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.lang.reflect.Proxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import rmi.core.RemoteException;
import rmi.message.InvokeRequest;
import rmi.message.InvokeResponse;
import rmi.core.Remote;
import rmi.core.RemoteException;

public class Unicast {
    private static Remote generateStub(Remote obj) {
        InvocationHandler handler = new InvocationHandler() {
            private Socket sock;
            private ObjectInputStream in;
            private ObjectOutputStream out;

            public Object invoke(Object proxy, Method method, Object[] args) throws RemoteException {
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

            private void setupSocket(String host, int port) {
                try {
                    sock = new Socket(host, port);
                    out = new ObjectOutputStream(sock.getOutputStream());
                    in = new ObjectInputStream(sock.getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        Remote proxy = (Remote) Proxy.newProxyInstance(
                            Remote.class.getClassLoader(),
                            obj.getClass().getInterfaces(),
                            handler);

        return proxy;
    }

    public static Remote export(Remote obj, int port) {
        RemoteObjectServer server = new RemoteObjectServer(obj, port);
        server.start();
        try {
            return (Remote)Class.forName(obj.getClass().getName() + "_stub").newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
