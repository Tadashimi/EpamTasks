package task20_transportation_data_base.cargo.service;

import task20_transportation_data_base.cargo.domain.Cargo;
import task20_transportation_data_base.cargo.search.CargoSearchCondition;
import task20_transportation_data_base.common.business.service.CommonService;

import java.util.List;
import java.util.Optional;

public interface CargoService extends CommonService<Cargo, Long> {

    Optional<Cargo> getByIdFetchingTransportations(Long id);

    List<Cargo> findByName(String name);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);

}
