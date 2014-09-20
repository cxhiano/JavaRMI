package rmi.registry;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import rmi.core.Remote;
import rmi.core.RemoteException;
import rmi.message.LookupRequest;
import rmi.message.LookupResponse;
import rmi.message.RebindRequest;
import rmi.message.RebindResponse;

public class Registry {
	public String host;
	public int port;

	public Registry(String host, int port) {
		this.host = host;
		this.port = port;
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public Remote lookup(String key) {
		Socket socket = null;
		try {
			socket = new Socket(host, port);
			ObjectInputStream ois = new ObjectInputStream(
					socket.getInputStream());
			ObjectOutputStream oos = new ObjectOutputStream(
					socket.getOutputStream());
			LookupRequest req = new LookupRequest(key);
			oos.writeObject(req);
			LookupResponse resp = (LookupResponse) ois.readObject();
			if (!resp.ok)
				throw new RemoteException("Bad Response.");
			return resp.stub;
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
		return null;
	}
	
	/**
	 * 
	 * @param key
	 * @param ref
	 */
	public void rebind(String key, Remote stub) {
		Socket socket = null;
		try {
			socket = new Socket(host, port);
			ObjectInputStream ois = new ObjectInputStream(
					socket.getInputStream());
			ObjectOutputStream oos = new ObjectOutputStream(
					socket.getOutputStream());
			RebindRequest req = new RebindRequest(key, stub);
			oos.writeObject(req);
			RebindResponse resp = (RebindResponse) ois.readObject();
			if (!resp.ok)
				throw new RemoteException("Bad Response.");
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
}
