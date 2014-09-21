package rmi.registry;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import rmi.core.Remote;
import rmi.core.SocketHandler;
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

	private static SocketHandler handler = new SocketHandler() {

		@Override
		public Response handle(Request request) {
			Response r;
			if (request instanceof LookupRequest) {
				LookupRequest req = (LookupRequest) request;
				LookupResponse resp = new LookupResponse();
				resp.stub = map.get(req.key);
				resp.ok = true;
				r = resp;
			} else if (request instanceof ListRequest) {
				ListResponse resp = new ListResponse();
				resp.keys = new ArrayList<String>(map.keySet());
				resp.ok = true;
				r = resp;
			} else if (request instanceof RebindRequest) {
				RebindRequest req = (RebindRequest) request;
				RebindResponse resp = new RebindResponse();
				resp.ok = true;
				r = resp;
				map.put(req.key, req.stub);
			} else if (request instanceof AuthRequest) {
				AuthResponse resp = new AuthResponse();
				resp.ok = true;
				r = resp;
			} else {
				r = new Response();
				r.ok = false;
			}
			return r;
		}
		
	};

	public static void main(String[] args) {
		SocketHandler.serve(Registry.DEFAULT_PORT, handler);
	}
}
