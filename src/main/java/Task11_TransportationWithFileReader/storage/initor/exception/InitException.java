package Task11_TransportationWithFileReader.storage.initor.exception;

import Task11_TransportationWithFileReader.common.business.exception.unchecked.OurCompanyException;

public class InitException extends OurCompanyException {

    public InitException(String message) {
        super(message);
    }
}
