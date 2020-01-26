package task16_transportation_lambda.carrier.service;

import task16_transportation_lambda.carrier.domain.Carrier;
import task16_transportation_lambda.common.business.service.CommonService;

import java.util.List;

public interface CarrierService extends CommonService<Carrier, Long> {

    Carrier getByIdFetchingTransportations(Long id);

    List<Carrier> findByName(String name);

}
