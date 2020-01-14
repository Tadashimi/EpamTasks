package task12_transportation_XML_paser_and_export_to_file.carrier.service;

import task12_transportation_XML_paser_and_export_to_file.carrier.domain.Carrier;
import task12_transportation_XML_paser_and_export_to_file.common.business.service.CommonService;

import java.util.List;

public interface CarrierService extends CommonService<Carrier, Long> {

    Carrier getByIdFetchingTransportations(Long id);

    List<Carrier> findByName(String name);

}
