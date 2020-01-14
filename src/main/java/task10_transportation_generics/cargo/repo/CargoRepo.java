package  task10_transportation_generics.cargo.repo;

import  task10_transportation_generics.cargo.domain.Cargo;
import  task10_transportation_generics.cargo.search.CargoSearchCondition;
import  task10_transportation_generics.common.business.repo.CommonRepo;

import java.util.List;

public interface CargoRepo extends CommonRepo<Cargo> {

    Cargo getByIdFetchingTransportations(long id);

    Cargo[] findByName(String name);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);

}
