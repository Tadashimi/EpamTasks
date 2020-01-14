package task7_transportation_collections.cargo.service;

import task7_transportation_collections.cargo.domain.Cargo;
import task7_transportation_collections.common.business.service.CommonService;

public interface CargoService extends CommonService {
    void add(Cargo cargo);

    Cargo getById(Long id);

    Cargo[] getByName(String name);
}
