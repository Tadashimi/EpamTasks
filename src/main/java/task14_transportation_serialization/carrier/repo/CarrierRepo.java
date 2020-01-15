package task14_transportation_serialization.carrier.repo;

import task14_transportation_serialization.carrier.domain.Carrier;
import task14_transportation_serialization.common.business.repo.CommonRepo;

public interface CarrierRepo extends CommonRepo<Carrier, Long> {

    Carrier getByIdFetchingTransportations(long id);

    Carrier[] findByName(String name);

}
