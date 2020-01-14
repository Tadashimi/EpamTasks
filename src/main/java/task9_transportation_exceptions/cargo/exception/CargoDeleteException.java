package  task9_transportation_exceptions.cargo.exception;

import  task9_transportation_exceptions.common.solutions.exception.TransportationException;

public class CargoDeleteException extends TransportationException {

    public CargoDeleteException(int errorCode, String message) {
        super(errorCode, message);
    }

}
