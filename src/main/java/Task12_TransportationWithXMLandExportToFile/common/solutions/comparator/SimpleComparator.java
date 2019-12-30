package Task12_TransportationWithXMLandExportToFile.common.solutions.comparator;

import java.util.Comparator;

public final class SimpleComparator {


    public static final Comparator<String> STRING_COMPARATOR = new Comparator<String>() {
        @Override
        public int compare(String s1, String s2) {

            if (s1 == null && s2 == null) {
                return 0;
            } else if (s1 != null) {
                return s1.compareTo(s2);
            } else {
                return -1;
            }

        }
    };


}
