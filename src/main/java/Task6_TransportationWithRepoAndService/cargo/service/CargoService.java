package Task6_TransportationWithRepoAndService.cargo.service;

import Task6_TransportationWithRepoAndService.cargo.domain.Cargo;

public interface CargoService {
    void addCargo(Cargo cargo);

    Cargo getById(long id);

    Cargo[] getByName(String name);

    void deleteById(long id);
}
