package task18_transportation_local_date_and_streams.storage.initor;

import task18_transportation_local_date_and_streams.common.business.exception.checked.InitStorageException;

public interface StorageInitor {
    void initStorage() throws InitStorageException;
}
