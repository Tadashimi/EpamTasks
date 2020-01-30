package task18_transportation_local_date_and_streams.cargo.service;

import task18_transportation_local_date_and_streams.cargo.domain.Cargo;
import task18_transportation_local_date_and_streams.cargo.search.CargoSearchCondition;
import task18_transportation_local_date_and_streams.common.business.service.CommonService;

import java.util.List;
import java.util.Optional;

public interface CargoService extends CommonService<Cargo, Long> {

    Optional<Cargo> getByIdFetchingTransportations(Long id);

    List<Cargo> findByName(String name);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);

}
