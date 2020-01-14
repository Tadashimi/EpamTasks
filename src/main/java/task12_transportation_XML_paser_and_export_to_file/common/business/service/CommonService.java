package task12_transportation_XML_paser_and_export_to_file.common.business.service;

import java.util.List;

public interface CommonService<TYPE, ID> {
    TYPE findById(ID id);

    void save(TYPE entity);

    boolean update(TYPE entity);

    boolean deleteById(ID id);

    List<TYPE> getAll();

    int countAll();

    void printAll();
}
