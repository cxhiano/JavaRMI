package rmi.core;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import rmi.message.InvokeRequest;
import rmi.message.InvokeResponse;
import rmi.message.Request;
import rmi.message.Response;
import rmi.util.SocketHelper;

public class RemoteObjectServer extends Thread {
	private Remote objRef = null;
	private int port;

	public RemoteObjectServer(Remote objRef, int port) {
		this.objRef = objRef;
		this.port = port;
	}

	private SocketHelper helper = new SocketHelper() {
		@Override
		public Response handle(Request request) {
			InvokeResponse resp = new InvokeResponse();

			try {
				InvokeRequest req = (InvokeRequest) request;

				Class<? extends Remote> cls = objRef.getClass();

				// No argument
				if (req.args == null) {
					Method method = cls.getMethod(req.methodName);
					resp.result = method.invoke(RemoteObjectServer.this.objRef);
				} else {

					// 1 or more argument
					Method method = cls.getMethod(req.methodName, req.types);

					resp.result = method.invoke(RemoteObjectServer.this.objRef,
									req.args);
				}
				return resp;
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
				resp.e = new RemoteException(e.toString());
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				resp.e = new RemoteException(e.toString());
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				resp.e = new RemoteException(e.toString());
			}
			return resp;
		}
	};

	public void run() {
		try {
			SocketHelper.serve(port, helper);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
