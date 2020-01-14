package task6_transportation_repo_and_service.cargo.repo;

import task6_transportation_repo_and_service.cargo.domain.Cargo;
import task6_transportation_repo_and_service.common.repo.BaseRepo;

public interface CargoRepo extends BaseRepo {

    void add(Cargo cargo);

    Cargo getById(long id);

    Cargo[] getByName(String name);
}
