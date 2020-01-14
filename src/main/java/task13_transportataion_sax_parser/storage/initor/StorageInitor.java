package task13_transportataion_sax_parser.storage.initor;

import task13_transportataion_sax_parser.common.business.exception.checked.InitStorageException;

public interface StorageInitor {
    void initStorage() throws InitStorageException;
}
