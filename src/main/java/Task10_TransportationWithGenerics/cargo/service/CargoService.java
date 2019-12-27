package main.java.Task10_TransportationWithGenerics.cargo.service;

import main.java.Task10_TransportationWithGenerics.cargo.domain.Cargo;
import main.java.Task10_TransportationWithGenerics.cargo.search.CargoSearchCondition;
import main.java.Task10_TransportationWithGenerics.common.business.service.CommonService;

import java.util.List;

public interface CargoService extends CommonService<Cargo> {

    Cargo getByIdFetchingTransportations(Long id);

    List<Cargo> findByName(String name);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);

}
