package Task7_TransportationWithCollections.cargo.repo;

import Task7_TransportationWithCollections.cargo.domain.Cargo;
import Task7_TransportationWithCollections.common.business.repo.CommonRepo;

import java.util.List;

public interface CargoRepo extends CommonRepo {

    void add(Cargo cargo);

    Cargo getById(long id);

    List<Cargo> getByName(String name);

}
