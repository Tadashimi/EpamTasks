package main.java.Task9_TransportationWithException.cargo.exception;

import main.java.Task9_TransportationWithException.common.solutions.exception.TransportationException;

public class CargoDeleteException extends TransportationException {

    public CargoDeleteException(int errorCode, String message) {
        super(errorCode, message);
    }

}
