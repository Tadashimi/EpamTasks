package Task12_TransportationWithXMLandExportToFile.storage.initor;

import Task12_TransportationWithXMLandExportToFile.storage.initor.impl.InMemoryStorageInitor;
import Task12_TransportationWithXMLandExportToFile.storage.initor.impl.TextFileDataInitor;
import Task12_TransportationWithXMLandExportToFile.storage.initor.impl.XMLFileDataInitor;

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
