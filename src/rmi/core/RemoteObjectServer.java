package rmi.core;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import rmi.message.InvokeRequest;
import rmi.message.InvokeResponse;
import rmi.message.Request;
import rmi.message.Response;
import rmi.net.SocketServer;
import rmi.net.SocketRequestHandler;

public class RemoteObjectServer {
	private Remote obj = null;
	private RemoteObjectRef ref = null;
	private int port;
	private SocketRequestHandler handler;

	public static void serveObject(Remote obj, RemoteObjectRef ref, int port)
			throws IOException {

		RemoteObjectServer remoteServer = new RemoteObjectServer(obj, ref, port);

		SocketServer server = SocketServer.getServer(port);
		server.bindHandler(remoteServer.handler);

		//Start the server if it's not yet started
		if (server.getState() == Thread.State.NEW)
			server.start();

	}

	private RemoteObjectServer(Remote obj, RemoteObjectRef ref, int port) {
		this.obj = obj;
		this.ref = ref;
		this.port = port;

		this.handler = new SocketRequestHandler(ref.getName()) {
			@Override
			public Response handle(Request request) {
				InvokeResponse resp = new InvokeResponse();

				try {
					InvokeRequest req = (InvokeRequest) request;

					Class<? extends Remote> cls = obj.getClass();

					// No argument
					if (req.args == null) {
						Method method = cls.getMethod(req.methodName);
						resp.result = method.invoke(RemoteObjectServer.this.obj);
					} else {

						// 1 or more argument
						Method method = cls.getMethod(req.methodName, req.types);

						resp.result = method.invoke(RemoteObjectServer.this.obj,
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
	}
}
