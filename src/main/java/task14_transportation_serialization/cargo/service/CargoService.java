package task14_transportation_serialization.cargo.service;

import task14_transportation_serialization.cargo.domain.Cargo;
import task14_transportation_serialization.cargo.search.CargoSearchCondition;
import task14_transportation_serialization.common.business.service.CommonService;

import java.util.List;

public interface CargoService extends CommonService<Cargo, Long> {

    Cargo getByIdFetchingTransportations(Long id);

    List<Cargo> findByName(String name);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);

}
