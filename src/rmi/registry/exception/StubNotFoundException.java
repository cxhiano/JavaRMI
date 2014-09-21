package rmi.registry.exception;

public class StubNotFoundException extends RuntimeException {
    public StubNotFoundException() {

    }

    public StubNotFoundException(String message) {
        super(message);
    }

}
