package rmi.core;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

import rmi.message.InvokeRequest;
import rmi.message.InvokeResponse;

public class StubHandler implements Serializable, InvocationHandler {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6842830036262560740L;
	private String host;
	private int port;

	public StubHandler(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws RemoteException {
		Socket sock = null;
		try {
			sock = new Socket(host, port);
			ObjectOutputStream out = new ObjectOutputStream(
					sock.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
			InvokeRequest req = new InvokeRequest(method.getName(), args);
			out.writeObject(req);
			out.flush();
			InvokeResponse response = (InvokeResponse) in.readObject();
			sock.close();
			if (response == null || !response.ok)
				throw new RemoteException("Bad Response.");
			return method.getReturnType().cast(response.result);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (sock != null)
					sock.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
