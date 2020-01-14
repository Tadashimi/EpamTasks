package task7_transportation_collections.carrier.service;

import task7_transportation_collections.carrier.domain.Carrier;
import task7_transportation_collections.common.business.service.CommonService;

public interface CarrierService extends CommonService {

    void add(Carrier carrier);

    Carrier getById(Long id);

    Carrier[] getByName(String name);

}
