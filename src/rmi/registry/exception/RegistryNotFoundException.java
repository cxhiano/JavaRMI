package rmi.registry.exception;

public class RegistryNotFoundException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -3511072729305131203L;

    public RegistryNotFoundException() {

    }

    public RegistryNotFoundException(String message) {
        super(message);
    }

}
