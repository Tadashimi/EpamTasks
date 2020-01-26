package task16_transportation_lambda.cargo.repo;

import task16_transportation_lambda.cargo.domain.Cargo;
import task16_transportation_lambda.cargo.search.CargoSearchCondition;
import task16_transportation_lambda.common.business.repo.CommonRepo;

import java.util.List;

public interface CargoRepo extends CommonRepo<Cargo, Long> {

    Cargo getByIdFetchingTransportations(long id);

    Cargo[] findByName(String name);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);
}
