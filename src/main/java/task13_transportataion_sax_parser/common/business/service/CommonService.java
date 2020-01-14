package task13_transportataion_sax_parser.common.business.service;

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
