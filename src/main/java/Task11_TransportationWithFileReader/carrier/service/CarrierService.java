package Task11_TransportationWithFileReader.carrier.service;

import Task11_TransportationWithFileReader.carrier.domain.Carrier;
import Task11_TransportationWithFileReader.common.business.service.CommonService;

import java.util.List;

public interface CarrierService extends CommonService<Carrier, Long> {

    Carrier getByIdFetchingTransportations(Long id);

    List<Carrier> findByName(String name);

}
