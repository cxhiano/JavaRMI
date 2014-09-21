package rmi.core;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

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
		InvokeResponse response = (InvokeResponse) SocketHandler.request(host,
				port, new InvokeRequest(method.getName(), args));
		return method.getReturnType().cast(response.result);
	}
}
