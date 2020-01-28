package task17_transportation_optional.storage.initor.fileinitor.xml.sax;

import java.util.HashSet;
import java.util.Set;

public final class CargoPaths {
    public static final String CARGOS = "data/cargoes";
    public static final String CARGO = CARGOS + "/cargo";
    public static final String NAME = CARGO + "/name";
    public static final String WEIGHT = CARGO + "/weight";
    public static final String EXPIRATION_DATE = CARGO + "/expirationDate";
    public static final String STORE_TEMPERATURE = CARGO + "/storeTemperature";
    public static final String SIZE = CARGO + "/size";
    public static final String MATERIAL = CARGO + "/material";
    private static final Set<String> ALL_PATHS = new HashSet<>();

    static {
        ALL_PATHS.add(CARGOS);
        ALL_PATHS.add(CARGO);
        ALL_PATHS.add(NAME);
        ALL_PATHS.add(WEIGHT);
        ALL_PATHS.add(EXPIRATION_DATE);
        ALL_PATHS.add(STORE_TEMPERATURE);
        ALL_PATHS.add(SIZE);
        ALL_PATHS.add(MATERIAL);
    }

    private CargoPaths() {

    }

    public static boolean isCargoPath(String path) {
        return ALL_PATHS.contains(path);
    }
}
