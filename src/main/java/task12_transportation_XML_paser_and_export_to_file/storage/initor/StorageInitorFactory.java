package task12_transportation_XML_paser_and_export_to_file.storage.initor;

import task12_transportation_XML_paser_and_export_to_file.storage.initor.impl.InMemoryStorageInitor;
import task12_transportation_XML_paser_and_export_to_file.storage.initor.impl.TextFileDataInitor;
import task12_transportation_XML_paser_and_export_to_file.storage.initor.impl.XMLFileDataInitor;

public final class StorageInitorFactory {

    private StorageInitorFactory() {

    }

    public static StorageInitor getStorageInitor(InitStorageType initStorageType) {
        switch (initStorageType) {

            case MEMORY: {
                return new InMemoryStorageInitor();
            }
            case TEXT_FILE: {
                return new TextFileDataInitor();
            }
            case XML_FILE: {
                return new XMLFileDataInitor();
            }
            default: {
                throw new RuntimeException("Unknown storage init type " + initStorageType);
            }
        }
    }

}
