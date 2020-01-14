package task12_transportation_XML_paser_and_export_to_file.cargo.repo;

import task12_transportation_XML_paser_and_export_to_file.cargo.domain.Cargo;
import task12_transportation_XML_paser_and_export_to_file.cargo.search.CargoSearchCondition;
import task12_transportation_XML_paser_and_export_to_file.common.business.repo.CommonRepo;

import java.util.List;

public interface CargoRepo extends CommonRepo<Cargo, Long> {

    Cargo getByIdFetchingTransportations(long id);

    Cargo[] findByName(String name);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);

}
