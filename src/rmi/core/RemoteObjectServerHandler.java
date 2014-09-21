package rmi.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import rmi.message.InvokeRequest;
import rmi.message.InvokeResponse;
import rmi.message.Request;
import rmi.message.Response;
import rmi.net.SocketRequestHandler;

public class RemoteObjectServerHandler extends SocketRequestHandler {

	private Remote obj;

	public RemoteObjectServerHandler(Remote obj) {
		this.obj = obj;
	}

	@Override
	public Response handle(Request request) {
		InvokeResponse resp = new InvokeResponse();
		try {
			InvokeRequest req = (InvokeRequest) request;
			Class<? extends Remote> cls = obj.getClass();
			if (req.args == null) {
				// No argument
				Method method = cls.getMethod(req.methodName);
				resp.result = method.invoke(this.obj);
			} else {
				// One or more arguments
				Method method = cls.getMethod(req.methodName, req.types);
				resp.result = method.invoke(this.obj, req.args);
			}
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

}
