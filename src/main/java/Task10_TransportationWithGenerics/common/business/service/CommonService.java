package  Task10_TransportationWithGenerics.common.business.service;

import  Task10_TransportationWithGenerics.common.business.domain.BaseEntity;

import java.util.List;

public interface CommonService<T extends BaseEntity> {

    boolean deleteById(Long id);

    void printAll();

    void add(T entity);

    T getById(Long id);

    List<T> getAll();

    void update(T entity);

}
