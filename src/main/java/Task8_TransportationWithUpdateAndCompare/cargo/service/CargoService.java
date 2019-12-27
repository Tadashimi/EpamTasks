package main.java.Task8_TransportationWithUpdateAndCompare.cargo.service;

import main.java.Task8_TransportationWithUpdateAndCompare.cargo.domain.Cargo;
import main.java.Task8_TransportationWithUpdateAndCompare.cargo.service.sortCargoList.CompareField;
import main.java.Task8_TransportationWithUpdateAndCompare.common.business.service.CommonService;

import java.util.List;

public interface CargoService extends CommonService {
    void add(Cargo cargo);

    Cargo getById(Long id);

    List<Cargo> getByName(String name);

    List<Cargo> sortCargo(CompareField compareField);

    Cargo update(Long id, Cargo cargo);
}
