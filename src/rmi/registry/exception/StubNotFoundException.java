package rmi.registry.exception;

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
