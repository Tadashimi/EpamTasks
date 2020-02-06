package task20_transportation_data_base.storage.initor;

import task20_transportation_data_base.common.business.exception.checked.InitStorageException;

public interface StorageInitor {
    void initStorage() throws InitStorageException;
}
