package task11_transportation_txt_parser.carrier.service;

import task11_transportation_txt_parser.carrier.domain.Carrier;
import task11_transportation_txt_parser.common.business.service.CommonService;

import java.util.List;

public interface CarrierService extends CommonService<Carrier, Long> {

    Carrier getByIdFetchingTransportations(Long id);

    List<Carrier> findByName(String name);

}
