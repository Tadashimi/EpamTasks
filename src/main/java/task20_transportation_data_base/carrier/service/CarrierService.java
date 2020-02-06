package task20_transportation_data_base.carrier.service;

import task20_transportation_data_base.carrier.domain.Carrier;
import task20_transportation_data_base.common.business.service.CommonService;

import java.util.List;
import java.util.Optional;

public interface CarrierService extends CommonService<Carrier, Long> {

    Optional<Carrier> getByIdFetchingTransportations(Long id);

    List<Carrier> findByName(String name);

}
