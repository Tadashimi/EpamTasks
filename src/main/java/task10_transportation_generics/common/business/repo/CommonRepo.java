package  task10_transportation_generics.common.business.repo;

import  task10_transportation_generics.common.business.domain.BaseEntity;

import java.util.List;

public interface CommonRepo<T extends BaseEntity> {

    boolean deleteById(long id);

    void add(T entity);

    T getById(long id);

    List<T> getAll();

    void update(T entity);

}
