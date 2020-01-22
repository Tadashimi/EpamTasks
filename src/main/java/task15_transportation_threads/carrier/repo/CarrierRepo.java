package task15_transportation_threads.carrier.repo;

import task15_transportation_threads.carrier.domain.Carrier;
import task15_transportation_threads.common.business.repo.CommonRepo;

public interface CarrierRepo extends CommonRepo<Carrier, Long> {

    Carrier getByIdFetchingTransportations(long id);

    Carrier[] findByName(String name);

}
