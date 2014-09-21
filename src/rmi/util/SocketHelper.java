package rmi.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import rmi.message.Request;
import rmi.message.Response;

/**
 * A wrapper class for servers to serve and for clients to send requests and get
 * response.
 *
 * @author Chao
 *
 */
public abstract class SocketHelper {

	public abstract Response handle(Request request);

	/**
	 * Run a server at given port number
	 *
	 * @param port
	 * @param helper
	 * @throws IOException
	 */
	public static void serve(int port, SocketHelper helper) throws IOException {
		ServerSocket listener = null;
		try {
			listener = new ServerSocket(port);
			while (true) {
				serve(listener, helper);
			}
		} finally {
			if (listener != null) {
				try {
					listener.close();
				} catch (Exception e) {
				}
			}
		}
	}

	/**
	 * Loop function for server at given listener
	 *
	 * @param listener
	 * @param helper
	 */
	public static void serve(ServerSocket listener, SocketHelper helper) {
		Socket socket = null;
		try {
			socket = listener.accept();
			socket.setSoTimeout(10000);
			ObjectOutputStream out = new ObjectOutputStream(
					socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(
					socket.getInputStream());
			Request req = (Request) in.readObject();
			out.writeObject( helper.handle(req));
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
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

	/**
	 *
	 * Wrapper function for client to send a request at host:port
	 *
	 * @param host
	 * @param port
	 * @param request
	 * @return response object
	 */
	public static Response request(String host, int port, Request request) {
		Socket sock = null;

		try {
			sock = new Socket(host, port);
			sock.setSoTimeout(10000);

			ObjectOutputStream out = new ObjectOutputStream(
					sock.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(sock.getInputStream());

			out.writeObject(request);
			out.flush();

			Response response = (Response) in.readObject();

			return response;
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
