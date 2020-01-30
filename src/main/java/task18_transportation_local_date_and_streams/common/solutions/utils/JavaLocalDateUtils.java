package task18_transportation_local_date_and_streams.common.solutions.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class JavaLocalDateUtils {

    private static final String PATTERN = "dd.MM.yyyy";

    private JavaLocalDateUtils() {

    }

    public static LocalDate valueOf(String dateStr, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(dateStr, formatter);
    }

    public static LocalDate valueOf(String dateStr) {
        return valueOf(dateStr, PATTERN);
    }

}
