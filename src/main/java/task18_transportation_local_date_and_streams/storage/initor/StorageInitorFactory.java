package task18_transportation_local_date_and_streams.storage.initor;

import task18_transportation_local_date_and_streams.storage.initor.fileinitor.TextFileDataInitor;
import task18_transportation_local_date_and_streams.storage.initor.fileinitor.xml.dom.XmlDomFileDataInitor;
import task18_transportation_local_date_and_streams.storage.initor.fileinitor.xml.sax.XmlSaxFileDataInitor;

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
            case CONCURRENT_INITOR: {
                return new ConcurrentInitor();
            }
            default: {
                throw new RuntimeException("Unknown storage init type " + initStorageType);
            }
        }
    }

}
