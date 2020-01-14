package task11_transportation_txt_parser.cargo.repo;

import task11_transportation_txt_parser.cargo.domain.Cargo;
import task11_transportation_txt_parser.cargo.search.CargoSearchCondition;
import task11_transportation_txt_parser.common.business.repo.CommonRepo;

import java.util.List;

public interface CargoRepo extends CommonRepo<Cargo, Long> {

    Cargo getByIdFetchingTransportations(long id);

    Cargo[] findByName(String name);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);

}
