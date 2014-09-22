package rmi.registry.exception;

/**
 * Exception representing a name is not binded to a stub on
 * {@link rmi.registry.RegistryServer}
 * 
 * @author Chao
 *
 */
public class StubNotFoundException extends RuntimeException {
    /**
	 * 
	 */
    private static final long serialVersionUID = 4723720683944630901L;

    public StubNotFoundException() {

    }

    public StubNotFoundException(String message) {
        super(message);
    }

}
