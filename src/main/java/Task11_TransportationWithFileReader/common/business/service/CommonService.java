package Task11_TransportationWithFileReader.common.business.service;

import java.util.List;

public interface CommonService<TYPE, ID> {

    boolean deleteById(ID id);

    void printAll();

    void add(TYPE entity);

    TYPE getById(ID id);

    List<TYPE> getAll();

    void update(TYPE entity);

    void addAll(List<TYPE> entities);

}
