package task15_transportation_threads.carrier.exception.unchecked;

import task15_transportation_threads.common.business.exception.unchecked.OurCompanyUncheckedException;

public class CarrierDeleteConstraintViolationException extends OurCompanyUncheckedException {

    private static final String MESSAGE = "Cant delete carrier with id '%s'. There are transportations which relates to it!";

    public CarrierDeleteConstraintViolationException(String message) {
        super(message);
    }

    public CarrierDeleteConstraintViolationException(long carrierId) {
        this(String.format(MESSAGE, carrierId));
    }
}
