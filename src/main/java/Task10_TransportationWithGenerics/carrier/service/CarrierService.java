package main.java.Task10_TransportationWithGenerics.carrier.service;

import main.java.Task10_TransportationWithGenerics.carrier.domain.Carrier;
import main.java.Task10_TransportationWithGenerics.common.business.service.CommonService;

import java.util.List;

public interface CarrierService extends CommonService<Carrier> {

    Carrier getByIdFetchingTransportations(Long id);

    List<Carrier> findByName(String name);

}
