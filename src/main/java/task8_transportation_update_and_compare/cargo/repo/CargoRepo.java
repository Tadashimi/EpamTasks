package task8_transportation_update_and_compare.cargo.repo;

import task8_transportation_update_and_compare.cargo.domain.Cargo;
import task8_transportation_update_and_compare.common.business.repo.CommonRepo;

public interface CargoRepo extends CommonRepo {

    void add(Cargo cargo);

    Cargo getById(long id);

    Cargo[] getByName(String name);

    Cargo[] getAll();

    Cargo update(long id, Cargo cargo);

}
