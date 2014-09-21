package rmi.registry;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import rmi.core.RemoteException;
import rmi.message.AuthRequest;
import rmi.message.AuthResponse;

public class LocateRegistry {
	public static Registry getRegistry(String host, int port) {
		Socket socket = null;
		Registry registry = null;
		try {
			socket = new Socket(host, port);
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			out.writeObject(new AuthRequest());
			AuthResponse resp = (AuthResponse)in.readObject();
			if (!resp.ok)
				throw new RemoteException("Cannot talk to registry.");
			return new Registry(host, port);
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
		return registry;
	}
}
