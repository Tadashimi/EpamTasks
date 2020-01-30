package task18_transportation_local_date_and_streams.common.solutions.comparator;

import java.util.Comparator;

public final class SimpleComparator {


    public static final Comparator<String> STRING_COMPARATOR = (s1, s2) -> {
        if (s1 == null && s2 == null) {
            return 0;
        } else if (s1 != null) {
            return s1.compareTo(s2);
        } else {
            return -1;
        }
    };


}
