package Task13_TransportataionWithSAXParsing.cargo.service;

import Task13_TransportataionWithSAXParsing.cargo.domain.Cargo;
import Task13_TransportataionWithSAXParsing.cargo.search.CargoSearchCondition;
import Task13_TransportataionWithSAXParsing.common.business.service.CommonService;

import java.util.List;

public interface CargoService extends CommonService<Cargo, Long> {

    Cargo getByIdFetchingTransportations(Long id);

    List<Cargo> findByName(String name);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);

}
