package task15_transportation_threads.storage.initor;

import task15_transportation_threads.common.business.exception.checked.InitStorageException;

public interface StorageInitor {
    void initStorage() throws InitStorageException;
}
