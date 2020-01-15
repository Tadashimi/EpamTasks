package task14_transportation_serialization.storage.initor;

import task14_transportation_serialization.common.business.exception.checked.InitStorageException;

public interface StorageInitor {
    void initStorage() throws InitStorageException;
}
