package rmi.registry;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.json.JSONObject;

public class RegistryServer {
	private static void loop(ServerSocket listener) {
		Socket socket = null;
		try {
			socket = listener.accept();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			StringBuilder builder = new StringBuilder();
		    String str;
		    while ((str = in.readLine()) != null)
		        builder.append(str);
		    JSONObject object = new JSONObject(builder.toString());
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
