package  task10_transportation_generics.common.business.service;

import  task10_transportation_generics.common.business.domain.BaseEntity;

import java.util.List;

public interface CommonService<T extends BaseEntity> {

    boolean deleteById(Long id);

    void printAll();

    void add(T entity);

    T getById(Long id);

    List<T> getAll();

    void update(T entity);

}
