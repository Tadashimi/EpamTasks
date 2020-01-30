package task17_transportation_optional.cargo.service;

import task17_transportation_optional.cargo.domain.Cargo;
import task17_transportation_optional.cargo.search.CargoSearchCondition;
import task17_transportation_optional.common.business.service.CommonService;

import java.util.List;
import java.util.Optional;

public interface CargoService extends CommonService<Cargo, Long> {

    Optional<Cargo> getByIdFetchingTransportations(Long id);

    List<Cargo> findByName(String name);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);

}
