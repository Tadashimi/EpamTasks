package Task12_TransportationWithXMLandExportToFile.common.solutions.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class JavaUtilDataUtils {

    private static final String PATTERN = "dd.MM.yyyy";

    private JavaUtilDataUtils() {

    }

    public static Date valueOf(String dateStr, String pattern) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.parse(dateStr);
    }

    public static Date valueOf(String dateStr) throws ParseException {
        return valueOf(dateStr, PATTERN);
    }

    public static String dateToString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(PATTERN);
        return dateFormat.format(date);
    }

    public static String dateToString(Date date, String pattern) {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }
}
