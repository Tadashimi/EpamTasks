package task15_transportation_threads.cargo.repo;

import task15_transportation_threads.cargo.domain.Cargo;
import task15_transportation_threads.cargo.search.CargoSearchCondition;
import task15_transportation_threads.common.business.repo.CommonRepo;

import java.util.List;

public interface CargoRepo extends CommonRepo<Cargo, Long> {

    Cargo getByIdFetchingTransportations(long id);

    Cargo[] findByName(String name);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);
}
