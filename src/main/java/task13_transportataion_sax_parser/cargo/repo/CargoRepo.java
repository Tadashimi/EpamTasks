package task13_transportataion_sax_parser.cargo.repo;

import task13_transportataion_sax_parser.cargo.domain.Cargo;
import task13_transportataion_sax_parser.cargo.search.CargoSearchCondition;
import task13_transportataion_sax_parser.common.business.repo.CommonRepo;

import java.util.List;

public interface CargoRepo extends CommonRepo<Cargo, Long> {

    Cargo getByIdFetchingTransportations(long id);

    Cargo[] findByName(String name);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);

}
