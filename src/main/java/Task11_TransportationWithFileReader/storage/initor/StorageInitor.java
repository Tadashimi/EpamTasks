package Task11_TransportationWithFileReader.storage.initor;

import Task11_TransportationWithFileReader.storage.initor.exception.InitException;

public interface StorageInitor {

    void initStorage();

    void initStorage(String filePath) throws InitException;

}
