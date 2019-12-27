package main.java.Task8_TransportationWithUpdateAndCompare.cargo.repo;

import main.java.Task8_TransportationWithUpdateAndCompare.cargo.domain.Cargo;
import main.java.Task8_TransportationWithUpdateAndCompare.common.business.repo.CommonRepo;

public interface CargoRepo extends CommonRepo {

    void add(Cargo cargo);

    Cargo getById(long id);

    Cargo[] getByName(String name);

    Cargo[] getAll();

    Cargo update(long id, Cargo cargo);

}
