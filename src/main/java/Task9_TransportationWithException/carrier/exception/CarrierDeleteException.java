package main.java.Task9_TransportationWithException.carrier.exception;

import main.java.Task9_TransportationWithException.common.solutions.exception.TransportationException;

public class CarrierDeleteException extends TransportationException {

    public CarrierDeleteException(int errorCode, String message) {
        super(errorCode, message);
    }

}
