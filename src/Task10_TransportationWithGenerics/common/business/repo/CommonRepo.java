package Task10_TransportationWithGenerics.common.business.repo;

import java.util.List;

public interface CommonRepo<T> {

    boolean deleteById(long id);

    void add(T entity);

    T getById(long id);

    List<T> getAll();

    void update(T entity);

}