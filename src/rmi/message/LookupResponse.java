package rmi.message;

import rmi.core.Remote;

/**
 * Response sent back from {@link rmi.registry.RegistryServer} to client with stub for given name
 * 
 * @author Chao
 *
 */
public class LookupResponse extends Response {

    /**
	 * 
	 */
    private static final long serialVersionUID = -1650026769834137261L;

    public Remote stub;
}
