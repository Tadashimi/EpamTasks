package task8_transportation_update_and_compare.cargo.service;

import task8_transportation_update_and_compare.cargo.domain.Cargo;
import task8_transportation_update_and_compare.cargo.service.cargoListComparator.CompareField;
import task8_transportation_update_and_compare.common.business.service.CommonService;

import java.util.List;

public interface CargoService extends CommonService {
    void add(Cargo cargo);

    Cargo getById(Long id);

    List<Cargo> getByName(String name);

    List<Cargo> sortCargo(CompareField compareField);

    Cargo update(Long id, Cargo cargo);
}
