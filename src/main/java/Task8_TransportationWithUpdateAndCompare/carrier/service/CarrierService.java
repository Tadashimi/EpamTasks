package main.java.Task8_TransportationWithUpdateAndCompare.carrier.service;

import main.java.Task8_TransportationWithUpdateAndCompare.carrier.domain.Carrier;
import main.java.Task8_TransportationWithUpdateAndCompare.common.business.service.CommonService;

import java.util.List;

public interface CarrierService extends CommonService {

    void add(Carrier carrier);

    Carrier getById(Long id);

    List<Carrier> getByName(String name);

    Carrier update(Long id, Carrier carrier);

}
