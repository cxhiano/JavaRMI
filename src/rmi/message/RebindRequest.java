package rmi.message;

import rmi.core.RemoteObjectRef;

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

    public RemoteObjectRef ref;

    public RebindRequest() {
    }

    public RebindRequest(RemoteObjectRef ref) {
        this.ref = ref;
    }

    @Override
    public String getToken() {
        return TOKEN;
    }
}