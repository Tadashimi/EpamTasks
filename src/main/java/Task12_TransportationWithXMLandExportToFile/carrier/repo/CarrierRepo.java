package Task12_TransportationWithXMLandExportToFile.carrier.repo;

import Task12_TransportationWithXMLandExportToFile.carrier.domain.Carrier;
import Task12_TransportationWithXMLandExportToFile.common.business.repo.CommonRepo;

public interface CarrierRepo extends CommonRepo<Carrier, Long> {

    Carrier getByIdFetchingTransportations(long id);

    Carrier[] findByName(String name);

}
