package test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import rmi.message.InvokeRequest;

public class HelloImp_stub implements Hello {
    public final String host = "localhost";
    public final int port = 12345;
    public final String remoteClassName = "test.HelloImp";
    private Socket sock;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public HelloImp_stub() {
        try {
            sock = new Socket(host, port);
            out = new ObjectOutputStream(sock.getOutputStream());
            in = new ObjectInputStream(sock.getInputStream());
        } catch (IOException e) {
            System.out.println("socket setup error");
        }
    }

    public void sayHello(int a, char c) {
        String methodName = getCurrentMethodName();
        InvokeRequest request = new InvokeRequest(remoteClassName, methodName);
        request.addArg(new Integer(a));
        request.addArg(new Character(c));
        try {
            out.writeObject(request);
            out.flush();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private String getCurrentMethodName() {
        StackTraceElement elements[] = (new Throwable()).getStackTrace();
        return elements[1].getMethodName();
    }
}
