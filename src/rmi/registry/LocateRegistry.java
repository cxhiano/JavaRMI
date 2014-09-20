package rmi.registry;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class LocateRegistry {
	public static Registry getRegistry(String host, int port) {
		// open socket.
		Socket socket = null;
		try {
			socket = new Socket(host, port);
			// get TCP streams and wrap them.
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

			// ask.
			out.println("who are you?");

			// // gets answer.
			// if ((in.readLine()).equals("I am a simple registry.")) {
			// return new Registry(host, port);
			// } else {
			// System.out.println("somebody is there but not a  registry!");
			// return null;
			// }
			return null;
		} catch (Exception e) {
			System.out.println("nobody is there!" + e);
			return null;
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
