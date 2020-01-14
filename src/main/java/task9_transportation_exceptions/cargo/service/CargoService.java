package  task9_transportation_exceptions.cargo.service;

import  task9_transportation_exceptions.cargo.domain.Cargo;
import  task9_transportation_exceptions.cargo.search.CargoSearchCondition;
import  task9_transportation_exceptions.common.business.service.CommonService;

import java.util.List;

public interface CargoService extends CommonService {
    void add(Cargo cargo);

    Cargo getById(Long id);

    List<Cargo> getAll();

    List<Cargo> findByName(String name);

    void update(Cargo cargo);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);
}
