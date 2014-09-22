package rmi.message;

import java.util.List;

/**
 * Response sent back from {@link rmi.registry.RegistryServer} to client with list of all names
 * 
 * @author Chao
 *
 */
public class ListResponse extends Response {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1242377821108367693L;

    public List<String> names;
}
