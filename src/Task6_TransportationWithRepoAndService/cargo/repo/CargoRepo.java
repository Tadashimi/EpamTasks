package Task6_TransportationWithRepoAndService.cargo.repo;

import Task6_TransportationWithRepoAndService.cargo.domain.Cargo;

public interface CargoRepo {

    void addCargo(Cargo cargo);

    Cargo getById(long id);

    Cargo[] getByName(String name);

    void deleteById(long id);
}
