package main.java.Task9_TransportationWithException.cargo.repo;

import main.java.Task9_TransportationWithException.cargo.domain.Cargo;
import main.java.Task9_TransportationWithException.cargo.search.CargoSearchCondition;
import main.java.Task9_TransportationWithException.common.business.repo.CommonRepo;
import main.java.Task9_TransportationWithException.transportation.domain.Transportation;

import java.util.List;

public interface CargoRepo extends CommonRepo {

    void add(Cargo cargo);

    Cargo getById(long id);

    Cargo[] findByName(String name);

    List<Cargo> getAll();

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);

    void update(Cargo cargo);

    default void removeTransportation(Cargo cargo, Transportation transportation) {
        if (cargo !=  null) {
            cargo.removeTransportation(transportation);
        }
    }
}
