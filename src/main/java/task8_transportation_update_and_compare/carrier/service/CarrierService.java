package task8_transportation_update_and_compare.carrier.service;

import task8_transportation_update_and_compare.carrier.domain.Carrier;
import task8_transportation_update_and_compare.common.business.service.CommonService;

import java.util.List;

public interface CarrierService extends CommonService {

    void add(Carrier carrier);

    Carrier getById(Long id);

    List<Carrier> getByName(String name);

    Carrier update(Long id, Carrier carrier);

}
