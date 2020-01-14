package task11_transportation_txt_parser.storage.initor;

import task11_transportation_txt_parser.storage.initor.exception.InitException;

public interface StorageInitor {

    void initStorage();

    void initStorage(String filePath) throws InitException;

}
