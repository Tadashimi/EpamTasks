package  Task10_TransportationWithGenerics.carrier.service;

import  Task10_TransportationWithGenerics.carrier.domain.Carrier;
import  Task10_TransportationWithGenerics.common.business.service.CommonService;

import java.util.List;

public interface CarrierService extends CommonService<Carrier> {

    Carrier getByIdFetchingTransportations(Long id);

    List<Carrier> findByName(String name);

}
