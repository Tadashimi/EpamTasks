package Task13_TransportataionWithSAXParsing.cargo.repo;

import Task13_TransportataionWithSAXParsing.cargo.domain.Cargo;
import Task13_TransportataionWithSAXParsing.cargo.search.CargoSearchCondition;
import Task13_TransportataionWithSAXParsing.common.business.repo.CommonRepo;

import java.util.List;

public interface CargoRepo extends CommonRepo<Cargo, Long> {

    Cargo getByIdFetchingTransportations(long id);

    Cargo[] findByName(String name);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);

}
