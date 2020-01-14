package  task9_transportation_exceptions.cargo.repo;

import  task9_transportation_exceptions.cargo.domain.Cargo;
import  task9_transportation_exceptions.cargo.search.CargoSearchCondition;
import  task9_transportation_exceptions.common.business.repo.CommonRepo;
import  task9_transportation_exceptions.transportation.domain.Transportation;

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
