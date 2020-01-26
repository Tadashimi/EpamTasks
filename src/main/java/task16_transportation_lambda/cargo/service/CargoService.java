package task16_transportation_lambda.cargo.service;

import task16_transportation_lambda.cargo.domain.Cargo;
import task16_transportation_lambda.cargo.search.CargoSearchCondition;
import task16_transportation_lambda.common.business.service.CommonService;

import java.util.List;

public interface CargoService extends CommonService<Cargo, Long> {

    Cargo getByIdFetchingTransportations(Long id);

    List<Cargo> findByName(String name);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);

}
