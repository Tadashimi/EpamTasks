package Task10_TransportationWithGenerics.common.business.service;

import java.util.List;

public interface CommonService<T> {

    boolean deleteById(Long id);

    void printAll();

    void add(T t);

    T getById(Long id);

    List<T> getAll();

    void update(T t);

}
