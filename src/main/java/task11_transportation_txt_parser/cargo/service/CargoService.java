package task11_transportation_txt_parser.cargo.service;

import task11_transportation_txt_parser.cargo.domain.Cargo;
import task11_transportation_txt_parser.cargo.search.CargoSearchCondition;
import task11_transportation_txt_parser.common.business.service.CommonService;

import java.util.List;

public interface CargoService extends CommonService<Cargo, Long> {

    Cargo getByIdFetchingTransportations(Long id);

    List<Cargo> findByName(String name);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);

}
