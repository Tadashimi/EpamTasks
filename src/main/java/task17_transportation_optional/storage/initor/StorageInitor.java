package task17_transportation_optional.storage.initor;

import task17_transportation_optional.common.business.exception.checked.InitStorageException;

public interface StorageInitor {
    void initStorage() throws InitStorageException;
}
