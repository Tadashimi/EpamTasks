package task14_transportation_serialization.storage.initor;

import task14_transportation_serialization.storage.initor.fileinitor.TextFileDataInitor;
import task14_transportation_serialization.storage.initor.fileinitor.xml.dom.XmlDomFileDataInitor;
import task14_transportation_serialization.storage.initor.fileinitor.xml.sax.XmlSaxFileDataInitor;

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
            case XML_DOM_FILE: {
                return new XmlDomFileDataInitor();
            }
            case XML_SAX_FILE: {
                return new XmlSaxFileDataInitor();
            }
            default: {
                throw new RuntimeException("Unknown storage init type " + initStorageType);
            }
        }
    }

}
