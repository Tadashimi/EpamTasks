package Task11_TransportationWithFileReader.carrier.repo;

import Task11_TransportationWithFileReader.carrier.domain.Carrier;
import Task11_TransportationWithFileReader.common.business.repo.CommonRepo;

public interface CarrierRepo extends CommonRepo<Carrier, Long> {

    Carrier getByIdFetchingTransportations(long id);

    Carrier[] findByName(String name);

}
