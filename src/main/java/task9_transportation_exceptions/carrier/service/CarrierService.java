package  task9_transportation_exceptions.carrier.service;

import  task9_transportation_exceptions.carrier.domain.Carrier;
import  task9_transportation_exceptions.common.business.service.CommonService;

import java.util.List;

public interface CarrierService extends CommonService {
    void add(Carrier carrier);

    Carrier getById(Long id);

    List<Carrier> findByName(String name);

    List<Carrier> getAll();

    void update(Carrier carrier);

}
