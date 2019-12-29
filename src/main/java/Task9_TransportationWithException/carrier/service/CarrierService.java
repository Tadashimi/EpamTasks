package  Task9_TransportationWithException.carrier.service;

import  Task9_TransportationWithException.carrier.domain.Carrier;
import  Task9_TransportationWithException.common.business.service.CommonService;

import java.util.List;

public interface CarrierService extends CommonService {
    void add(Carrier carrier);

    Carrier getById(Long id);

    List<Carrier> findByName(String name);

    List<Carrier> getAll();

    void update(Carrier carrier);

}
