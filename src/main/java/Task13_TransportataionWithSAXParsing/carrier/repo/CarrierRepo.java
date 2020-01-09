package Task13_TransportataionWithSAXParsing.carrier.repo;

import Task13_TransportataionWithSAXParsing.carrier.domain.Carrier;
import Task13_TransportataionWithSAXParsing.common.business.repo.CommonRepo;

public interface CarrierRepo extends CommonRepo<Carrier, Long> {

    Carrier getByIdFetchingTransportations(long id);

    Carrier[] findByName(String name);

}
