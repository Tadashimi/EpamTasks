package task13_transportataion_sax_parser.carrier.service;

import task13_transportataion_sax_parser.carrier.domain.Carrier;
import task13_transportataion_sax_parser.common.business.service.CommonService;

import java.util.List;

public interface CarrierService extends CommonService<Carrier, Long> {

    Carrier getByIdFetchingTransportations(Long id);

    List<Carrier> findByName(String name);

}
