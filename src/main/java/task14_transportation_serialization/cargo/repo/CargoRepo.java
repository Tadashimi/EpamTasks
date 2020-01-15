package task14_transportation_serialization.cargo.repo;

import task14_transportation_serialization.cargo.domain.Cargo;
import task14_transportation_serialization.cargo.search.CargoSearchCondition;
import task14_transportation_serialization.common.business.repo.CommonRepo;

import java.util.List;

public interface CargoRepo extends CommonRepo<Cargo, Long> {

    Cargo getByIdFetchingTransportations(long id);

    Cargo[] findByName(String name);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);
}
