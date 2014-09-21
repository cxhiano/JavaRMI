package rmi.core;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

import rmi.message.InvokeRequest;
import rmi.message.InvokeResponse;

public class RemoteObjectServer extends Thread {
	private Remote objRef = null;
	private int port;

	public RemoteObjectServer(Remote objRef, int port) {
		this.objRef = objRef;
		this.port = port;
	}

	private Object handleRequest(InvokeRequest req) {
		try {
			Class<? extends Remote> cls = objRef.getClass();
			Class<?>[] args = new Class<?>[req.args.size()];
			for (int i = 0; i < req.args.size(); ++i)
				args[i] = req.args.get(i).getClass();
			Method method = cls.getMethod(req.methodName, args);
			if (req.args.size() == 0) {
				return method.invoke(this.objRef);
			} else {
				return method.invoke(this.objRef, req.args.toArray());
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void loop(ServerSocket listener) {
		Socket socket = null;
		try {
			socket = listener.accept();
			ObjectOutputStream oos = new ObjectOutputStream(
					socket.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(
					socket.getInputStream());
			InvokeRequest req = (InvokeRequest) ois.readObject();
			InvokeResponse resp = new InvokeResponse();
			resp.result = handleRequest(req);
			resp.ok = true;
			oos.writeObject(resp);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (Exception e) {
				}
			}
		}
	}

	public void run() {
		ServerSocket listener = null;
		try {
			listener = new ServerSocket(port);
			while (true) {
				loop(listener);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (listener != null) {
				try {
					listener.close();
				} catch (Exception e) {
				}
			}
		}
	}

}
