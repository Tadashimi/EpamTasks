package  task10_transportation_generics.carrier.service;

import  task10_transportation_generics.carrier.domain.Carrier;
import  task10_transportation_generics.common.business.service.CommonService;

import java.util.List;

public interface CarrierService extends CommonService<Carrier> {

    Carrier getByIdFetchingTransportations(Long id);

    List<Carrier> findByName(String name);

}
