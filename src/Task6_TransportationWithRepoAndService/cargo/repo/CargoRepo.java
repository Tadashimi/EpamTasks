package Task6_TransportationWithRepoAndService.cargo.repo;

import Task6_TransportationWithRepoAndService.cargo.domain.Cargo;
import Task6_TransportationWithRepoAndService.common.repo.BaseRepo;

public interface CargoRepo extends BaseRepo {

    void add(Cargo cargo);

    Cargo getById(long id);

    Cargo[] getByName(String name);
}
