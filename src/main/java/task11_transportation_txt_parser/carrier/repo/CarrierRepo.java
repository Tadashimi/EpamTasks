package task11_transportation_txt_parser.carrier.repo;

import task11_transportation_txt_parser.carrier.domain.Carrier;
import task11_transportation_txt_parser.common.business.repo.CommonRepo;

public interface CarrierRepo extends CommonRepo<Carrier, Long> {

    Carrier getByIdFetchingTransportations(long id);

    Carrier[] findByName(String name);

}
