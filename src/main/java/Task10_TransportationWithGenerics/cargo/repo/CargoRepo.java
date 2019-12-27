package main.java.Task10_TransportationWithGenerics.cargo.repo;

import main.java.Task10_TransportationWithGenerics.cargo.domain.Cargo;
import main.java.Task10_TransportationWithGenerics.cargo.search.CargoSearchCondition;
import main.java.Task10_TransportationWithGenerics.common.business.repo.CommonRepo;

import java.util.List;

public interface CargoRepo extends CommonRepo<Cargo> {

    Cargo getByIdFetchingTransportations(long id);

    Cargo[] findByName(String name);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);

}
