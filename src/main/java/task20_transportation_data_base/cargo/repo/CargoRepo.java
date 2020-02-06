package task20_transportation_data_base.cargo.repo;

import task20_transportation_data_base.cargo.domain.Cargo;
import task20_transportation_data_base.cargo.search.CargoSearchCondition;
import task20_transportation_data_base.common.business.repo.CommonRepo;

import java.util.List;
import java.util.Optional;

public interface CargoRepo extends CommonRepo<Cargo, Long> {

    Optional<Cargo> getByIdFetchingTransportations(long id);

    Cargo[] findByName(String name);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);
}
