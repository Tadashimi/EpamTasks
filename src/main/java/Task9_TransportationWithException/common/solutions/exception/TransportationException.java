package main.java.Task9_TransportationWithException.common.solutions.exception;

public abstract class TransportationException extends RuntimeException {
    protected final int errorCode;

    public TransportationException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

}
