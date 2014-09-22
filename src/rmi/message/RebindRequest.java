package rmi.message;

import rmi.core.Remote;

/**
 * Request sent from {@link rmi.core.RemoteServer} to
 * {@link rmi.registry.RegistryServer} to bind stub to give name
 * 
 * @author Chao
 *
 */
public class RebindRequest extends Request {

    /**
	 * 
	 */
    private static final long serialVersionUID = -836045415455500522L;
    public static final String TOKEN = "Rebind";

    public String name;
    public Remote stub;

    public RebindRequest() {
    }

    public RebindRequest(String name, Remote stub) {
        this.name = name;
        this.stub = stub;
    }

    @Override
    public String getToken() {
        return TOKEN;
    }
}