package task18_transportation_local_date_and_streams.carrier.repo;

import task18_transportation_local_date_and_streams.carrier.domain.Carrier;
import task18_transportation_local_date_and_streams.common.business.repo.CommonRepo;

import java.util.Optional;

public interface CarrierRepo extends CommonRepo<Carrier, Long> {

    Optional<Carrier> getByIdFetchingTransportations(long id);

    Carrier[] findByName(String name);

}
