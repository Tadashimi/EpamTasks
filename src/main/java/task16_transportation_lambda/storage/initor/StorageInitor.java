package task16_transportation_lambda.storage.initor;

import task16_transportation_lambda.common.business.exception.checked.InitStorageException;

public interface StorageInitor {
    void initStorage() throws InitStorageException;
}
