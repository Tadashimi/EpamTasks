package task13_transportataion_sax_parser.cargo.service;

import task13_transportataion_sax_parser.cargo.domain.Cargo;
import task13_transportataion_sax_parser.cargo.search.CargoSearchCondition;
import task13_transportataion_sax_parser.common.business.service.CommonService;

import java.util.List;

public interface CargoService extends CommonService<Cargo, Long> {

    Cargo getByIdFetchingTransportations(Long id);

    List<Cargo> findByName(String name);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);

}
