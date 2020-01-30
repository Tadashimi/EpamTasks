package task18_transportation_local_date_and_streams.cargo.repo;

import task18_transportation_local_date_and_streams.cargo.domain.Cargo;
import task18_transportation_local_date_and_streams.cargo.search.CargoSearchCondition;
import task18_transportation_local_date_and_streams.common.business.repo.CommonRepo;

import java.util.List;
import java.util.Optional;

public interface CargoRepo extends CommonRepo<Cargo, Long> {

    Optional<Cargo> getByIdFetchingTransportations(long id);

    Cargo[] findByName(String name);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);
}
