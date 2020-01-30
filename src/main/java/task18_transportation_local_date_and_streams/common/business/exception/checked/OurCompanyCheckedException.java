package task18_transportation_local_date_and_streams.common.business.exception.checked;

public class OurCompanyCheckedException extends Exception {

    public OurCompanyCheckedException(String message) {
        super(message);
    }

    public OurCompanyCheckedException(String message, Exception cause) {
        super(message);
        this.initCause(cause);
    }
}
