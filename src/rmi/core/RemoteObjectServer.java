package rmi.core;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import rmi.message.InvokeRequest;
import rmi.message.InvokeResponse;
import rmi.message.Request;
import rmi.message.Response;

public class RemoteObjectServer extends Thread {
	private Remote objRef = null;
	private int port;

	public RemoteObjectServer(Remote objRef, int port) {
		this.objRef = objRef;
		this.port = port;
	}

	private SocketHandler handler = new SocketHandler() {
		@Override
		public Response handle(Request request) {
			try {
				InvokeRequest req = (InvokeRequest) request;
				InvokeResponse resp = new InvokeResponse();
				resp.ok = true;
				Class<? extends Remote> cls = objRef.getClass();

				// No argument
				if (req.args == null) {
					Method method = cls.getMethod(req.methodName);
					resp.result = method.invoke(RemoteObjectServer.this.objRef);
				} else {

					// 1 or more argument
					Class<?>[] args = new Class<?>[req.args.length];
					for (int i = 0; i < req.args.length; ++i)
						args[i] = req.args[i].getClass();
					Method method = cls.getMethod(req.methodName, args);
					resp.result = method.invoke(RemoteObjectServer.this.objRef,
							req.args);
				}
				return resp;
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			return new Response();
		}
	};

	public void run() {
		try {
			SocketHandler.serve(port, handler);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
