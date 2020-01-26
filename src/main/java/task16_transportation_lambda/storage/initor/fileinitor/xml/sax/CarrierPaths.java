package task16_transportation_lambda.storage.initor.fileinitor.xml.sax;

import java.util.HashSet;
import java.util.Set;

public final class CarrierPaths {
    public static final String CARRIERS = "data/carriers";
    public static final String CARRIER = CARRIERS + "/carrier";
    public static final String NAME = CARRIER + "/name";
    public static final String ADDRESS = CARRIER + "/address";
    public static final String TYPE = CARRIER + "/type";
    private static final Set<String> ALL_PATHS = new HashSet<>();

    static {
        ALL_PATHS.add(CARRIERS);
        ALL_PATHS.add(CARRIER);
        ALL_PATHS.add(NAME);
        ALL_PATHS.add(ADDRESS);
        ALL_PATHS.add(TYPE);
    }

    private CarrierPaths() {

    }

    public static boolean isCarrierPath(String path) {
        return ALL_PATHS.contains(path);
    }


}
