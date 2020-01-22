package task15_transportation_threads.carrier.service;

import task15_transportation_threads.carrier.domain.Carrier;
import task15_transportation_threads.common.business.service.CommonService;

import java.util.List;

public interface CarrierService extends CommonService<Carrier, Long> {

    Carrier getByIdFetchingTransportations(Long id);

    List<Carrier> findByName(String name);

}
