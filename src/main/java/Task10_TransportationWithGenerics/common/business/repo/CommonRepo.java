package  Task10_TransportationWithGenerics.common.business.repo;

import  Task10_TransportationWithGenerics.common.business.domain.BaseEntity;

import java.util.List;

public interface CommonRepo<T extends BaseEntity> {

    boolean deleteById(long id);

    void add(T entity);

    T getById(long id);

    List<T> getAll();

    void update(T entity);

}
