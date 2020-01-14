package task12_transportation_XML_paser_and_export_to_file.cargo.service;

import task12_transportation_XML_paser_and_export_to_file.cargo.domain.Cargo;
import task12_transportation_XML_paser_and_export_to_file.cargo.search.CargoSearchCondition;
import task12_transportation_XML_paser_and_export_to_file.common.business.service.CommonService;

import java.util.List;

public interface CargoService extends CommonService<Cargo, Long> {

    Cargo getByIdFetchingTransportations(Long id);

    List<Cargo> findByName(String name);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);

}
