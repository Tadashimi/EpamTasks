package Task13_TransportataionWithSAXParsing.carrier.service;

import Task13_TransportataionWithSAXParsing.carrier.domain.Carrier;
import Task13_TransportataionWithSAXParsing.common.business.service.CommonService;

import java.util.List;

public interface CarrierService extends CommonService<Carrier, Long> {

    Carrier getByIdFetchingTransportations(Long id);

    List<Carrier> findByName(String name);

}
