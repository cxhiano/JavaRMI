package rmi.registry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import rmi.core.RemoteObjectReference;

public class Registry {
	public String host;
	public int port;

	public Registry(String host, int port) {
		this.host = host;
		this.port = port;
	}

	/**
	 * 
	 * @param serviceName
	 * @return
	 * @throws IOException
	 */
	public RemoteObjectReference lookup(String serviceName) throws IOException {
		Socket socket = new Socket(this.host, this.port);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

		System.out.println("stream made.");

		// it is locate request, with a service name.
		out.println("lookup");
		out.println(serviceName);

		System.out.println("command and service name sent.");

		RemoteObjectReference ref = null;

		socket.close();

		return ref;
	}

	/**
	 * 
	 * @param serviceName
	 * @param ref
	 * @throws IOException
	 */
	public void rebind(String serviceName, RemoteObjectReference ref)
			throws IOException {
		Socket soc = new Socket(this.host, this.port);

		BufferedReader in = new BufferedReader(new InputStreamReader(
				soc.getInputStream()));
		PrintWriter out = new PrintWriter(soc.getOutputStream(), true);

		// it is a rebind request, with a service name and ROR.
		// out.println("rebind");
		// out.println(serviceName);
		// out.println(ror.IP_adr);
		// out.println(ror.Port);
		// out.println(ror.Obj_Key);
		// out.println(ror.Remote_Interface_Name);

		// it also gets an ack, but this is not used.
		// String ack = in.readLine();

		// close the socket.
		soc.close();
	}
}
