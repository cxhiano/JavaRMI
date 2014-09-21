package rmi.registry.exception;

public class UnknownRequestException extends RuntimeException {
    public UnknownRequestException() {}

    public UnknownRequestException(String message) {
        super(message);
    }

}
