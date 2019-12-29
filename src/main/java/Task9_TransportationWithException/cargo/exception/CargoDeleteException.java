package  Task9_TransportationWithException.cargo.exception;

import  Task9_TransportationWithException.common.solutions.exception.TransportationException;

public class CargoDeleteException extends TransportationException {

    public CargoDeleteException(int errorCode, String message) {
        super(errorCode, message);
    }

}
