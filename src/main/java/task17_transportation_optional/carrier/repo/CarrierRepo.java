package task17_transportation_optional.carrier.repo;

import task17_transportation_optional.carrier.domain.Carrier;
import task17_transportation_optional.common.business.repo.CommonRepo;

import java.util.Optional;

public interface CarrierRepo extends CommonRepo<Carrier, Long> {

    Optional<Carrier> getByIdFetchingTransportations(long id);

    Carrier[] findByName(String name);

}
