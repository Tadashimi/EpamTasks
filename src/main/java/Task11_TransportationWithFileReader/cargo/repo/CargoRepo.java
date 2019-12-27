package Task11_TransportationWithFileReader.cargo.repo;

import Task11_TransportationWithFileReader.cargo.domain.Cargo;
import Task11_TransportationWithFileReader.cargo.search.CargoSearchCondition;
import Task11_TransportationWithFileReader.common.business.repo.CommonRepo;

import java.util.List;

public interface CargoRepo extends CommonRepo<Cargo, Long> {

    Cargo getByIdFetchingTransportations(long id);

    Cargo[] findByName(String name);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);

}
