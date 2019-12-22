package Task9_TransportationWithException.cargo.repo;

import Task9_TransportationWithException.cargo.domain.Cargo;
import Task9_TransportationWithException.cargo.search.CargoSearchCondition;
import Task9_TransportationWithException.common.business.repo.CommonRepo;
import Task9_TransportationWithException.transportation.domain.Transportation;

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
