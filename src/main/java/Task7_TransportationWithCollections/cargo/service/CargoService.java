package main.java.Task7_TransportationWithCollections.cargo.service;

import main.java.Task7_TransportationWithCollections.cargo.domain.Cargo;
import main.java.Task7_TransportationWithCollections.common.business.service.CommonService;

public interface CargoService extends CommonService {
    void add(Cargo cargo);

    Cargo getById(Long id);

    Cargo[] getByName(String name);
}
