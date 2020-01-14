package task11_transportation_txt_parser.storage.initor.exception;

import task11_transportation_txt_parser.common.business.exception.unchecked.OurCompanyException;

public class InitException extends OurCompanyException {

    public InitException(String message) {
        super(message);
    }
}
