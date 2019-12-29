package Task8_TransportationWithUpdateAndCompare.cargo.service;

import Task8_TransportationWithUpdateAndCompare.cargo.domain.Cargo;
import Task8_TransportationWithUpdateAndCompare.cargo.service.cargoListComparator.CompareField;
import Task8_TransportationWithUpdateAndCompare.common.business.service.CommonService;

import java.util.List;

public interface CargoService extends CommonService {
    void add(Cargo cargo);

    Cargo getById(Long id);

    List<Cargo> getByName(String name);

    List<Cargo> sortCargo(CompareField compareField);

    Cargo update(Long id, Cargo cargo);
}
