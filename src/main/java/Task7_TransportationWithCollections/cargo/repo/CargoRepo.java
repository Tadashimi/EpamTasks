package main.java.Task7_TransportationWithCollections.cargo.repo;

import main.java.Task7_TransportationWithCollections.cargo.domain.Cargo;
import main.java.Task7_TransportationWithCollections.common.business.repo.CommonRepo;

public interface CargoRepo extends CommonRepo {

    void add(Cargo cargo);

    Cargo getById(long id);

    Cargo[] getByName(String name);

}
