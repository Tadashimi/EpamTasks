package Task13_TransportataionWithSAXParsing.storage.initor;

import Task13_TransportataionWithSAXParsing.common.business.exception.checked.InitStorageException;

public interface StorageInitor {
    void initStorage() throws InitStorageException;
}
