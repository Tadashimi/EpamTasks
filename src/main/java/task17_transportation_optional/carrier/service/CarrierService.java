package task17_transportation_optional.carrier.service;

import task17_transportation_optional.carrier.domain.Carrier;
import task17_transportation_optional.common.business.service.CommonService;

import java.util.List;

public interface CarrierService extends CommonService<Carrier, Long> {

    Carrier getByIdFetchingTransportations(Long id);

    List<Carrier> findByName(String name);

}
