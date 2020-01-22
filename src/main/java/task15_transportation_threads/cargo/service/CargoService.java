package task15_transportation_threads.cargo.service;

import task15_transportation_threads.cargo.domain.Cargo;
import task15_transportation_threads.cargo.search.CargoSearchCondition;
import task15_transportation_threads.common.business.service.CommonService;

import java.util.List;

public interface CargoService extends CommonService<Cargo, Long> {

    Cargo getByIdFetchingTransportations(Long id);

    List<Cargo> findByName(String name);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);

}
