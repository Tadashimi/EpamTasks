package Task13_TransportataionWithSAXParsing.storage.initor;

import Task13_TransportataionWithSAXParsing.storage.initor.impl.InMemoryStorageInitor;
import Task13_TransportataionWithSAXParsing.storage.initor.impl.TextFileDataInitor;
import Task13_TransportataionWithSAXParsing.storage.initor.impl.XMLFileDOMDataInitor;
import Task13_TransportataionWithSAXParsing.storage.initor.impl.XMLFileSAXDataInitor;

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
            case XML_FILE_DOM: {
                return new XMLFileDOMDataInitor();
            }
            case XML_FILE_SAX: {
                return new XMLFileSAXDataInitor();
            }
            default: {
                throw new RuntimeException("Unknown storage init type " + initStorageType);
            }
        }
    }

}
