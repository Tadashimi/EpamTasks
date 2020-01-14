package task12_transportation_XML_paser_and_export_to_file.carrier.repo;

import task12_transportation_XML_paser_and_export_to_file.carrier.domain.Carrier;
import task12_transportation_XML_paser_and_export_to_file.common.business.repo.CommonRepo;

public interface CarrierRepo extends CommonRepo<Carrier, Long> {

    Carrier getByIdFetchingTransportations(long id);

    Carrier[] findByName(String name);

}
