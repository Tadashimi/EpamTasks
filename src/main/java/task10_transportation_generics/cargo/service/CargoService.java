package  task10_transportation_generics.cargo.service;

import  task10_transportation_generics.cargo.domain.Cargo;
import  task10_transportation_generics.cargo.search.CargoSearchCondition;
import  task10_transportation_generics.common.business.service.CommonService;

import java.util.List;

public interface CargoService extends CommonService<Cargo> {

    Cargo getByIdFetchingTransportations(Long id);

    List<Cargo> findByName(String name);

    List<Cargo> search(CargoSearchCondition cargoSearchCondition);

}
