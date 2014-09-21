package rmi.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import rmi.message.Request;
import rmi.message.Response;

public class SocketServer extends Thread {
	private static final Logger LOGGER = Logger.getGlobal();
	private static Map<Integer, SocketServer> serverSockets = new ConcurrentHashMap<Integer, SocketServer>();

	public int port;
	private ServerSocket listener;
	private Map<String, SocketRequestHandler> handlers;

	public static SocketServer getServer(int port) throws IOException {
		SocketServer server = serverSockets.get(port);

		if (server == null) {
			server = new SocketServer(port);
			serverSockets.put(port, server);
		}

		return server;
	}

	private SocketServer(int port) throws IOException {
		this.port = port;
		this.listener = new ServerSocket(port);
		this.handlers = new HashMap<String, SocketRequestHandler>();
	}

	public void run() {
		Socket socket = null;

		while (true) {
			try {
				socket = listener.accept();
				ObjectOutputStream out = new ObjectOutputStream(
						socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(
						socket.getInputStream());
				Request req = (Request) in.readObject();
				Response resp = dispatch(req);
				out.writeObject(resp);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				if (socket != null)
					try {
						socket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
		}
	}

	public void bindHandler(String token, SocketRequestHandler handler) {
		this.handlers.put(token, handler);
	}

	public void removeHandler(String token) {
		this.handlers.remove(token);
	}

	private Response dispatch(Request req) {
		SocketRequestHandler handler = this.handlers.get(req.getToken());
		if (handler != null) {
			return handler.handle(req);
		}
		return null;
	}
}
