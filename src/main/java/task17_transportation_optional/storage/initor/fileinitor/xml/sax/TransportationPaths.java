package task17_transportation_optional.storage.initor.fileinitor.xml.sax;

import java.util.HashSet;
import java.util.Set;

public final class TransportationPaths {
    public static final String TRANSPORTATIONS = "data/transportations";
    public static final String TRANSPORTATION = TRANSPORTATIONS + "/transportation";
    public static final String BILLTO = TRANSPORTATION + "/billto";
    public static final String TRANSPORTATION_BEGIN_DATE = TRANSPORTATION + "/transportationBeginDate";
    public static final String DESCRIPTION = TRANSPORTATION + "/description";
    private static final Set<String> ALL_PATHS = new HashSet<>();

    static {
        ALL_PATHS.add(TRANSPORTATIONS);
        ALL_PATHS.add(TRANSPORTATION);
        ALL_PATHS.add(BILLTO);
        ALL_PATHS.add(TRANSPORTATION_BEGIN_DATE);
        ALL_PATHS.add(DESCRIPTION);
    }

    private TransportationPaths() {

    }

    public static boolean isTransportationPath(String path) {
        return ALL_PATHS.contains(path);
    }
}
