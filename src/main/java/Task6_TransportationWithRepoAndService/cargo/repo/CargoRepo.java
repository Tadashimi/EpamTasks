package main.java.Task6_TransportationWithRepoAndService.cargo.repo;

import main.java.Task6_TransportationWithRepoAndService.cargo.domain.Cargo;
import main.java.Task6_TransportationWithRepoAndService.common.repo.BaseRepo;

public interface CargoRepo extends BaseRepo {

    void add(Cargo cargo);

    Cargo getById(long id);

    Cargo[] getByName(String name);
}
