package rmi.core;

import java.net.ServerSocket;
import java.net.Socket;

public class RegistryServer {
	private static void loop(ServerSocket listener) {

		Socket socket = null;
		try {
			socket = listener.accept();
		} catch (Exception e) {
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
