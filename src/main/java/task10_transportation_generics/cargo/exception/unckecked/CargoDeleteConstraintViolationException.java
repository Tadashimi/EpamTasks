package  task10_transportation_generics.cargo.exception.unckecked;

import  task10_transportation_generics.common.business.exception.unchecked.OurCompanyException;

public class CargoDeleteConstraintViolationException extends OurCompanyException {

    private static final String MESSAGE = "Cant delete cargo with id '%s'. There are transportations which relates to it!";

    public CargoDeleteConstraintViolationException(String message) {
        super(message);
    }

    public CargoDeleteConstraintViolationException(long cargoId) {
        this(String.format(MESSAGE, cargoId));
    }
}
