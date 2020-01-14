package task13_transportataion_sax_parser.carrier.repo;

import task13_transportataion_sax_parser.carrier.domain.Carrier;
import task13_transportataion_sax_parser.common.business.repo.CommonRepo;

public interface CarrierRepo extends CommonRepo<Carrier, Long> {

    Carrier getByIdFetchingTransportations(long id);

    Carrier[] findByName(String name);

}
