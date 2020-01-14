package task7_transportation_collections.cargo.repo;

import task7_transportation_collections.cargo.domain.Cargo;
import task7_transportation_collections.common.business.repo.CommonRepo;

public interface CargoRepo extends CommonRepo {

    void add(Cargo cargo);

    Cargo getById(long id);

    Cargo[] getByName(String name);

}
