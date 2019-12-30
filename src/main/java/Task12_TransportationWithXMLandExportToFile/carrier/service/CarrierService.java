package Task12_TransportationWithXMLandExportToFile.carrier.service;

import Task12_TransportationWithXMLandExportToFile.carrier.domain.Carrier;
import Task12_TransportationWithXMLandExportToFile.common.business.service.CommonService;

import java.util.List;

public interface CarrierService extends CommonService<Carrier, Long> {

    Carrier getByIdFetchingTransportations(Long id);

    List<Carrier> findByName(String name);

}
