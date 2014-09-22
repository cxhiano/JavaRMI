package rmi.registry;

import java.util.List;

import rmi.core.Remote;
import rmi.message.ListRequest;
import rmi.message.ListResponse;
import rmi.message.LookupRequest;
import rmi.message.LookupResponse;
import rmi.message.RebindRequest;
import rmi.message.Response;
import rmi.net.SocketRequest;

/**
 * Registry interface.
 * 
 * @author Chao
 *
 */
public class Registry {

    public String host;
    public int port;

    public Registry(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * 
     * @param name
     * @return stub corresponding to given name
     */
    public Remote lookup(String name) {
        LookupResponse resp = (LookupResponse) SocketRequest.request(host,
                port, new LookupRequest(name));

        Response.throwIfInvalid(resp);
        return resp.stub;
    }

    /**
     * Bind a stub to a name
     *
     * @param name
     * @param stub
     */
    public void rebind(String name, Remote stub) {
        SocketRequest.request(host, port, new RebindRequest(name, stub));
    }

    /**
     * 
     * @return all names on registry server
     */
    public List<String> list() {
        ListResponse resp = (ListResponse) SocketRequest.request(host, port,
                new ListRequest());
        Response.throwIfInvalid(resp);
        return resp.names;
    }
}
