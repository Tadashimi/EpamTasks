package task18_transportation_local_date_and_streams.carrier.service;

import task18_transportation_local_date_and_streams.carrier.domain.Carrier;
import task18_transportation_local_date_and_streams.common.business.service.CommonService;

import java.util.List;
import java.util.Optional;

public interface CarrierService extends CommonService<Carrier, Long> {

    Optional<Carrier> getByIdFetchingTransportations(Long id);

    List<Carrier> findByName(String name);

}
