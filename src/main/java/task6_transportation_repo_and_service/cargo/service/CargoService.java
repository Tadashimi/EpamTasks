package task6_transportation_repo_and_service.cargo.service;

import task6_transportation_repo_and_service.cargo.domain.Cargo;

public interface CargoService {
    void addCargo(Cargo cargo);

    Cargo getById(long id);

    Cargo[] getByName(String name);

    void deleteById(long id);
}
