package test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import rmi.message.InvokeRequest;

public class HelloImp_stub implements Hello {
    public final String host = "localhost";
    public final int port = 12345;
    private Socket sock;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public void sayHello(Integer a, Character c) {
        setupSocket();

        String methodName = getCurrentMethodName();
        InvokeRequest request = new InvokeRequest(methodName);

        request.addArg(a);
        request.addArg(c);

        try {
            out.writeObject(request);
            out.flush();
            sock.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void setupSocket() {
        try {
            sock = new Socket(host, port);
            out = new ObjectOutputStream(sock.getOutputStream());
            in = new ObjectInputStream(sock.getInputStream());
        } catch (IOException e) {
            System.out.println("socket setup error");
        }
    }

    private String getCurrentMethodName() {
        StackTraceElement elements[] = (new Throwable()).getStackTrace();
        return elements[1].getMethodName();
    }
}
