package task14_transportation_serialization.carrier.service;

import task14_transportation_serialization.carrier.domain.Carrier;
import task14_transportation_serialization.common.business.service.CommonService;

import java.util.List;

public interface CarrierService extends CommonService<Carrier, Long> {

    Carrier getByIdFetchingTransportations(Long id);

    List<Carrier> findByName(String name);

}
