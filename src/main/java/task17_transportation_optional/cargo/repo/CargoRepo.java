package task17_transportation_optional.cargo.repo;

import task17_transportation_optional.cargo.domain.Cargo;
import task17_transportation_optional.cargo.search.CargoSearchCondition;
import task17_transportation_optional.common.business.repo.CommonRepo;

import java.util.List;
import java.util.Optional;

public interface CargoRepo extends CommonRepo<Cargo, Long> {

    Optional<Cargo> getByIdFetchingTransportations(long id);

    Cargo[] findByName(String name);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);
}
