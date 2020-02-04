package task18_transportation_local_date_and_streams.common.solutions.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class JavaLocalDateUtils {

    private static final String DEFAULT_PATTERN = "dd.MM.yyyy";
    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_PATTERN);

    private JavaLocalDateUtils() {

    }

    public static LocalDate valueOf(String dateStr) {
        return LocalDate.parse(dateStr, DEFAULT_FORMATTER);
    }

}
