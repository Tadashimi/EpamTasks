package main.java.Task9_TransportationWithException.cargo.service;

import main.java.Task9_TransportationWithException.cargo.domain.Cargo;
import main.java.Task9_TransportationWithException.cargo.search.CargoSearchCondition;
import main.java.Task9_TransportationWithException.common.business.service.CommonService;

import java.util.List;

public interface CargoService extends CommonService {
    void add(Cargo cargo);

    Cargo getById(Long id);

    List<Cargo> getAll();

    List<Cargo> findByName(String name);

    void update(Cargo cargo);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);
}
