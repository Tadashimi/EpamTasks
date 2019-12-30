package Task12_TransportationWithXMLandExportToFile.cargo.repo;

import Task12_TransportationWithXMLandExportToFile.cargo.domain.Cargo;
import Task12_TransportationWithXMLandExportToFile.cargo.search.CargoSearchCondition;
import Task12_TransportationWithXMLandExportToFile.common.business.repo.CommonRepo;

import java.util.List;

public interface CargoRepo extends CommonRepo<Cargo, Long> {

    Cargo getByIdFetchingTransportations(long id);

    Cargo[] findByName(String name);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);

}
