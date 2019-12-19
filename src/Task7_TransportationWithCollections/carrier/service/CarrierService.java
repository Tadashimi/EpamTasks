package Task7_TransportationWithCollections.carrier.service;

import Task7_TransportationWithCollections.carrier.domain.Carrier;
import Task7_TransportationWithCollections.common.business.service.CommonService;

public interface CarrierService extends CommonService {

    void add(Carrier carrier);

    Carrier getById(Long id);

    Carrier[] getByName(String name);

}
