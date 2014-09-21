package rmi.registry;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import rmi.core.Remote;
import rmi.message.AuthRequest;
import rmi.message.AuthResponse;
import rmi.message.ListRequest;
import rmi.message.ListResponse;
import rmi.message.LookupRequest;
import rmi.message.LookupResponse;
import rmi.message.RebindRequest;
import rmi.message.RebindResponse;
import rmi.message.Request;
import rmi.message.Response;

public class RegistryServer {

	private static Map<String, Remote> map = new HashMap<String, Remote>();

	private static Response handle(Socket socket, Request object) {
		Response r;
		if (object instanceof LookupRequest) {
			LookupRequest req = (LookupRequest) object;
			LookupResponse resp = new LookupResponse();
			resp.stub = map.get(req.key);
			resp.ok = true;
			r = resp;
		} else if (object instanceof ListRequest) {
			ListResponse resp = new ListResponse();
			resp.keys = new ArrayList<String>(map.keySet());
			resp.ok = true;
			r = resp;
		} else if (object instanceof RebindRequest) {
			RebindRequest req = (RebindRequest) object;
			RebindResponse resp = new RebindResponse();
			resp.ok = true;
			r = resp;
			map.put(req.key, req.stub);
		} else if (object instanceof AuthRequest) {
			AuthResponse resp = new AuthResponse();
			resp.ok = true;
			r = resp;
		} else {
			r = new Response();
			r.ok = false;
		}
		return r;
	}

	private static void loop(ServerSocket listener) {
		Socket socket = null;
		try {
			socket = listener.accept();
			ObjectOutputStream oos = new ObjectOutputStream(
					socket.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(
					socket.getInputStream());
			Request msg = (Request) ois.readObject();
			oos.writeObject(handle(socket, msg));
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

	public static void main(String[] args) {
		ServerSocket listener = null;
		try {
			listener = new ServerSocket(Integer.parseInt(args[0]));
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
