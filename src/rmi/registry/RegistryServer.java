package rmi.registry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import rmi.core.Constants;
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
import rmi.net.SocketRequestHandler;
import rmi.net.SocketServer;
import rmi.registry.exception.StubNotFoundException;

/**
 * RegistryServer for register stub to name
 * 
 * @author Chao
 *
 */
public class RegistryServer {

    private static Map<String, Remote> map = new ConcurrentHashMap<String, Remote>();

    private static final SocketRequestHandler LOOKUP_HANDLER = new SocketRequestHandler() {

        @Override
        public Response handle(Request request) {
            LookupRequest req = (LookupRequest) request;
            LookupResponse resp = new LookupResponse();
            resp.stub = map.get(req.name);
            if (resp.stub == null)
                resp.e = new StubNotFoundException(String.format(
                        "No stub for %s", req.name));
            return resp;
        }

    };

    private static final SocketRequestHandler LIST_HANDLER = new SocketRequestHandler() {

        @Override
        public Response handle(Request request) {
            ListResponse resp = new ListResponse();
            resp.names = new ArrayList<String>(map.keySet());
            return resp;
        }

    };

    private static final SocketRequestHandler REBIND_HANDLER = new SocketRequestHandler() {

        @Override
        public Response handle(Request request) {
            RebindRequest req = (RebindRequest) request;
            RebindResponse resp = new RebindResponse();
            map.put(req.name, req.stub);
            return resp;
        }

    };

    private static final SocketRequestHandler AUTH_HANDLER = new SocketRequestHandler() {

        @Override
        public Response handle(Request request) {

            AuthResponse resp = new AuthResponse();
            return resp;
        }

    };

    public static void main(String[] args) throws IOException {
        try {
            SocketServer server = SocketServer.getServer(Constants.DEFAULT_REGISTRY_PORT);
            server.bindHandler(LookupRequest.TOKEN, LOOKUP_HANDLER);
            server.bindHandler(ListRequest.TOKEN, LIST_HANDLER);
            server.bindHandler(RebindRequest.TOKEN, REBIND_HANDLER);
            server.bindHandler(AuthRequest.TOKEN, AUTH_HANDLER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
