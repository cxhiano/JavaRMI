package test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import rmi.core.RemoteException;
import rmi.message.InvokeRequest;
import rmi.message.InvokeResponse;

public class HelloImp_stub implements Hello {
	public final String host = "localhost";
	public final int port = 12345;
	private Socket sock;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	public String sayHello(Integer a, Character c) {
        setupSocket();

        InvokeRequest request = new InvokeRequest("sayHello");
        InvokeResponse response = null;

        request.addArg(a);
        request.addArg(c);

        try {
            out.writeObject(request);
            out.flush();
            response = (InvokeResponse)in.readObject();
            sock.close();
        } catch (IOException e) {
        	e.printStackTrace();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        if (response == null || !response.ok || !(response.result instanceof String))
        	throw new RemoteException("Bad Response.");
        return (String)response.result;
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
