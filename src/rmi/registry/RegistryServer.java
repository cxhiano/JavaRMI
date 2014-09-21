package rmi.registry;

import java.io.IOException;
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
import rmi.net.*;
import rmi.registry.exception.*;

public class RegistryServer {

	private static Map<String, Remote> map = new HashMap<String, Remote>();

	private static SocketRequestHandler handler = new SocketRequestHandler() {

		@Override
		public Response handle(Request request) {
			Response r;
			if (request instanceof LookupRequest) {	//Client ask for a stub
				LookupRequest req = (LookupRequest) request;
				LookupResponse resp = new LookupResponse();
				resp.stub = map.get(req.key);
				if (resp.stub == null)
					resp.e = new StubNotFoundException(
								String.format("No stub for %s", req.key));
				r = resp;
			} else if (request instanceof ListRequest) { //List all stubs
				ListResponse resp = new ListResponse();
				resp.keys = new ArrayList<String>(map.keySet());
				r = resp;
			} else if (request instanceof RebindRequest) {
				RebindRequest req = (RebindRequest) request;
				RebindResponse resp = new RebindResponse();
				r = resp;
				map.put(req.key, req.stub);
			} else if (request instanceof AuthRequest) {
				AuthResponse resp = new AuthResponse();
				r = resp;
			} else {
				r = new Response();
				r.e = new UnknownRequestException("Unknown Request");
			}
			return r;
		}

	};

	public static void main(String[] args) throws IOException {
		try {
			SocketServer server = SocketServer.getServer(Registry.DEFAULT_PORT);
			server.bindHandler(handler);
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
