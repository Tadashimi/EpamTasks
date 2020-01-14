package  task9_transportation_exceptions.carrier.exception;

import  task9_transportation_exceptions.common.solutions.exception.TransportationException;

public class CarrierDeleteException extends TransportationException {

    public CarrierDeleteException(int errorCode, String message) {
        super(errorCode, message);
    }

}
