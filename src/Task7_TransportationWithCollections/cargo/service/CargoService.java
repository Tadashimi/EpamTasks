package Task7_TransportationWithCollections.cargo.service;

import Task7_TransportationWithCollections.cargo.domain.Cargo;
import Task7_TransportationWithCollections.common.business.service.CommonService;

import java.util.List;

public interface CargoService extends CommonService {
    void add(Cargo cargo);

    Cargo getById(Long id);

    List<Cargo> getByName(String name);
}
