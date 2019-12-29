package Task7_TransportationWithCollections.cargo.repo;

import Task7_TransportationWithCollections.cargo.domain.Cargo;
import Task7_TransportationWithCollections.common.business.repo.CommonRepo;

public interface CargoRepo extends CommonRepo {

    void add(Cargo cargo);

    Cargo getById(long id);

    Cargo[] getByName(String name);

}
